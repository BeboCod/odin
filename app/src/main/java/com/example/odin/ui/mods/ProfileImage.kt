package com.example.odin.ui.mods

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.odin.R

/**
 * Composable que muestra una imagen de perfil circular.
 *
 * @param imageUser [Painter] que representa la imagen del perfil.
 * @param modifier [Modifier] opcional para personalizar el tamaño y otros aspectos del componente.
 */
@Composable
fun ImagesProfile(imageUser: Painter, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(100.dp)
            .clip(CircleShape)
            .background(colorScheme.onBackground) // Fondo de color detrás de la imagen
    ) {
        Image(
            painter = imageUser,
            contentDescription = null,
            modifier = Modifier
                .size(100.dp) // Tamaño de la imagen
                .clip(CircleShape) // Forma circular
        )
    }
}

@Preview
@Composable
fun ImagesProfilePreview() {
    ImagesProfile(
        imageUser = painterResource(id = R.drawable.baseline_account_circle_24), // Usa una imagen de perfil de ejemplo
        modifier = Modifier.size(100.dp)
    )
}
