package com.example.odin.ui.screens.register

import android.content.Context
import android.widget.Toast
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.odin.data.model.request.RegisterRequest
import com.example.odin.data.model.response.RegisterResponse
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
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RegisterViewModel(private val context: Context, private val client: HttpClient) : ViewModel(),
    RegisterInterface {
    data class UiState(
        val username: String = "",
        val email: String = "",
        val password: String = "",
        val confirmPassword: String = "",
        val isShowingError: Boolean = false,
    )

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    override fun onShowError() {
        _uiState.update { it.copy(isShowingError = false) }
    }

    private fun validateUsername(username: String): Boolean =
        username.length in 1..14 && "[_.-]".toRegex().containsMatchIn(username)

    private fun validateEmail(email: String): Boolean =
        email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$".toRegex())

    private fun validatePassword(password: String): Boolean = password.length >= 6

    private fun validateConfirmPassword(password: String, confirmPassword: String): Boolean =
        password == confirmPassword

    override fun isNotEmpty(): Boolean =
        uiState.value.username.isNotEmpty() && uiState.value.email.isNotEmpty() && uiState.value.password.isNotEmpty() && uiState.value.confirmPassword.isNotEmpty()

    override fun onValuesChanged(
        username: String,
        email: String,
        password: String,
        confirmPassword: String,
    ) {
        _uiState.update {
            it.copy(
                username = username,
                email = email,
                password = password,
                confirmPassword = confirmPassword
            )
        }
    }

    override fun validate(): Boolean {
        val username = uiState.value.username
        val email = uiState.value.email
        val password = uiState.value.password
        val confirmPassword = uiState.value.confirmPassword
        if (!validateUsername(username)) {
            Toast.makeText(context, "Username no válido", Toast.LENGTH_SHORT).show()
            return false
        }
        if (!validateEmail(email)) {
            Toast.makeText(context, "Email no válido", Toast.LENGTH_SHORT).show()
            return false
        }
        if (!validatePassword(password)) {
            Toast.makeText(context, "Contraseña no válida", Toast.LENGTH_SHORT).show()
            return false
        }
        if (!validateConfirmPassword(password, confirmPassword)) {
            Toast.makeText(context, "Contraseñas no coinciden", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    override fun register(callback: (ValidationResponse) -> Unit) {
        val username = uiState.value.username
        val email = uiState.value.email
        val password = uiState.value.password

        if (validate())
        viewModelScope.launch(Dispatchers.Main) {
            try {
                val response = client.post("${Constants.HTTPS_ODIN}/register") {
                    contentType(ContentType.Application.Json)
                    setBody(
                        RegisterRequest(
                            name = username.lowercase(),
                            email = email.lowercase(),
                            password = password.lowercase()
                        )
                    )
                }
                if (response.status == HttpStatusCode.Created) {
                    val data = response.body<RegisterResponse>()
                    context.dataStore.edit { preferences ->
                        preferences[STRING_ID_KEY.keys] = data.uid
                        preferences[STRING_NAME_KEY.keys] = username
                        preferences[STRING_IMAGE_URL_KEY.keys] = ""
                        preferences[BOOLEAN_VERIFICATION_KEY.keys] = "false"
                        preferences[LIST_POSTS_KEY.keys] = emptySet()
                        preferences[LIST_FOLLOWERS_KEY.keys] = emptySet()
                        preferences[LIST_COMMENTS_KEY.keys] = emptySet()
                    }
                    Toast.makeText(context, "Registro exitoso", Toast.LENGTH_SHORT).show()
                    callback(ValidationResponse(isValid = true, data = "Registro exitoso"))
                } else {
                    _uiState.update { it.copy(isShowingError = true) }
                    callback(ValidationResponse(isValid = false, data = "Registro fallido"))
                }
            } catch (e: ClientRequestException) {
                e.printStackTrace()
                _uiState.update { it.copy(isShowingError = true) }
                callback(
                    ValidationResponse(
                        isValid = false,
                        data = "Registro fallido",
                        errorMessage = e.message
                    )
                )
            } catch (e: Exception) {
                e.printStackTrace()
                _uiState.update { it.copy(isShowingError = true) }
                callback(
                    ValidationResponse(
                        isValid = false,
                        data = "Registro fallido",
                        errorMessage = e.message
                    )
                )
            } finally {
                client.close()
            }
        }
        else
            callback(ValidationResponse(isValid = false, data = "Registro fallido"))
    }

}