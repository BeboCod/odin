package com.example.odin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntOffset
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.odin.ui.screens.center.CenterScreen
import com.example.odin.ui.screens.center.screens.home.HomeScreen
import com.example.odin.ui.screens.login.LoginScreen
import com.example.odin.ui.screens.register.RegisterScreen
import com.example.odin.ui.screens.start.startScreen
import com.example.odin.ui.theme.OdinTheme
import com.example.odin.utils.Routes

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        installSplashScreen()
        setContent {
            OdinTheme {
                val navController = rememberNavController()
                NavHost(
                    modifier = Modifier.background(colorScheme.background),
                    navController = navController,
                    startDestination = Routes.Start.route,
                    enterTransition = {
                        slideIn(
                            tween(500),
                            initialOffset = { fullSize -> IntOffset(fullSize.width, 0) }
                        )
                    },
                    exitTransition = {
                        slideOut(
                            tween(500),
                            targetOffset = { fullSize -> IntOffset(-fullSize.width, 0) }
                        )
                    }
                ) {
                    composable(Routes.Start.route) {
                        startScreen(navController)
                    }
                    composable(Routes.Login.route) {
                        LoginScreen(navController)
                    }
                    composable(Routes.Register.route) {
                        RegisterScreen()
                    }
                    composable(Routes.Center.route) {
                        CenterScreen()
                    }
                }
            }
        }
    }
}