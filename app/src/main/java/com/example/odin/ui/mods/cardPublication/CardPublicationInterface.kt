package com.example.odin.ui.mods.cardPublication

interface CardPublicationInterface {
    fun onClickCard()

    fun onClickLike()

    fun onClickShare()

    fun onClickInfo(isBottomSheetOpen: Boolean)

    fun onClickChip()

    fun onClickComment()

    fun onClickProfile()
}