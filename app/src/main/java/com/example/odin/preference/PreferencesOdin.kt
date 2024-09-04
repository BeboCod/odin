package com.example.odin.preference

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.example.odin.utils.dataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

/**
 * Implementación de [InterfacePreference] que utiliza DataStore para gestionar las preferencias de la aplicación.
 *
 * @param context Contexto de la aplicación. Utilizado para inicializar el DataStore.
 */
class PreferencesOdin(context: Context) : InterfacePreference {

    // DataStore para manejar las preferencias compartidas
    private val dataStore: DataStore<Preferences> = context.dataStore

    /**
     * Verifica si existen datos almacenados en las preferencias.
     *
     * @return `true` si existen datos, `false` en caso contrario.
     */
    override suspend fun existing(): Boolean {
        return dataStore.data.firstOrNull()?.toPreferences() != null
    }

    /**
     * Obtiene un flujo que emite un valor booleano indicando si el usuario está verificado.
     *
     * @return Un flujo que emite `true` si el usuario está verificado, `false` en caso contrario.
     */
    override fun isUserVerified(): Flow<Boolean> =
        dataStore.data.map { preferences ->
            preferences[KeysPreference.PreferenceStringKey.BOOLEAN_VERIFICATION_KEY.key]?.toBoolean() ?: false
        }

    /**
     * Guarda un valor de tipo cadena en las preferencias.
     *
     * @param key Clave bajo la cual se almacenará el valor. Debe ser una instancia de `KeysPreference.PreferenceStringKey`.
     * @param value El valor de cadena a guardar.
     */
    override suspend fun saveString(key: KeysPreference.PreferenceStringKey, value: String) {
        dataStore.edit { preferences ->
            preferences[key.key] = value
        }
    }

    /**
     * Guarda un conjunto de cadenas en las preferencias.
     *
     * @param key Clave bajo la cual se almacenará el conjunto. Debe ser una instancia de `KeysPreference.PreferenceListKey`.
     * @param value El conjunto de cadenas a guardar.
     */
    override suspend fun saveStringSet(key: KeysPreference.PreferenceListKey, value: Set<String>) {
        dataStore.edit { preferences ->
            preferences[key.key] = value
        }
    }

    /**
     * Obtiene un flujo que emite una lista de identificadores de publicaciones.
     *
     * @return Un flujo que emite la lista de identificadores de publicaciones.
     */
    override fun getPostIds(): Flow<List<String>> = dataStore.data.map { preferences ->
        preferences[KeysPreference.PreferenceListKey.LIST_POSTS_KEY.key]?.toList() ?: emptyList()
    }

    /**
     * Obtiene un flujo que emite una lista de identificadores de seguidores.
     *
     * @return Un flujo que emite la lista de identificadores de seguidores.
     */
    override fun getFollowerIds(): Flow<List<String>> = dataStore.data.map { preferences ->
        preferences[KeysPreference.PreferenceListKey.LIST_FOLLOWERS_KEY.key]?.toList() ?: emptyList()
    }

    /**
     * Obtiene un flujo que emite una lista de identificadores de comentarios.
     *
     * @return Un flujo que emite la lista de identificadores de comentarios.
     */
    override fun getCommentIds(): Flow<List<String>> = dataStore.data.map { preferences ->
        preferences[KeysPreference.PreferenceListKey.LIST_COMMENTS_KEY.key]?.toList() ?: emptyList()
    }

    /**
     * Obtiene un flujo que emite el nombre del usuario.
     *
     * @return Un flujo que emite el nombre del usuario.
     */
    override fun getUserName(): Flow<String> = dataStore.data.map { preferences ->
        preferences[KeysPreference.PreferenceStringKey.STRING_NAME_KEY.key] ?: ""
    }
}
