package com.example.odin.preference

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey

enum class PREFERENCE_STRING_KEY(val keys: Preferences.Key<String>) {
    STRING_ID_KEY(stringPreferencesKey("string_id_key")),
    STRING_NAME_KEY(stringPreferencesKey("string_name_key")),
    STRING_IMAGE_URL_KEY(stringPreferencesKey("string_image_url_key")),
    BOOLEAN_VERIFICATION_KEY(stringPreferencesKey("boolean_verification_key")),
}

enum class PREFERENCE_LIST_KEY(val keys: Preferences.Key<Set<String>>){
    LIST_POSTS_KEY(stringSetPreferencesKey("list_posts_key")),
    LIST_FOLLOWERS_KEY(stringSetPreferencesKey("list_followers_key")),
    LIST_COMMENTS_KEY(stringSetPreferencesKey("list_comments_key")),
}
