package com.example.odin.ui.screens.login

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import com.example.odin.ui.mods.Show
import com.example.odin.ui.mods.TextFieldCustom
import com.example.odin.ui.mods.TextFieldPasswordCustom
import com.example.odin.ui.mods.TitleOdin
import com.example.odin.ui.theme.OdinTheme
import com.example.odin.utils.Routes
import io.ktor.client.HttpClient

@Composable
fun LoginScreen(navController: NavController, context: Context, client: HttpClient) =
    OdinTheme { LoginScreenContent(navController, context, client) }

@Composable
@Preview(
    showBackground = true,
    device = "spec:width=1792px,height=828px,dpi=440,orientation=portrait"
)
private fun LoginScreenPreview() {
    LoginScreen(rememberNavController(), LocalContext.current, HttpClient())
}

@Composable
private fun LoginScreenContent(navController: NavController, context: Context, client: HttpClient) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(colorScheme.background)
            .padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            LoginForm(navController, context, client)
        }
    }
}

@Composable
private fun LoginForm(navController: NavController, context: Context, client: HttpClient) {
    val viewModel = LoginViewModel(context = context, client = client)
    Header()
    Content(viewModel)
    Footer(navController, viewModel)
}

@Composable
private fun Header() {
    LogoOverride()
    TitleOdin(text = stringResource(id = R.string.login))
    Spacer(modifier = Modifier.size(20.dp))
}

@Composable
private fun Content(viewModel: LoginViewModel) {
    val state by viewModel.uiState.collectAsState()
    TextFieldCustom(
        value = state.email,
        placeholderRes = R.string.email,
        keyboardType = KeyboardType.Email,
        leadingIconRes = R.drawable.baseline_email_24,
        onTextFieldChanged = {
            viewModel.onEmailChanged(it)
        }
    )
    Spacer(modifier = Modifier.size(20.dp))
    TextFieldPasswordCustom(
        value = state.password,
        placeholderRes = R.string.password,
        onTextFieldChanged = {
            viewModel.onPasswordChanged(it)
        }
    )
    Spacer(modifier = Modifier.size(20.dp))
}

@Composable
private fun Footer(navController: NavController, viewModel: LoginViewModel) {
    val state by viewModel.uiState.collectAsState()
    if (state.isShowingError ){
        Show(
            containerColor = colorScheme.error,
            icon = R.drawable.baseline_warning_amber_24,
            Title = stringResource(
                id = R.string.failed_login
            ),
            MSG = stringResource(id = R.string.incorrect_login)
        ) {
            viewModel.onShowingError(false)
        }
    }
    ButtonOdin(
        text = stringResource(id = R.string.login),
        modifier = Modifier.size(200.dp, 50.dp),
        callback = {
            val response = viewModel.auth()
            if (response.isValid) {
                navController.navigate(Routes.Center.route)
            } else {
                viewModel.onShowingError(true)
                print("${response.data} ${response.errorMessage}")
            }
        }
    )
    Spacer(modifier = Modifier.size(20.dp))
    ButtonOdin(
        text = stringResource(id = R.string.register),
        modifier = Modifier.size(200.dp, 50.dp),
        callback = { navigateToRegister(navController) }
    )
}

private fun navigateToRegister(navController: NavController) =
    navController.navigate(Routes.Register.route)