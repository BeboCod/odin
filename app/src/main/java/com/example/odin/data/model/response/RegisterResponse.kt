package com.example.odin.data.model.response

import kotlinx.serialization.Serializable

/**
 * Clase de datos que representa la respuesta de registro de un nuevo usuario.
 *
 * Esta clase es utilizada para recibir la información necesaria después de que
 * un usuario se registre exitosamente en el sistema.
 *
 * @property token Objeto que contiene el token de autenticación generado para el usuario.
 * @property uid Identificador único del usuario generado durante el registro.
 */
@Serializable
data class RegisterResponse(
    val token: TokenResponse,
    val uid: String
)
