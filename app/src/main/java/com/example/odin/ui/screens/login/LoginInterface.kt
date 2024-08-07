package com.example.odin.ui.screens.login

import com.example.odin.data.model.response.ValidationResponse

interface LoginInterface {
    fun auth(): ValidationResponse
    fun validateEmail(email: String): Boolean
    fun validatePassword(password: String): Boolean
    fun onEmailChanged(email: String)
    fun onPasswordChanged(password: String)
}