package com.example.odin.data.model.request

import kotlinx.serialization.Serializable

/**
 * Clase de datos que representa la solicitud de autenticación.
 *
 * Esta clase es utilizada para enviar un token de identificación al servidor
 * para autenticar a un usuario.
 *
 * @property idToken El token de identificación que se utiliza para autenticar al usuario.
 */
@Serializable
data class AuthenticateRequest(
    val idToken: String
)
