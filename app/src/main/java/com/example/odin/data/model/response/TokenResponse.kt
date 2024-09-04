package com.example.odin.data.model.response

import kotlinx.serialization.Serializable

/**
 * Clase de datos que representa un token de autenticación.
 *
 * Esta clase se utiliza para enviar el token necesario para autenticar al usuario
 * en futuras solicitudes al servidor.
 *
 * @property accessToken Token de acceso utilizado para autenticar al usuario.
 * @property refreshToken Token de refresco utilizado para obtener un nuevo token de acceso cuando el actual expire.
 */
@Serializable
data class TokenResponse(
    val accessToken: String,
    val refreshToken: String
)
