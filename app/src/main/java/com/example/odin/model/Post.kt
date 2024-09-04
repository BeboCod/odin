package com.example.odin.model

/**
 * Clase de datos que representa una publicación en la red social.
 *
 * Esta clase contiene la información de una publicación, incluyendo el título, el usuario
 * que la creó, una descripción, un enlace a un video relacionado, el código fuente,
 * una conclusión, y una lista de colaboradores.
 *
 * @property id Identificador único de la publicación.
 * @property title Título de la publicación.
 * @property user Usuario que creó la publicación. Representado como un objeto de la clase `User`.
 * @property description Descripción detallada de la publicación.
 * @property urlVideo Enlace al video relacionado con la publicación, si aplica.
 * @property code Código fuente presentado en la publicación.
 * @property conclusion Conclusión o resumen relacionado con la publicación.
 * @property collaborators Lista de usuarios que colaboraron en la publicación. Puede estar vacía si no hay colaboradores.
 */
data class Post(
    val id: String,
    val title: String,
    val user: User,
    val description: String,
    val urlVideo: String,
    val code: String,
    val conclusion: String,
    val collaborators: List<User>
)

