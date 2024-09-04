package com.example.odin.data.model.response

import kotlinx.serialization.Serializable

/**
 * Clase de datos que representa la respuesta de una operación de validación.
 *
 * Esta clase se utiliza para comunicar los resultados de una validación, incluyendo
 * si la validación fue exitosa o no, un mensaje de error opcional y datos adicionales opcionales.
 *
 * @property isValid Indica si la validación fue exitosa (`true`) o fallida (`false`).
 * @property errorMessage Mensaje de error asociado a la validación fallida. Es nulo si la validación fue exitosa.
 * @property data Datos adicionales relacionados con la validación. Es nulo si no se requieren datos adicionales.
 */
@Serializable
data class ValidationResponse(
    val isValid: Boolean,
    val errorMessage: String? = null,
    val data: String? = null
)
