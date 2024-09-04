package com.example.odin.model

/**
 * Clase de datos que representa un comentario en una publicación.
 *
 * Esta clase contiene la información necesaria para identificar y describir un comentario hecho
 * en una publicación por un usuario.
 *
 * @property idComment Identificador único del comentario.
 * @property idPost Identificador único de la publicación a la que se ha hecho el comentario.
 * @property idUser Identificador único del usuario que ha hecho el comentario.
 * @property author Nombre del autor del comentario.
 * @property comment Texto del comentario hecho por el usuario.
 */
data class Comments(
    val idComment: String,
    val idPost: String,
    val idUser: String,
    val author: String,
    val comment: String
)
