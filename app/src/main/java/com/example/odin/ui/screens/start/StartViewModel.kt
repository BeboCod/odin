package com.example.odin.ui.screens.start

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.odin.preference.PreferencesOdin
import kotlinx.coroutines.launch

class StartViewModel(private val context: Context) : ViewModel() {
    fun dataExists(callback: (Boolean) -> Unit) {
        viewModelScope.launch {
            callback(PreferencesOdin(context).existing())
        }
    }
}