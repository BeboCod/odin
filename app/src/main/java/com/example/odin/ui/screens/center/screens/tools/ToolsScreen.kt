package com.example.odin.ui.screens.center.screens.tools

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.odin.ui.mods.LogoOverride
import com.example.odin.ui.theme.OdinTheme
import com.example.odin.utils.Constants
import com.example.odin.utils.Routes


@Composable
fun ToolsScreen(navController: NavController) = OdinTheme { ScreenContent(navController) }

@Composable
private fun ScreenContent(navController: NavController) {
    // Usar LazyVerticalGrid en lugar de LazyVerticalStaggeredGrid
    LazyVerticalGrid(
        columns = GridCells.Fixed(2), // Ajusta el número de columnas según sea necesario
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
            .padding(top = 20.dp)
            .background(colorScheme.background)
    ) {
        items(Constants.toolsIdString.size) {
            val height = if (it % 2 == 0) 150.dp else 100.dp
            CardTools(
                onClick = {
                    navController.navigate(Routes.ToolsScreen.createRoute(Constants.toolsIdString[it].second))
                },
                string = stringResource(id = Constants.toolsIdString[it].first),
                modifier = Modifier
                    .height(height)
            )
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
fun ToolsScreenPreview() = ToolsScreen(rememberNavController())