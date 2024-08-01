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
    Button(
        onClick = { callback(true) },
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