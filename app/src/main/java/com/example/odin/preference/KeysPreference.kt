package com.example.odin.preference

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey

/**
 * Objeto que contiene claves para acceder a las preferencias compartidas en la aplicación.
 *
 * Utiliza enumeraciones para definir las claves para diferentes tipos de datos almacenados en las preferencias.
 */
object KeysPreference {

    /**
     * Enum que define las claves para valores de tipo cadena en las preferencias.
     *
     * @property keys La clave asociada al valor de tipo cadena.
     */
    enum class PreferenceStringKey(val key: Preferences.Key<String>) {
        STRING_ID_KEY(stringPreferencesKey("string_id_key")),
        STRING_NAME_KEY(stringPreferencesKey("string_name_key")),
        STRING_IMAGE_URL_KEY(stringPreferencesKey("string_image_url_key")),
        BOOLEAN_VERIFICATION_KEY(stringPreferencesKey("boolean_verification_key")),
    }

    /**
     * Enum que define las claves para conjuntos de cadenas en las preferencias.
     *
     * @property key La clave asociada al conjunto de cadenas.
     */
    enum class PreferenceListKey(val key: Preferences.Key<Set<String>>) {
        LIST_POSTS_KEY(stringSetPreferencesKey("list_posts_key")),
        LIST_FOLLOWERS_KEY(stringSetPreferencesKey("list_followers_key")),
        LIST_COMMENTS_KEY(stringSetPreferencesKey("list_comments_key")),
    }
}

