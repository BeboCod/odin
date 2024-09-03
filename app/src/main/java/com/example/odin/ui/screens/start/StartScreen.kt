package com.example.odin.ui.screens.start

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.odin.R
import com.example.odin.ui.mods.ButtonOdin
import com.example.odin.ui.mods.LogoOverride
import com.example.odin.ui.mods.TitleOdin
import com.example.odin.ui.theme.OdinTheme
import com.example.odin.utils.Routes

@Composable
fun StartScreen(navController: NavController, context: Context) =
    OdinTheme { StartScreenContent(navController, context) }

@Composable
@Preview
private fun StartScreenPreview() {
    StartScreen(rememberNavController(), LocalContext.current)
}

@Composable
private fun StartScreenContent(navController: NavController, context: Context) {
    val viewModel = StartViewModel(context)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorScheme.background)
            .padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        StartContent(navController, viewModel)
    }
}

@Composable
private fun StartContent(navController: NavController, viewModel: StartViewModel) {
    LogoOverride()
    TitleOdin(text = stringResource(id = R.string.app_name))
    Spacer(modifier = Modifier.size(20.dp))
    ButtonOdin(
        text = stringResource(id = R.string.start),
        modifier = Modifier.size(200.dp, 50.dp),
        callback = {
//            viewModel.dataExists {
//                val route = if (it) Routes.Center.route else Routes.Login.route
//                navController.navigate(route)
//            }
            navController.navigate(Routes.Center.route)
        }
    )
}