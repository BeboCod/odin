package com.example.odin.ui.screens.center.screens.profile

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalBottomSheetDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
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
import kotlinx.coroutines.launch

@Composable
fun ProfileScreen() = OdinTheme { ProfileContentScreen() }

@Composable
private fun ProfileContentScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        ProfileHeader()
        ProfileContent()
    }
}

@Composable
private fun ProfileHeader() {
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
        UserName(text = "Override")
        BarInfo()
    }
}

@Composable
fun UserName(text: String, @DrawableRes icon: Int = R.drawable.baseline_verified_24) {
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
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = Color(0xFF75FAFC)
        )
    }
}

@Composable
private fun BarInfo() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        InfoContent(stringRes = R.string.publications, counter = 212)
        InfoContent(stringRes = R.string.follows, counter = 423)
        InfoContent(stringRes = R.string.comments, counter = 137)
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
private fun ProfileContent(viewModel: ProfileViewModel = ProfileViewModel()) {
    val navigationController = rememberNavController()
    var route = remember { mutableStateOf("Publication") }
    CustomBottomAppBar(navigationController = navigationController, route)
    NavigationComponent(navController = navigationController)
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
                    isSelected = route.value == Routes.Publication.route,
                    stringRes = R.string.publications,
                    onClick = {
                        route.value = Routes.Publication.route
                        navigationController.navigate(Routes.Publication.route) {
                            popUpTo(0)
                        }
                    }
                )
                BottomAppBarIcon(
                    isSelected = route.value == Routes.Comments.route,
                    stringRes = R.string.comments,
                    onClick = {
                        route.value = Routes.Comments.route
                        navigationController.navigate(Routes.Comments.route) {
                            popUpTo(0)
                        }
                    }
                )
                BottomAppBarIcon(
                    isSelected = route.value == Routes.Settings.route,
                    stringRes = R.string.settings,
                    onClick = {
                        route.value = Routes.Settings.route
                        navigationController.navigate(Routes.Settings.route) {
                            popUpTo(0)
                        }
                    }
                )
            }
        }
    }
}

@Composable
private fun NavigationComponent(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        NavHost(
            navController = navController,
            startDestination = Routes.Publication.route,
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
            composable(Routes.Publication.route) {
                ProfilePublicationsScreen()
            }
            composable(Routes.Comments.route) {
                ProfileCommentsScreen()
            }
            composable(Routes.Settings.route) {
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
    LazyColumn(
        modifier = Modifier
            .padding(horizontal = 15.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            TitleOdin(text = "Publicaciones")
        }
    }
}

@Composable
private fun ProfileCommentsScreen() {
    LazyColumn(
        modifier = Modifier
            .padding(horizontal = 15.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            TitleOdin(text = "Comentarios")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileSettingsScreen() {
    val coroutineScope = rememberCoroutineScope()
    val bottomSheetState = rememberModalBottomSheetState()
    val selectedOption = remember { mutableStateOf("") }

    if (bottomSheetState.isVisible){
        ModalBottomSheet(
            sheetState = bottomSheetState,
            onDismissRequest = { coroutineScope.launch { bottomSheetState.hide() } },
            containerColor = colorScheme.onBackground,
            scrimColor = colorScheme.scrim
        ) {
            when (selectedOption.value) {
                "AccountSettings" -> SettingsAccountScreen()
                "Security" -> SettingsSecurityScreen()
                "SupportPrivacy" -> SettingsSupportPrivacyScreen()
            }
        }

    }

    LazyColumn(
        modifier = Modifier
            .padding(horizontal = 15.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Settings(
                stringRes = R.string.account_settings,
                icon = R.drawable.baseline_admin_panel_settings_24,
                onClick = {
                    selectedOption.value = "AccountSettings"
                    coroutineScope.launch { bottomSheetState.show() }
                }
            )
            Settings(
                stringRes = R.string.security,
                icon = R.drawable.baseline_security_24,
                onClick = {
                    selectedOption.value = "Security"
                    coroutineScope.launch { bottomSheetState.show() }
                }
            )
            Settings(
                stringRes = R.string.support_privacy,
                icon = R.drawable.baseline_warning_amber_24,
                onClick = {
                    selectedOption.value = "SupportPrivacy"
                    coroutineScope.launch { bottomSheetState.show() }
                }
            )
            Settings(
                stringRes = R.string.logout,
                icon = R.drawable.baseline_logout_24,
                onClick = {
                },
                color = colorScheme.error
            )
        }
    }
}

@Composable
private fun Settings(
    @StringRes stringRes: Int,
    @DrawableRes icon: Int,
    onClick: () -> Unit,
    color: Color = colorScheme.onBackground,
) {
    Card(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(color)
                .padding(horizontal = 15.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Absolute.SpaceBetween
        ) {
            Text(
                text = stringResource(id = stringRes),
                style = TextStyle(
                    color = colorScheme.secondary,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                )
            )
            Icon(
                painter = painterResource(id = icon),
                contentDescription = null,
                tint = colorScheme.secondary,
                modifier = Modifier.size(40.dp)
            )
        }
    }
    Spacer(modifier = Modifier.height(20.dp))
}

@Composable
private fun SettingsAccountScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TitleOdin(text = "Cuenta")
    }
}

@Composable
private fun SettingsSecurityScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TitleOdin(text = "Seguridad")
    }
}

@Composable
private fun SettingsSupportPrivacyScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TitleOdin(text = "Soporte y privacidad")
    }
}

@Preview(
    showBackground = true,
    device = "spec:width=1792px,height=828px,dpi=440,orientation=portrait"
)
@Composable
private fun ProfileScreenPreview() {
    OdinTheme {
        ProfileScreen()
    }
}