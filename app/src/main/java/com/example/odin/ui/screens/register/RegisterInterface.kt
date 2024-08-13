package com.example.odin.ui.screens.register

import com.example.odin.data.model.response.ValidationResponse

interface RegisterInterface {
    fun onShowError()
    fun isNotEmpty(): Boolean
    fun onValuesChanged(username: String, email: String, password: String, confirmPassword: String)
    fun validate(): Boolean
    fun register(callback: (ValidationResponse) -> Unit)
}