package com.example.odin.utils

/**
 * Definición de rutas de navegación en la aplicación.
 * Usamos una clase sellada para asegurar que solo las rutas definidas aquí sean utilizadas.
 */

sealed class Routes(val route: String) {
    data object Login: Routes("Login")
    data object Home: Routes("Home")
    data object Tools: Routes("Tools")
    data object Register: Routes("Register")
    data object Start: Routes("Start")
    data object Center: Routes("Center")
    data object Community: Routes("Community")
    data object Profile: Routes("Profile")
}