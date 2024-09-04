package com.example.odin.ui.mods.cardPublication

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModel
import com.example.odin.utils.Constants
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

@SuppressLint("StaticFieldLeak")
class CardPublicationViewModel(private val context: Context) : CardPublicationInterface, ViewModel() {
    data class UiState(
        val isLiked: Boolean = false,
        val isSheetOpen: Boolean = false,
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

    override fun onClickInfo(isBottomSheetOpen: Boolean) {
        _uiState.value = _uiState.value.copy(isSheetOpen = isBottomSheetOpen)
    }

    override fun onClickChip() {
        TODO("Not yet implemented")
    }

    override fun onClickComment() {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(Constants.DISCORD_URL))
        context.startActivity(intent)
    }

    override fun onClickProfile() {
        TODO("Not yet implemented")
    }

}