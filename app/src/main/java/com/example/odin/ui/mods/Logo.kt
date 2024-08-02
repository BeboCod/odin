package com.example.odin.ui.mods

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.example.odin.R

@Composable
fun LogoOverride() {
    Icon(
        painter = painterResource(id = R.drawable.logo_override),
        contentDescription = null,
        tint = colorScheme.primary
    )
}