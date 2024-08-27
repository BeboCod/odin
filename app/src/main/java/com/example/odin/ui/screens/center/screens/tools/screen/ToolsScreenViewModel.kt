package com.example.odin.ui.screens.center.screens.tools.screen

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ToolsScreenViewModel(data: Array<String>) : ViewModel(), InterfaceToolsScreen {
    data class UiState(
        val data: Array<String>
    )

    private val _uiState = MutableStateFlow(UiState(data))
    val uiState = _uiState.asStateFlow()
}