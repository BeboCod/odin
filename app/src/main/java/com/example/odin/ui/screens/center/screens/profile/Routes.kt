package com.example.odin.ui.screens.center.screens.profile

sealed class Routes(val route: String) {
    data object Publication: Routes("Publication")
    data object Comments: Routes("Comments")
    data object Settings: Routes("Settings")
}