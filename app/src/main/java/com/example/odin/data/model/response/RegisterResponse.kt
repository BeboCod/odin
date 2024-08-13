package com.example.odin.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class RegisterResponse(val token: TokenResponse, val uid: String)