package com.example.odin.ui.mods

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.odin.R

@Composable
fun Show(
    containerColor: Color,
    icon: Int,
    Title: String,
    MSG: String,
    open: (Boolean) -> Unit
) {
    AlertDialog(
        onDismissRequest = { open(false) },
        confirmButton = {
            Button(
                onClick = { open(false) },
                colors = ButtonDefaults.buttonColors(containerColor = Color.White)
            ) {
                Text(text = "Ok", fontWeight = FontWeight.Bold, color = containerColor)
            }
        },
        title = {
            Row (
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = Title, fontWeight = FontWeight.Bold, fontSize = 20.sp)
            }
        },
        text = {
            Row (
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = MSG, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
            }
        },
        icon = {
            Icon(painter = painterResource(id = icon), contentDescription = null)
        },
        containerColor = containerColor,
        textContentColor = Color.White,
        titleContentColor = Color.White,
        iconContentColor = Color.White,
    )
}

@Composable
@Preview
private fun preview() {
    Show(containerColor = MaterialTheme.colorScheme.error, icon = R.drawable.baseline_warning_amber_24, Title = "Error", MSG = "Error") {

    }
}