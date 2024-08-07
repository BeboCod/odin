package com.example.odin.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class ValidationResponse(val isValid: Boolean, val errorMessage: String? = null, val data: String? = null)
