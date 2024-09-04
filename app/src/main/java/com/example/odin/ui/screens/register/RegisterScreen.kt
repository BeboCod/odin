package com.example.odin.ui.screens.register

import Show
import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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
import io.ktor.client.HttpClient

@Composable
fun RegisterScreen(context: Context, navController: NavController, client: HttpClient) =
    OdinTheme { RegisterScreenContent(navController, context, client) }

@Composable
@Preview
private fun RegisterScreenPreview() {
    RegisterScreen(LocalContext.current, rememberNavController(), HttpClient())
}

@Composable
private fun RegisterScreenContent(navController: NavController, context: Context, client: HttpClient) {
    val viewModel = RegisterViewModel(context, client)
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        item {
            RegisterForm(viewModel, navController)
        }
    }
}

@Composable
private fun RegisterForm(viewModel: RegisterViewModel, navController: NavController) {
    Header()
    Content(viewModel)
    Footer(viewModel, navController)
}

@Composable
private fun Header() {
    LogoOverride()
    TitleOdin(text = stringResource(id = R.string.register))
    Spacer(modifier = Modifier.size(20.dp))
}

@Composable
private fun Content(viewModel: RegisterViewModel) {
    val state by viewModel.uiState.collectAsState()
    TextFieldCustom(
        value = state.username,
        placeholderRes = R.string.username,
        keyboardType = KeyboardType.Text,
        leadingIconRes = R.drawable.baseline_person_24,
        onTextFieldChanged = {
            viewModel.onValuesChanged(
                username = it,
                email = state.email,
                password = state.password,
                confirmPassword = state.confirmPassword
            )
        }
    )
    Spacer(modifier = Modifier.size(20.dp))
    TextFieldCustom(
        value = state.email,
        placeholderRes = R.string.email,
        keyboardType = KeyboardType.Email,
        leadingIconRes = R.drawable.baseline_email_24,
        onTextFieldChanged = {
            viewModel.onValuesChanged(
                username = state.username,
                email = it,
                password = state.password,
                confirmPassword = state.confirmPassword
            )
        }
    )
    Spacer(modifier = Modifier.size(20.dp))
    TextFieldPasswordCustom(
        value = state.password,
        placeholderRes = R.string.password,
        onTextFieldChanged = {
            viewModel.onValuesChanged(
                username = state.username,
                email = state.email,
                password = it,
                confirmPassword = state.confirmPassword
            )
        }
    )
    Spacer(modifier = Modifier.size(20.dp))
    TextFieldPasswordCustom(
        value = state.confirmPassword,
        placeholderRes = R.string.confirm_password,
        onTextFieldChanged = {
            viewModel.onValuesChanged(
                username = state.username,
                email = state.email,
                password = state.password,
                confirmPassword = it
            )
        }
    )
    Spacer(modifier = Modifier.size(20.dp))
}

@Composable
private fun Footer(viewModel: RegisterViewModel, navController: NavController) {
    val isSowingError by viewModel.uiState.collectAsState()
    if (isSowingError.isShowingError) {
        Show(
            containerColor = colorScheme.error,
            icon = R.drawable.baseline_warning_amber_24,
            title = stringResource(id = R.string.sign_failed),
            msg = stringResource(id = R.string.incorrect_register)
        ) {
            viewModel.onShowError()
        }
    }
    ButtonOdin(
        text = stringResource(id = R.string.register),
        modifier = Modifier.size(200.dp, 50.dp),
        callback = {
            if (viewModel.isNotEmpty()) {
                viewModel.register{ responseRegister ->
                    if (responseRegister.isValid) {
                        navController.navigate(Routes.Center.route)
                    } else {
                        print(responseRegister.errorMessage)
                    }
                }
            }
        }
    )
    Spacer(modifier = Modifier.size(20.dp))
}