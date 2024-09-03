package com.example.odin.ui.screens.center.screens.tools.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.odin.R
import com.example.odin.ui.mods.TitleCardOdin
import com.example.odin.ui.screens.center.screens.tools.ToolsScreen
import com.example.odin.ui.screens.center.screens.tools.ToolsViewModel
import com.example.odin.ui.theme.OdinTheme

@Composable
fun ScreenTools(content: @Composable () -> Unit) = Screen(content)

@Composable
private fun Screen(content: @Composable () -> Unit) {
    OdinTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colorScheme.background)
                .padding(horizontal = 10.dp)
                .padding(bottom = 20.dp, top = 10.dp)
        ) {
            content()
        }

    }
}

@Composable
private fun Header(title: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(10.dp)
            .background(colorScheme.background),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_arrow_back_ios_24),
                contentDescription = null,
                tint = colorScheme.primary,
                modifier = Modifier.size(80.dp)
            )
        }
        TitleCardOdin(text = title)
    }
}

@Composable
@Preview
private fun ScreenTool() = Screen(content = { ContentPreview() })

@Composable
private fun ContentPreview() {

}