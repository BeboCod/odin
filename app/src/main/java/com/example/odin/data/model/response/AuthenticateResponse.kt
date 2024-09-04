package com.example.odin.data.model.response

import kotlinx.serialization.Serializable

/**
 * Clase de datos que representa la respuesta de autenticación de un usuario.
 *
 * Esta clase se utiliza para recibir la información del usuario desde el servidor
 * después de un inicio de sesión exitoso.
 *
 * @property id Identificador único del usuario.
 * @property name Nombre completo del usuario.
 * @property imageUrl URL de la imagen de perfil del usuario.
 * @property verification Indica si la cuenta del usuario está verificada.
 * @property followers Lista de identificadores de los seguidores del usuario. Puede ser nula si no tiene seguidores.
 * @property posts Lista de identificadores de las publicaciones del usuario. Puede ser nula si no tiene publicaciones.
 * @property comments Lista de identificadores de los comentarios hechos por el usuario. Puede ser nula si no ha hecho comentarios.
 */
@Serializable
data class AuthenticateResponse(
    val id: String,
    val name: String,
    val imageUrl: String,
    val verification: Boolean,
    val followers: List<String>?,
    val posts: List<String>?,
    val comments: List<String>?
)

