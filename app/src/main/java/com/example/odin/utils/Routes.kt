package com.example.odin.utils

sealed class Routes(val route: String) {
    data object Login: Routes("Login")
    data object Home: Routes("Home")
    data object Tools: Routes("Tools")
    data object Register: Routes("Register")
    data object Start: Routes("Start")
    data object Center: Routes("Center")
    data object Follows: Routes("Follows")
    data object Profile: Routes("Profile")
}