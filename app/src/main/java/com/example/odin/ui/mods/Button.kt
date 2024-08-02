package com.example.odin.ui.mods

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.odin.ui.theme.OdinTheme

@Composable
fun ButtonOdin(text: String, modifier: Modifier, callback: (Boolean) -> Unit) {
    // Creación de un botón con sombra personalizada y colores
    Button(
        onClick = { callback(true) }, // Acción a realizar cuando se hace clic en el botón
        modifier = modifier.shadow(
            elevation = 30.dp, // Elevación de la sombra
            spotColor = colorScheme.primary, // Color principal de la sombra
            ambientColor = colorScheme.primary // Color ambiental de la sombra
        ),
        colors = buttonColors(
            contentColor = colorScheme.secondary, // Color del contenido del botón
            containerColor = colorScheme.primary // Color del fondo del botón
        ),
    ) {
        // Texto del botón con estilo personalizado
        Text(text = text, fontWeight = FontWeight.Bold, fontSize = 20.sp)
    }
}

@Preview
@Composable
fun ButtonOdinPreview() {
    OdinTheme {
        ButtonOdin("Login", Modifier.size(200.dp, 50.dp)) {
        }
    }
}