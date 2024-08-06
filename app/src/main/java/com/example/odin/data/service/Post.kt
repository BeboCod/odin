package com.example.odin.data.service

data class Post(
    val id: String,
    val title: String,
    val user: User,
    val description: String,
    val urlVideo: String,
    val code: String,
    val conclusion: String,
    val collaborators: List<User>,
)
