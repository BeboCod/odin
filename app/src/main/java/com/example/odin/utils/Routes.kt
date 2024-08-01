package com.example.odin.utils

sealed class Routes(val route: String) {
    data object Login: Routes("Login")
    data object Home: Routes("Home")
    data object Profile: Routes("Profile")
    data object Settings: Routes("Settings")
    data object Register: Routes("Register")
    data object Start: Routes("Start")
}