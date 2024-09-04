package com.example.odin.ui.screens.center.screens.profile

import android.content.Context
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.odin.R
import com.example.odin.ui.mods.ImagesProfile
import com.example.odin.ui.mods.TitleOdin
import com.example.odin.ui.theme.OdinTheme
import com.example.odin.utils.RoutesPublication
import kotlinx.coroutines.launch

@Composable
fun ProfileScreen(context: Context) = OdinTheme { ProfileContentScreen(context) }

@Composable
private fun ProfileContentScreen(context: Context) {
    val viewModel = ProfileViewModel(context)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        ProfileHeader(viewModel)
        ProfileContent(viewModel)
    }
}

@Composable
private fun ProfileHeader(viewModel: ProfileViewModel) {
    var profileData by remember { mutableStateOf(ProfileViewModel.ProfileData()) }

    LaunchedEffect(viewModel) {
        profileData = viewModel.getProfileData() // Obtención de datos de perfil
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = 20.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ImagesProfile(painterResource(id = R.drawable.baseline_account_circle_24))
        Spacer(modifier = Modifier.size(10.dp))
        UserName(
            text = profileData.name,
            icon = if (profileData.isVerified) R.drawable.baseline_verified_24 else R.drawable.baseline_person_24
        )
        BarInfo(viewModel)
    }
}

@Composable
fun UserName(text: String, @DrawableRes icon: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            fontWeight = FontWeight.Medium,
            fontStyle = FontStyle.Normal,
            textAlign = TextAlign.Center,
            modifier = Modifier.widthIn(max = 400.dp),
            overflow = TextOverflow.Ellipsis,
            maxLines = 2,
            style = TextStyle(
                fontSize = 25.sp,
            ),
            color = colorScheme.secondary
        )
        Spacer(modifier = Modifier.size(10.dp))
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = Color(0xFF75FAFC)
        )
    }
}

@Composable
private fun BarInfo(viewModel: ProfileViewModel) {
    var postCount by remember { mutableStateOf(0) }
    var followersCount by remember { mutableStateOf(0) }
    var commentsCount by remember { mutableStateOf(0) }

    LaunchedEffect(viewModel) {
        postCount = viewModel.getProfileData().postCount
        followersCount = viewModel.getProfileData().followersCount
        commentsCount = viewModel.getProfileData().commentsCount
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        InfoContent(stringRes = R.string.publications, counter = postCount)
        InfoContent(stringRes = R.string.follows, counter = followersCount)
        InfoContent(stringRes = R.string.comments, counter = commentsCount)
    }
}

@Composable
private fun InfoContent(@StringRes stringRes: Int, counter: Int) {
    Column(
        modifier = Modifier
            .wrapContentWidth()
            .wrapContentHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = stringRes),
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal,
            textAlign = TextAlign.Center,
            modifier = Modifier.widthIn(max = 400.dp),
            overflow = TextOverflow.Ellipsis,
            maxLines = 2,
            style = TextStyle(
                fontSize = 20.sp,
            ),
            color = colorScheme.secondary
        )
        Text(
            text = "$counter",
            fontWeight = FontWeight.Medium,
            fontStyle = FontStyle.Normal,
            textAlign = TextAlign.Center,
            modifier = Modifier.widthIn(max = 400.dp),
            overflow = TextOverflow.Ellipsis,
            maxLines = 2,
            style = TextStyle(
                fontSize = 20.sp,
            ),
            color = colorScheme.secondary
        )
    }
}

@Composable
private fun ProfileContent(viewModel: ProfileViewModel) {
    val navigationController = rememberNavController()
    val route = remember { mutableStateOf("Publication") }
    CustomBottomAppBar(navigationController = navigationController, route)
    NavigationComponent(navController = navigationController, viewModel)
}

@Composable
private fun CustomBottomAppBar(
    navigationController: NavHostController,
    route: MutableState<String>,
) {
    BottomAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        containerColor = Color.Transparent,
        contentColor = Color.Black
    ) {
        LazyRow(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            item {
                BottomAppBarIcon(
                    isSelected = route.value == RoutesPublication.Publication.route,
                    stringRes = R.string.publications,
                    onClick = {
                        route.value = RoutesPublication.Publication.route
                        navigationController.navigate(RoutesPublication.Publication.route) {
                            popUpTo(0)
                        }
                    }
                )
                BottomAppBarIcon(
                    isSelected = route.value == RoutesPublication.Comments.route,
                    stringRes = R.string.comments,
                    onClick = {
                        route.value = RoutesPublication.Comments.route
                        navigationController.navigate(RoutesPublication.Comments.route) {
                            popUpTo(0)
                        }
                    }
                )
                BottomAppBarIcon(
                    isSelected = route.value == RoutesPublication.Settings.route,
                    stringRes = R.string.settings,
                    onClick = {
                        route.value = RoutesPublication.Settings.route
                        navigationController.navigate(RoutesPublication.Settings.route) {
                            popUpTo(0)
                        }
                    }
                )
            }
        }
    }
}

@Composable
private fun NavigationComponent(navController: NavHostController, viewModel: ProfileViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        NavHost(
            navController = navController,
            startDestination = RoutesPublication.Publication.route,
            enterTransition = {
                slideIn(
                    tween(500),
                    initialOffset = { fullSize -> IntOffset(0, fullSize.height) }
                )
            },
            exitTransition = {
                slideOut(
                    tween(500),
                    targetOffset = { fullSize -> IntOffset(0, -fullSize.height) }
                )
            }
        ) {
            composable(RoutesPublication.Publication.route) {
                ProfilePublicationsScreen()
            }
            composable(RoutesPublication.Comments.route) {
                ProfileCommentsScreen()
            }
            composable(RoutesPublication.Settings.route) {
                ProfileSettingsScreen()
            }
        }
    }
}

@Composable
private fun BottomAppBarIcon(
    onClick: () -> Unit,
    isSelected: Boolean,
    @StringRes stringRes: Int,
) {
    val color = if (isSelected) colorScheme.primary else colorScheme.background
    val textColor = if (isSelected) colorScheme.background else colorScheme.secondary
    Box(
        modifier = Modifier
            .wrapContentWidth()
            .wrapContentHeight()
            .clip(CircleShape)
            .background(color)
    ) {
        TextButton(onClick = onClick) {
            Text(
                text = stringResource(id = stringRes),
                style = TextStyle(
                    color = textColor,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                )
            )
        }
    }
}

@Composable
private fun ProfilePublicationsScreen() {
    // Implementación de la pantalla de publicaciones
}

@Composable
private fun ProfileCommentsScreen() {
    // Implementación de la pantalla de comentarios
}

@Composable
private fun ProfileSettingsScreen() {
    // Implementación de la pantalla de configuraciones
}
