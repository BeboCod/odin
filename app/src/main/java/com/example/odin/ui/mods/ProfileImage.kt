package com.example.odin.ui.mods

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp

@Composable
fun ImagesProfile(imageUser: Painter) {
    Box(
        modifier = Modifier
            .size(100.dp)
            .clip(CircleShape)
            .background(colorScheme.primary)
            .padding(15.dp)
    ) {
        Image(
            painter = imageUser,
            contentDescription = null,
            modifier = Modifier
                .clip(CircleShape)
                .size(100.dp)
        )
    }
}