package com.example.odin.ui.screens.center

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.odin.R
import com.example.odin.ui.screens.center.screens.home.HomeScreen
import com.example.odin.ui.screens.center.screens.profile.ProfileScreen
import com.example.odin.utils.Routes

@Composable
fun CenterScreen() {
    val navigationController = rememberNavController()
    val selected = remember { mutableIntStateOf(R.drawable.baseline_home_filled_24) }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(colorScheme.background),
        bottomBar = {
            BottomAppBar(
                modifier = Modifier
                    .offset(y = (-16).dp)
                    .clip(RoundedCornerShape(50.dp))
                    .background(Color(0xFF272a40))
                    .fillMaxWidth()
                    .height(90.dp),
                containerColor = Color.Transparent,
                contentColor = Color.Black
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 16.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                ) {
                    IconButton(
                        onClick = {
                            selected.intValue = R.drawable.baseline_architecture_24
                            navigationController.navigate(Routes.Tools.route) {
                                popUpTo(0)
                            }
                        },
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_architecture_24),
                            contentDescription = null,
                            modifier = Modifier.size(60.dp),
                            tint = if (selected.intValue == R.drawable.baseline_architecture_24) colorScheme.primary else colorScheme.secondary
                        )
                    }
                    IconButton(
                        onClick = {
                            selected.intValue = R.drawable.baseline_person_24
                            navigationController.navigate(Routes.Follows.route) {
                                popUpTo(0)
                            }
                        },
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_person_24),
                            contentDescription = null,
                            modifier = Modifier.size(60.dp),
                            tint = if (selected.intValue == R.drawable.baseline_person_24) colorScheme.primary else colorScheme.secondary
                        )
                    }
                    IconButton(
                        onClick = {
                            selected.intValue = R.drawable.baseline_home_filled_24
                            navigationController.navigate(Routes.Home.route) {
                                popUpTo(0)
                            }
                        },
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_home_filled_24),
                            contentDescription = null,
                            modifier = Modifier.size(60.dp),
                            tint = if (selected.intValue == R.drawable.baseline_home_filled_24) colorScheme.primary else colorScheme.secondary
                        )
                    }
                    IconButton(
                        onClick = {
                            selected.intValue = R.drawable.baseline_account_circle_24
                            navigationController.navigate(Routes.Profile.route) {
                                popUpTo(0)
                            }
                        },
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_account_circle_24),
                            contentDescription = null,
                            modifier = Modifier.size(60.dp),
                            tint = if (selected.intValue == R.drawable.baseline_account_circle_24) colorScheme.primary else colorScheme.secondary
                        )
                    }
                }
            }
        }
    ) { ing ->
        NavHost(navController = navigationController, startDestination = Routes.Home.route, modifier = Modifier.padding(ing)) {
            composable(Routes.Home.route) {
                HomeScreen()
            }
            composable(Routes.Profile.route) {
                ProfileScreen()
            }
        }
    }
}