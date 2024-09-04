package com.example.odin.preference

import kotlinx.coroutines.flow.Flow

/**
 * Interfaz que define las operaciones básicas para gestionar las preferencias de una aplicación.
 *
 * Esta interfaz proporciona métodos para:
 *  - Verificar la existencia de datos en las preferencias.
 *  - Obtener el estado de verificación del usuario.
 *  - Guardar datos de tipo cadena y conjuntos de cadenas.
 *  - Obtener flujos de datos para diferentes tipos de información, como publicaciones, seguidores, comentarios y nombre de usuario.
 */
interface InterfacePreference {

    /**
     * Verifica si existen datos almacenados en las preferencias.
     *
     * @return `true` si existen datos, `false` en caso contrario.
     */
    suspend fun existing(): Boolean

    /**
     * Obtiene un flujo que emite un valor booleano indicando si el usuario está verificado.
     *
     * @return Un flujo que emite `true` si el usuario está verificado, `false` en caso contrario.
     */
    fun isUserVerified(): Flow<Boolean>

    /**
     * Guarda un valor de tipo cadena en las preferencias.
     *
     * @param key La clave bajo la cual se almacenará el valor. Debe ser una instancia de `KeysPreference.PreferenceStringKey`.
     * @param value El valor de cadena a guardar.
     */
    suspend fun saveString(key: KeysPreference.PreferenceStringKey, value: String)

    /**
     * Guarda un conjunto de cadenas en las preferencias.
     *
     * @param key La clave bajo la cual se almacenará el conjunto. Debe ser una instancia de `KeysPreference.PreferenceListKey`.
     * @param value El conjunto de cadenas a guardar.
     */
    suspend fun saveStringSet(key: KeysPreference.PreferenceListKey, value: Set<String>)

    /**
     * Obtiene un flujo que emite una lista de identificadores de publicaciones.
     *
     * @return Un flujo que emite la lista de identificadores de publicaciones.
     */
    fun getPostIds(): Flow<List<String>>

    /**
     * Obtiene un flujo que emite una lista de identificadores de seguidores.
     *
     * @return Un flujo que emite la lista de identificadores de seguidores.
     */
    fun getFollowerIds(): Flow<List<String>>

    /**
     * Obtiene un flujo que emite una lista de identificadores de comentarios.
     *
     * @return Un flujo que emite la lista de identificadores de comentarios.
     */
    fun getCommentIds(): Flow<List<String>>

    /**
     * Obtiene un flujo que emite el nombre del usuario.
     *
     * @return Un flujo que emite el nombre del usuario.
     */
    fun getUserName(): Flow<String>
}
