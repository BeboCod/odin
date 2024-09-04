package com.example.odin.model

/**
 * Clase de datos que representa a un usuario en la red social.
 *
 * Esta clase contiene la información básica de un usuario, incluyendo su nombre de usuario,
 * una URL de su imagen de perfil, un identificador único, y un indicador de verificación.
 *
 * @property userName Nombre de usuario del usuario. Este es el nombre que se mostrará en la interfaz.
 * @property urlImage URL de la imagen de perfil del usuario. Puede ser una URL vacía si no tiene imagen.
 * @property id Identificador único del usuario. Se usa para distinguir a cada usuario.
 * @property verified Indica si el usuario ha sido verificado. `true` si está verificado, `false` si no lo está.
 */
data class User(
    val userName: String,
    val urlImage: String,
    val id: String,
    val verified: Boolean
)
