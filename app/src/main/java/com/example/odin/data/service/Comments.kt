package com.example.odin.data.service

data class Comment(
    val idComment: String,
    val idPost: String,
    val idUser: String,
    val author: String,
    val comment: String,
)