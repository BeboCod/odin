package com.example.odin.ui.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.odin.R
import com.example.odin.ui.mods.ButtonOdin
import com.example.odin.ui.mods.LogoOverride
import com.example.odin.ui.mods.TextFieldCustom
import com.example.odin.ui.mods.TextFieldPasswordCustom
import com.example.odin.ui.mods.TitleOdin
import com.example.odin.ui.theme.OdinTheme
import com.example.odin.utils.Routes

@Composable
fun LoginScreen(navController: NavController) {
    OdinTheme {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(colorScheme.background)
                .padding(10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Screen(navController)
            }
        }
    }
}

@Composable
@Preview
private fun LoginScreenPreview() {
    LoginScreen(rememberNavController())
}

@Composable
private fun Screen(navController: NavController) {
    LogoOverride()
    TitleOdin(text = stringResource(id = R.string.login))
    Spacer(modifier = Modifier.size(20.dp))
    TextFieldCustom(
        value = "",
        stringResource = R.string.email,
        keyboardType = KeyboardType.Email,
        painterResource = R.drawable.baseline_email_24
    ) {
    }
    Spacer(modifier = Modifier.size(20.dp))
    TextFieldPasswordCustom(value = "", stringResource = R.string.password) {

    }
    Spacer(modifier = Modifier.size(20.dp))
    ButtonOdin(
        text = stringResource(id = R.string.login),
        modifier = Modifier.size(200.dp, 50.dp)
    ) {

    }
    Spacer(modifier = Modifier.size(20.dp))
    ButtonOdin(
        text = stringResource(id = R.string.register),
        modifier = Modifier.size(200.dp, 50.dp)
    ) {
        if (it) {
            navController.navigate(Routes.Register.route)
        }
    }
}
