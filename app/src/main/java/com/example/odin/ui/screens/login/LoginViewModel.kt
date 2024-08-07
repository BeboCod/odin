package com.example.odin.ui.screens.login

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.odin.data.model.request.AuthenticateRequest
import com.example.odin.data.model.response.AuthenticateResponse
import com.example.odin.data.model.response.ValidationResponse
import com.example.odin.utils.Constants
import com.example.odin.utils.dataStore
import com.google.firebase.auth.FirebaseAuth
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(private val client: HttpClient, private val context: Context) : ViewModel(), LoginInterface {
    data class UiState(
        val email: String = "",
        val password: String = "",
        val isShowingError: Boolean = false
    )

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    fun getData(): Flow<Pair<String?, List<String>>> {
        return context.dataStore.data.map { preferences ->
            val stringValue = preferences[Constants.STRING_KEY]
            val listValue = preferences[Constants.LIST_KEY]?.toList() ?: emptyList()
            Pair(stringValue, listValue)
        }
    }

    override fun onEmailChanged(email: String) {
        _uiState.update { it.copy(email = email) }
    }

    override fun onPasswordChanged(password: String) {
        _uiState.update { it.copy(password = password) }
    }

    fun onShowingError(isShowingError: Boolean) {
        _uiState.update { it.copy(isShowingError = isShowingError) }
    }

    override fun validateEmail(email: String): Boolean =
        email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$".toRegex())

    override fun validatePassword(password: String): Boolean = password.length >= 6

    override fun auth(): ValidationResponse {
        val email = uiState.value.email
        val password = uiState.value.password

        if (!validateEmail(email)) return ValidationResponse(isValid = false, data = "Email no válido")

        if (!validatePassword(password)) return ValidationResponse(
            isValid = false,
            data = "Contraseña no válida"
        )

        val auth = FirebaseAuth.getInstance()
        auth.signInWithEmailAndPassword(uiState.value.email, uiState.value.password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    user?.getIdToken(true)?.addOnCompleteListener { tokenTask ->
                        if (tokenTask.isSuccessful) {
                            val idToken = tokenTask.result?.token ?: ""
                            viewModelScope.launch {
                                try {
                                    val response =
                                        client.post("${Constants.HTTPS_ODIN}/auth") {
                                            contentType(ContentType.Application.Json)
                                            setBody(
                                                AuthenticateRequest(
                                                    idToken = idToken
                                                )
                                            )
                                        }
                                    if (response.status == HttpStatusCode.Accepted) {
                                        val authenticateResponse =
                                            response.body<AuthenticateResponse>()
                                        context.dataStore.edit { preferences ->
                                            preferences[Constants.STRING_ID_KEY] =
                                                authenticateResponse.id
                                            preferences[Constants.STRING_NAME_KEY] =
                                                authenticateResponse.name
                                            preferences[Constants.STRING_IMAGE_URL_KEY] =
                                                authenticateResponse.imageUrl
                                            preferences[Constants.BOOLEAN_VERIFICATION_KEY] =
                                                authenticateResponse.verification
                                            preferences[Constants.LIST_POSTS_KEY] =
                                                authenticateResponse.posts?.toSet() ?: emptySet()
                                            preferences[Constants.LIST_FOLLOWERS_KEY] =
                                                authenticateResponse.followers?.toSet()
                                                    ?: emptySet()
                                            preferences[Constants.LIST_COMMENTS_KEY] =
                                                authenticateResponse.comments?.toSet() ?: emptySet()
                                        }
                                        ValidationResponse(
                                            isValid = true,
                                            data = "Inicio de sesión exitoso"
                                        )
                                    } else {
                                        ValidationResponse(
                                            isValid = false,
                                            data = "Inicio de sesión fallido"
                                        )
                                    }
                                } catch (e: ClientRequestException) {
                                    ValidationResponse(
                                        isValid = false,
                                        errorMessage = e.message,
                                        data = "Inicio de sesión fallido"
                                    )
                                } catch (e: Exception) {
                                    ValidationResponse(
                                        isValid = false,
                                        errorMessage = e.message,
                                        data = "Inicio de sesión fallido"
                                    )
                                }
                            }
                        } else {
                            ValidationResponse(isValid = false, data = "Inicio de sesión fallido")
                        }
                    }
                } else {
                    ValidationResponse(isValid = false, data = "Inicio de sesión fallido")
                }
            }

        return ValidationResponse(isValid = false, data = "Inicio de sesión fallido")
    }
}