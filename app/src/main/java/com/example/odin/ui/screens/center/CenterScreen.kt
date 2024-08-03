package com.example.odin.ui.screens.center

import androidx.annotation.DrawableRes
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.odin.R
import com.example.odin.ui.screens.center.screens.home.HomeScreen
import com.example.odin.ui.screens.center.screens.profile.ProfileScreen
import com.example.odin.ui.screens.center.screens.tools.ToolsScreen
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
            CustomBottomAppBar(selected, navigationController)
        }
    ) { ing ->
        NavigationHost(navigationController, ing)
    }
}

@Composable
private fun CustomBottomAppBar(selected: MutableIntState, navigationController: NavController) {
    BottomAppBar(
        modifier = Modifier
            .offset(y = (-16).dp)
            .clip(RoundedCornerShape(50.dp))
            .background(Color(0xFF272a40))
            .fillMaxWidth()
            .height(100.dp),
        containerColor = Color.Transparent,
        contentColor = Color.Black
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            val modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
            BottomAppBarIcon(
                iconRes = R.drawable.baseline_architecture_24,
                isSelected = selected.intValue == R.drawable.baseline_architecture_24,
                modifier = modifier,
                onClick = {
                    selected.intValue = R.drawable.baseline_architecture_24
                    navigationController.navigate(Routes.Tools.route) {
                        popUpTo(0)
                    }
                }
            )
            BottomAppBarIcon(
                iconRes = R.drawable.baseline_groups_2_24,
                isSelected = selected.intValue == R.drawable.baseline_groups_2_24,
                modifier = modifier,
                onClick = {
                    selected.intValue = R.drawable.baseline_groups_2_24
                    navigationController.navigate(Routes.Community.route) {
                        popUpTo(0)
                    }
                }
            )
            BottomAppBarIcon(
                iconRes = R.drawable.baseline_home_filled_24,
                isSelected = selected.intValue == R.drawable.baseline_home_filled_24,
                modifier = modifier,
                onClick = {
                    selected.intValue = R.drawable.baseline_home_filled_24
                    navigationController.navigate(Routes.Home.route) {
                        popUpTo(0)
                    }
                }
            )
            BottomAppBarIcon(
                iconRes = R.drawable.baseline_account_circle_24,
                isSelected = selected.intValue == R.drawable.baseline_account_circle_24,
                modifier = modifier,
                onClick = {
                    selected.intValue = R.drawable.baseline_account_circle_24
                    navigationController.navigate(Routes.Profile.route) {
                        popUpTo(0)
                    }
                }
            )
        }
    }
}

@Composable
private fun BottomAppBarIcon(
    @DrawableRes iconRes: Int,
    isSelected: Boolean,
    modifier: Modifier,
    onClick: () -> Unit,
) {
    Box (
        modifier = Modifier
            .wrapContentWidth()
            .wrapContentHeight()
            .clip(CircleShape)
            .background(if (isSelected) colorScheme.primary else colorScheme.onBackground)
    ) {
        IconButton(
            onClick = onClick,
            modifier = modifier.padding(5.dp)
        ) {
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = null,
                modifier = Modifier.size(60.dp),
                tint = if (isSelected) colorScheme.onBackground else colorScheme.secondary
            )
        }
    }
}

@Composable
private fun NavigationHost(navController: NavHostController, innerPadding: PaddingValues) {
    NavHost(
        navController = navController,
        startDestination = Routes.Home.route,
        modifier = Modifier
            .padding(innerPadding)
            .background(colorScheme.background),
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
        composable(Routes.Home.route) {
            HomeScreen()
        }
        composable(Routes.Profile.route) {
            ProfileScreen()
        }
        composable(Routes.Tools.route) {
            ToolsScreen()
        }
        composable(Routes.Community.route) {
            HomeScreen()
        }
    }
}