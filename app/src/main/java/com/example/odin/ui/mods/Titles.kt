package com.example.odin.ui.mods

import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.odin.ui.theme.OdinTheme

// Definiciones de estilos de texto
private val TitleStyle = TextStyle(
    fontSize = 50.sp,
    fontWeight = FontWeight.Bold,
    fontStyle = FontStyle.Normal,
    textAlign = TextAlign.Center,
    shadow = Shadow(
        color = Color(0xFFF6B076),
        offset = Offset(0.0f, 0.0f),
        blurRadius = 5f
    ),
)

private val CardTitleStyle = TextStyle(
    fontSize = 20.sp,
    fontWeight = FontWeight.Bold,
    fontStyle = FontStyle.Normal,
    textAlign = TextAlign.Center,
)

private val DescriptionStyle = TextStyle(
    fontSize = 16.sp,
    fontStyle = FontStyle.Normal,
    textAlign = TextAlign.Center,
)

private val UserNameStyle = TextStyle(
    fontSize = 25.sp,
    fontWeight = FontWeight.Medium,
    fontStyle = FontStyle.Normal,
    textAlign = TextAlign.Center,
)

/**
 * Composable que muestra un título con un estilo destacado.
 *
 * @param text Texto a mostrar en el título.
 */
@Composable
fun TitleOdin(text: String) {
    Text(
        text = text,
        style = TitleStyle,
        color = colorScheme.secondary
    )
}

/**
 * Composable que muestra un título para una tarjeta.
 *
 * @param text Texto a mostrar en el título de la tarjeta.
 */
@Composable
fun TitleCardOdin(text: String) {
    Text(
        text = text,
        style = CardTitleStyle,
        modifier = Modifier.widthIn(max = 400.dp),
        overflow = TextOverflow.Ellipsis,
        maxLines = 2,
        color = colorScheme.secondary
    )
}

/**
 * Composable que muestra una descripción.
 *
 * @param text Texto a mostrar en la descripción.
 */
@Composable
fun DescriptionOdin(text: String) {
    Text(
        text = text,
        style = DescriptionStyle,
        modifier = Modifier.widthIn(max = 400.dp),
        overflow = TextOverflow.Ellipsis,
        maxLines = 2,
        color = colorScheme.secondary
    )
}

/**
 * Composable que muestra el nombre de usuario.
 *
 * @param text Texto a mostrar en el nombre de usuario.
 */
@Composable
fun UserNameOdin(text: String) {
    Text(
        text = text,
        style = UserNameStyle,
        modifier = Modifier.widthIn(max = 400.dp),
        overflow = TextOverflow.Ellipsis,
        maxLines = 2,
        color = colorScheme.secondary
    )
}

@Preview
@Composable
private fun TitleOdinPreview() {
    OdinTheme {
        TitleOdin("Hello World!!!")
    }
}
