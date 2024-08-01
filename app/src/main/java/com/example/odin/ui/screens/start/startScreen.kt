package com.example.odin.ui.screens.start

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.odin.ui.mods.ButtonOdin
import com.example.odin.ui.mods.LogoOverride
import com.example.odin.ui.mods.TitleOdin
import com.example.odin.ui.theme.OdinTheme
import com.example.odin.utils.Routes

@Composable
fun startScreen(navController: NavController) {
    OdinTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colorScheme.background)
                .padding(10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Screen(navController)
        }
    }
}

@Composable
@Preview
private fun StartScreenPreview() {
    startScreen(rememberNavController())
}

@Composable
private fun Screen(navController: NavController) {
    LogoOverride()
    TitleOdin(text = "ODIN")
    Spacer(modifier = Modifier.size(20.dp))
    ButtonOdin(text = "Start", modifier = Modifier.size(200.dp, 50.dp)) {
        if (!it) {
            navController.navigate(Routes.Login.route)
        }else{
            navController.navigate(Routes.Center.route)
        }
    }
}