package com.example.odin.ui.screens.login

import android.content.Context
import android.widget.Toast
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.odin.data.model.request.AuthenticateRequest
import com.example.odin.data.model.response.AuthenticateResponse
import com.example.odin.data.model.response.ValidationResponse
import com.example.odin.preference.PREFERENCE_LIST_KEY.LIST_COMMENTS_KEY
import com.example.odin.preference.PREFERENCE_LIST_KEY.LIST_FOLLOWERS_KEY
import com.example.odin.preference.PREFERENCE_LIST_KEY.LIST_POSTS_KEY
import com.example.odin.preference.PREFERENCE_STRING_KEY.BOOLEAN_VERIFICATION_KEY
import com.example.odin.preference.PREFERENCE_STRING_KEY.STRING_ID_KEY
import com.example.odin.preference.PREFERENCE_STRING_KEY.STRING_IMAGE_URL_KEY
import com.example.odin.preference.PREFERENCE_STRING_KEY.STRING_NAME_KEY
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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(private val client: HttpClient, private val context: Context) : ViewModel(),
    LoginInterface {
    data class UiState(
        val email: String = "",
        val password: String = "",
        val isShowingError: Boolean = false,
    )

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    override fun onEmailChanged(email: String) {
        _uiState.update { it.copy(email = email) }
    }

    override fun onPasswordChanged(password: String) {
        _uiState.update { it.copy(password = password) }
    }

    fun onShowingError() {
        _uiState.update { it.copy(isShowingError = false) }
    }

    fun isNotEmpty(): Boolean =
        uiState.value.email.isNotEmpty() && uiState.value.password.isNotEmpty()


    override fun validateEmail(email: String): Boolean =
        email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$".toRegex())

    override fun validatePassword(password: String): Boolean = password.length >= 6

    private fun validate(): Boolean {
        val email = uiState.value.email.lowercase()
        val password = uiState.value.password.lowercase()

        if (!validateEmail(email)) {
            Toast.makeText(context, "Email no válido", Toast.LENGTH_SHORT).show()
            return false
        }

        if (!validatePassword(password)) {
            Toast.makeText(context, "Contraseña no válida", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    override fun auth(callback: (ValidationResponse) -> Unit) {
        val auth = FirebaseAuth.getInstance()
        if (validate())
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
                                                preferences[STRING_ID_KEY.keys] =
                                                    authenticateResponse.id
                                                preferences[STRING_NAME_KEY.keys] =
                                                    authenticateResponse.name
                                                preferences[STRING_IMAGE_URL_KEY.keys] =
                                                    authenticateResponse.imageUrl
                                                preferences[BOOLEAN_VERIFICATION_KEY.keys] =
                                                    authenticateResponse.verification.toString()
                                                preferences[LIST_POSTS_KEY.keys] =
                                                    authenticateResponse.posts?.toSet()
                                                        ?: emptySet()
                                                preferences[LIST_FOLLOWERS_KEY.keys] =
                                                    authenticateResponse.followers?.toSet()
                                                        ?: emptySet()
                                                preferences[LIST_COMMENTS_KEY.keys] =
                                                    authenticateResponse.comments?.toSet()
                                                        ?: emptySet()
                                            }
                                            callback(
                                                ValidationResponse(
                                                    isValid = true,
                                                    data = "Inicio de sesión exitoso"
                                                )
                                            )
                                        } else {
                                            _uiState.update { it.copy(isShowingError = true) }
                                            callback(
                                                ValidationResponse(
                                                    isValid = false,
                                                    data = "Inicio de sesión fallido"
                                                )
                                            )
                                        }
                                    } catch (e: ClientRequestException) {
                                        _uiState.update { it.copy(isShowingError = true) }
                                        callback(
                                            ValidationResponse(
                                                isValid = false,
                                                data = "Inicio de sesión fallido"
                                            )
                                        )
                                    } catch (e: Exception) {
                                        _uiState.update { it.copy(isShowingError = true) }
                                        callback(
                                            ValidationResponse(
                                                isValid = false,
                                                data = "Inicio de sesión fallido"
                                            )
                                        )
                                    }
                                }
                            } else {
                                _uiState.update { it.copy(isShowingError = true) }
                                callback(
                                    ValidationResponse(
                                        isValid = false,
                                        data = "Inicio de sesión fallido"
                                    )
                                )
                            }
                        }
                    } else {
                        _uiState.update { it.copy(isShowingError = true) }
                        callback(
                            ValidationResponse(
                                isValid = false,
                                data = "Inicio de sesión fallido"
                            )
                        )
                    }
                }
        else {
            _uiState.update { it.copy(isShowingError = true) }
            callback(
                ValidationResponse(
                    isValid = false,
                    data = "Inicio de sesión fallido"
                )
            )
        }
    }
}