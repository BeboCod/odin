package com.example.odin.ui.screens.login

import com.example.odin.data.model.response.ValidationResponse

/**
 * Interfaz para gestionar el proceso de autenticación de usuario.
 * Define los métodos necesarios para autenticar a un usuario y manejar las entradas de email y contraseña.
 */
interface LoginInterface {

    /**
     * Realiza la autenticación del usuario.
     *
     * @param callback Función de retorno de llamada que recibe un objeto `ValidationResponse`.
     *                 El resultado de la autenticación se pasa a través de este callback.
     */
    fun auth(callback: (ValidationResponse) -> Unit)

    /**
     * Valida la dirección de correo electrónico proporcionada.
     *
     * @param email La dirección de correo electrónico a validar.
     * @return `true` si el email es válido según las reglas establecidas, `false` en caso contrario.
     */
    fun validateEmail(email: String): Boolean

    /**
     * Valida la contraseña proporcionada.
     *
     * @param password La contraseña a validar.
     * @return `true` si la contraseña cumple con los requisitos establecidos, `false` en caso contrario.
     */
    fun validatePassword(password: String): Boolean

    /**
     * Actualiza el estado del email cuando cambia.
     *
     * @param email La nueva dirección de correo electrónico.
     */
    fun onEmailChanged(email: String)

    /**
     * Actualiza el estado de la contraseña cuando cambia.
     *
     * @param password La nueva contraseña.
     */
    fun onPasswordChanged(password: String)
}
