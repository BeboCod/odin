package com.example.odin.data.model.request

import kotlinx.serialization.Serializable

/**
 * Clase de datos que representa la solicitud de registro de un nuevo usuario.
 *
 * Esta clase se utiliza para enviar la información necesaria al servidor
 * para crear una nueva cuenta de usuario.
 *
 * @property email Correo electrónico del nuevo usuario. Debe ser un correo válido.
 * @property password Contraseña del nuevo usuario. Debe cumplir con los requisitos de seguridad.
 * @property name Nombre completo del nuevo usuario.
 */
@Serializable
data class RegisterRequest(
    val email: String,
    val password: String,
    val name: String
)
