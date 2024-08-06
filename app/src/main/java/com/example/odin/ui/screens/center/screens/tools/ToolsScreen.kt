package com.example.odin.ui.screens.center.screens.tools

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.odin.ui.theme.OdinTheme
import com.example.odin.utils.Constants


@Composable
fun ToolsScreen() = OdinTheme { ScreenContent() }

@Composable
private fun ScreenContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            verticalItemSpacing = 8.dp,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
                .background(colorScheme.background)
        ) {
            items(100) {
                val height = if (it % 2 == 0) 150.dp else 100.dp
                CardTools(
                    onClick = { /*TODO*/ },
                    string = Constants.materias[it],
                    modifier = Modifier
                        .height(height)
                )
            }
        }
    }
}

@Composable
private fun CardTools(
    onClick: () -> Unit,
    string: String,
    modifier: Modifier,
) {
    Card(
        onClick = onClick,
        modifier = modifier,
    ) {
        Box(
            modifier = Modifier
                .background(Color(0xFF676f9d))
                .fillMaxSize()
                .padding(10.dp),
            contentAlignment = Alignment.TopStart
        ) {
            Text(
                text = string,
                style = TextStyle(
                    color = colorScheme.secondary,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    )
            )
        }
    }
}

@Preview(
    showBackground = true,
)
@Composable
fun ToolsScreenPreview() = ToolsScreen()