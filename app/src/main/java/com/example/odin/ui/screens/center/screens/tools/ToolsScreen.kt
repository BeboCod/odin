package com.example.odin.ui.screens.center.screens.tools

import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.odin.ui.theme.OdinTheme


@Composable
fun ToolsScreen() = OdinTheme { ScreenContent() }

@Composable
private fun ScreenContent() {

}

@Preview(
    showBackground = true,
    device = "spec:width=1792px,height=828px,dpi=420"
)
@Composable
fun ToolsScreenPreview() = ToolsScreen()