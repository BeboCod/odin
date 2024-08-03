package com.example.odin.ui.screens.register

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import com.example.odin.R
import com.example.odin.ui.mods.ButtonOdin
import com.example.odin.ui.mods.LogoOverride
import com.example.odin.ui.mods.TextFieldCustom
import com.example.odin.ui.mods.TextFieldPasswordCustom
import com.example.odin.ui.mods.TitleOdin
import com.example.odin.ui.theme.OdinTheme

@Composable
fun RegisterScreen() = OdinTheme { RegisterScreenContent() }

@Composable
@Preview
private fun RegisterScreenPreview() {
    RegisterScreen()
}

@Composable
private fun RegisterScreenContent() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        item {
            RegisterForm()
        }
    }
}

@Composable
private fun RegisterForm() {
    LogoOverride()
    TitleOdin(text = stringResource(id = R.string.register))
    Spacer(modifier = Modifier.size(20.dp))

    // Campo para el correo electrónico
    TextFieldCustom(
        value = "",
        placeholderRes = R.string.email,
        keyboardType = KeyboardType.Email,
        leadingIconRes = R.drawable.baseline_email_24,
        onTextFieldChanged = {  }
    )
    Spacer(modifier = Modifier.size(20.dp))

    // Campo para la contraseña
    TextFieldPasswordCustom(
        value = "",
        placeholderRes = R.string.password,
        onTextFieldChanged = {  }
    )
    Spacer(modifier = Modifier.size(20.dp))

    // Campo para confirmar la contraseña
    TextFieldPasswordCustom(
        value = "",
        placeholderRes = R.string.confirm_password,
        onTextFieldChanged = {  }
    )
    Spacer(modifier = Modifier.size(20.dp))

    // Botón para registrar
    ButtonOdin(
        text = stringResource(id = R.string.register),
        modifier = Modifier.size(200.dp, 50.dp),
        callback = {  }
    )
}