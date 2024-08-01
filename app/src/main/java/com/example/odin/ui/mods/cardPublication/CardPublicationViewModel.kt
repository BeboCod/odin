package com.example.odin.ui.mods.cardPublication

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CardPublicationViewModel : CardPublicationInterface {

    data class UiState(
        val isLiked: Boolean = false,
    )

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    override fun onClickCard() {
        TODO("Not yet implemented")
    }

    override fun onClickLike() {
        _uiState.value = _uiState.value.copy(isLiked = !_uiState.value.isLiked)
    }

    override fun onClickShare() {
        TODO("Not yet implemented")
    }

    override fun onClickInfo() {
        TODO("Not yet implemented")
    }

    override fun onClickChip() {
        TODO("Not yet implemented")
    }

    override fun onClickComment() {
        TODO("Not yet implemented")
    }

    override fun onClickProfile() {
        TODO("Not yet implemented")
    }

}