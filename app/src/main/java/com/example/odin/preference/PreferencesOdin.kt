package com.example.odin.preference

import android.content.Context
import androidx.datastore.preferences.core.edit
import com.example.odin.utils.dataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class PreferencesOdin(context: Context) {
    private val dataStore = context.dataStore
    private val KEYS = PREFERENCE_STRING_KEY.entries

    suspend fun existing(): Boolean = dataStore.data.first().contains(KEYS.first().keys)

    fun verified(): Flow<Boolean> =
        dataStore.data.map { it[PREFERENCE_STRING_KEY.BOOLEAN_VERIFICATION_KEY.keys].toBoolean() }

    suspend fun saveDataString(key: PREFERENCE_STRING_KEY, value: String) {
        dataStore.edit { preferences ->
            preferences[key.keys] = value
        }
    }

    suspend fun saveDataList(key: PREFERENCE_LIST_KEY, value: Set<String>) {
        dataStore.edit { preferences ->
            preferences[key.keys] = value
        }
    }

    fun getDataPost(): Flow<List<String>> = dataStore.data.map { preferences ->
        preferences[PREFERENCE_LIST_KEY.LIST_POSTS_KEY.keys]?.toList() ?: emptyList()
    }


    fun getDataFollowers(): Flow<List<String>> = dataStore.data.map { preferences ->
        preferences[PREFERENCE_LIST_KEY.LIST_FOLLOWERS_KEY.keys]?.toList() ?: emptyList()
    }

    fun getDataComments(): Flow<List<String>> = dataStore.data.map { preferences ->
        preferences[PREFERENCE_LIST_KEY.LIST_COMMENTS_KEY.keys]?.toList() ?: emptyList()
    }

    fun getName(): Flow<String> = dataStore.data.map { preferences ->
        preferences[PREFERENCE_STRING_KEY.STRING_NAME_KEY.keys]?.toString() ?: ""
    }
}