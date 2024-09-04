package com.example.odin.ui.mods

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

/**
 * Composable que representa un botón estilizado para la aplicación Odin.
 *
 * @param text El texto que se mostrará en el botón.
 * @param modifier Modificador opcional para personalizar el estilo y disposición del botón.
 * @param callback Función que se invoca cuando se hace clic en el botón.
 */
@Composable
fun ButtonOdin(
    text: String,
    modifier: Modifier = Modifier,
    callback: () -> Unit
) {
    Button(
        onClick = callback,
        modifier = modifier.shadow(
            elevation = 30.dp,
            spotColor = colorScheme.primary,
            ambientColor = colorScheme.primary
        ),
        colors = buttonColors(
            contentColor = colorScheme.secondary,
            containerColor = colorScheme.primary
        ),
    ) {
        Text(
            text = text,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
    }
}

/**
 * Vista previa del composable [ButtonOdin].
 */
@Preview(showBackground = true)
@Composable
fun ButtonOdinPreview() {
    OdinTheme {
        ButtonOdin(
            text = "Click Me",
            callback = { /* Acción de prueba */ }
        )
    }
}
