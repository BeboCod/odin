package com.example.odin.ui.mods

import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.odin.R
import com.example.odin.ui.theme.OdinTheme

@Composable
fun TitleOdin(text: String) {
    Text(
        text = text,
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Normal,
        textAlign = TextAlign.Center,
        style = TextStyle(
            fontSize = 50.sp,
            shadow = Shadow(
                color = colorScheme.primary,
                offset = Offset(0.0f, 0.0f),
                blurRadius = 5f
            ),
        ),
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

@Composable
fun TitleCardOdin(text: String) {
    Text(
        text = text,
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Normal,
        textAlign = TextAlign.Center,
        modifier = Modifier.widthIn(max = 400.dp),
        overflow = TextOverflow.Ellipsis,
        maxLines = 2,
        style = TextStyle(
            fontSize = 20.sp,
        ),
        color = colorScheme.secondary
    )
}

@Composable
fun DescriptionOdin(text: String) {
    Text(
        text = text,
        fontStyle = FontStyle.Normal,
        textAlign = TextAlign.Center,
        modifier = Modifier.widthIn(max = 400.dp),
        overflow = TextOverflow.Ellipsis,
        maxLines = 2,
        style = TextStyle(
            fontSize = 16.sp,
        ),
        color = colorScheme.secondary
    )
}

@Composable
fun UserNameOdin(text: String) {
    Text(
        text = text,
        fontWeight = FontWeight.Medium,
        fontStyle = FontStyle.Normal,
        textAlign = TextAlign.Center,
        modifier = Modifier.widthIn(max = 400.dp),
        overflow = TextOverflow.Ellipsis,
        maxLines = 2,
        style = TextStyle(
            fontSize = 25.sp,
        ),
        color = colorScheme.secondary
    )
}