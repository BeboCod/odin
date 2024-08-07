package com.example.odin.data.model.request

import kotlinx.serialization.Serializable

@Serializable
data class AuthenticateRequest(val idToken: String)
