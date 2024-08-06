package com.example.odin.utils

import androidx.annotation.StringRes
import com.example.odin.R

/**
 * Definición de rutas de navegación en la aplicación.
 * Usamos una clase sellada para asegurar que solo las rutas definidas aquí sean utilizadas.
 */

sealed class Routes(val route: String, @StringRes val stringRes: Int) {
    data object Login: Routes("Login", R.string.login)
    data object Home: Routes("Home", R.string.home)
    data object Tools: Routes("Tools/{toolName}", R.string.tools){
        fun createRoute(toolName: String) = "Tools/$toolName"
    }
    data object Register: Routes("Register", R.string.register)
    data object Start: Routes("Start", R.string.start)
    data object Center: Routes("Center", R.string.center)
    data object Community: Routes("Community", R.string.community)
    data object Profile: Routes("Profile", R.string.profile)
    data object Publications: Routes("Publications/{idPublication}", R.string.publications){
        fun createRoute(idPublication: String) = "Publications/$idPublication"
    }
    data object Post: Routes("Post", R.string.post)
}

sealed class RoutesPublication(val route: String, stringRes: Int) {
    data object Publication: Routes("Publication", R.string.publications)
    data object Comments: Routes("Comments", R.string.comments)
    data object Settings: Routes("Settings", R.string.settings)
}