package com.example.odin.ui.screens.register

import com.example.odin.data.model.response.ValidationResponse

interface RegisterInterface {
    /**
     * Muestra un mensaje de error.
     */
    fun onShowError()

    /**
     * Verifica si todos los campos necesarios están llenos.
     *
     * @return `true` si todos los campos están llenos, `false` en caso contrario.
     */
    fun isNotEmpty(): Boolean

    /**
     * Actualiza los valores de los campos de entrada.
     *
     * @param username Nombre de usuario ingresado.
     * @param email Dirección de correo electrónico ingresada.
     * @param password Contraseña ingresada.
     * @param confirmPassword Confirmación de la contraseña ingresada.
     */
    fun onValuesChanged(username: String, email: String, password: String, confirmPassword: String)

    /**
     * Valida los campos de entrada.
     *
     * @return `true` si todos los campos son válidos, `false` en caso contrario.
     */
    fun validate(): Boolean

    /**
     * Registra al usuario utilizando los valores de entrada.
     *
     * @param callback Función de retorno de llamada que recibe un objeto `ValidationResponse`
     *                 indicando el resultado del registro.
     */
    fun register(callback: (ValidationResponse) -> Unit)
}
