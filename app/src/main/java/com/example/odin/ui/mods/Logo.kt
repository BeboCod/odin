package com.example.odin.ui.mods

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.odin.R

/**
 * Composable que muestra el logo de la aplicación con el color primario de la paleta.
 *
 * @param modifier Opcional: permite personalizar el tamaño y otros aspectos del icono.
 */
@Composable
fun LogoOverride(modifier: Modifier = Modifier) {
    Icon(
        painter = painterResource(id = R.drawable.logo_override),
        contentDescription = "Logo de la aplicación",
        tint = colorScheme.primary,
        modifier = modifier
    )
}