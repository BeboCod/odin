package com.example.odin.ui.screens.login

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.odin.data.model.request.AuthenticateRequest
import com.example.odin.data.model.response.AuthenticateResponse
import com.example.odin.data.model.response.ValidationResponse
import com.example.odin.preference.KeysPreference
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

/**
 * ViewModel para la gestión de la pantalla de inicio de sesión.
 *
 * @param client Instancia del cliente HTTP para realizar las solicitudes.
 * @param context Contexto de la aplicación.
 */
@SuppressLint("StaticFieldLeak")
class LoginViewModel(
    private val client: HttpClient,
    private val context: Context
) : ViewModel(), LoginInterface {

    /**
     * Estado de la UI del ViewModel.
     *
     * @property email Dirección de correo electrónico ingresada por el usuario.
     * @property password Contraseña ingresada por el usuario.
     * @property isShowingError Indica si se debe mostrar un mensaje de error.
     */
    data class UiState(
        val email: String = "",
        val password: String = "",
        val isShowingError: Boolean = false,
    )

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    /**
     * Actualiza el estado del correo electrónico.
     *
     * @param email La nueva dirección de correo electrónico.
     */
    override fun onEmailChanged(email: String) {
        _uiState.update { it.copy(email = email) }
    }

    /**
     * Actualiza el estado de la contraseña.
     *
     * @param password La nueva contraseña.
     */
    override fun onPasswordChanged(password: String) {
        _uiState.update { it.copy(password = password) }
    }

    /**
     * Oculta el mensaje de error si está mostrando uno.
     */
    fun onShowingError() {
        _uiState.update { it.copy(isShowingError = false) }
    }

    /**
     * Verifica si los campos de email y contraseña no están vacíos.
     *
     * @return `true` si ambos campos están llenos, `false` en caso contrario.
     */
    fun isNotEmpty(): Boolean =
        uiState.value.email.isNotEmpty() && uiState.value.password.isNotEmpty()

    /**
     * Valida la dirección de correo electrónico.
     *
     * @param email La dirección de correo electrónico a validar.
     * @return `true` si el email es válido, `false` en caso contrario.
     */
    override fun validateEmail(email: String): Boolean =
        email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$".toRegex())

    /**
     * Valida la contraseña.
     *
     * @param password La contraseña a validar.
     * @return `true` si la contraseña tiene al menos 6 caracteres, `false` en caso contrario.
     */
    override fun validatePassword(password: String): Boolean = password.length >= 6

    /**
     * Realiza la validación de los campos de email y contraseña.
     *
     * @return `true` si ambos campos son válidos, `false` en caso contrario.
     */
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

    /**
     * Realiza la autenticación del usuario mediante Firebase y el backend de la aplicación.
     *
     * @param callback Función de retorno de llamada que recibe un objeto `ValidationResponse`
     *                 indicando el resultado de la autenticación.
     */
    override fun auth(callback: (ValidationResponse) -> Unit) {
        val auth = FirebaseAuth.getInstance()
        if (validate()) {
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
                                                preferences[KeysPreference.PreferenceStringKey.STRING_ID_KEY.key] =
                                                    authenticateResponse.id
                                                preferences[KeysPreference.PreferenceStringKey.STRING_NAME_KEY.key] =
                                                    authenticateResponse.name
                                                preferences[KeysPreference.PreferenceStringKey.STRING_IMAGE_URL_KEY.key] =
                                                    authenticateResponse.imageUrl
                                                preferences[KeysPreference.PreferenceStringKey.BOOLEAN_VERIFICATION_KEY.key] =
                                                    authenticateResponse.verification.toString()
                                                preferences[KeysPreference.PreferenceListKey.LIST_POSTS_KEY.key] =
                                                    authenticateResponse.posts?.toSet()
                                                        ?: emptySet()
                                                preferences[KeysPreference.PreferenceListKey.LIST_FOLLOWERS_KEY.key] =
                                                    authenticateResponse.followers?.toSet()
                                                        ?: emptySet()
                                                preferences[KeysPreference.PreferenceListKey.LIST_COMMENTS_KEY.key] =
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
}
