package com.example.odin.ui.screens.center.screens.profile

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.odin.preference.PreferencesOdin
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.launch

class ProfileViewModel(private val context: Context) : ViewModel(), InterfaceProfileViewModel {

    data class UiState(
        val openBottomSheet: Boolean = false,
        val composeId: String = "",
    )

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    suspend fun getPost(): List<String> {
        val preferences = PreferencesOdin(context)
        return preferences.getDataPost().first()
    }

    suspend fun getFollowers(): List<String> {
        val preferences = PreferencesOdin(context)
        return preferences.getDataFollowers().first()
    }

    suspend fun getComments(): List<String> {
        val preferences = PreferencesOdin(context)
        return preferences.getDataComments().first()
    }

    suspend fun getVerified(): Boolean {
        val preferences = PreferencesOdin(context)
        return preferences.verified().first()
    }

    suspend fun getName(): String {
        val preferences = PreferencesOdin(context)
        return preferences.getName().first().toString()
    }
}