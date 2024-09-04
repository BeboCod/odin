package com.example.odin.ui.screens.center.screens.home

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.odin.R
import com.example.odin.ui.mods.ChipTheme
import com.example.odin.ui.mods.cardPublication.CardPublication
import com.example.odin.ui.theme.OdinTheme
import com.example.odin.utils.Routes

@Composable
fun HomeScreen(navController: NavController) = OdinTheme { Screen(navController) }

@Composable
@Preview(device = "spec:width=1334px,height=750px,dpi=440,orientation=portrait")
private fun HomeScreenPreview() {
    HomeScreen(rememberNavController())
}

@Composable
private fun Screen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 15.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SearchField(
            value = "",
            stringResourceId = R.string.search,
            painterResourceId = R.drawable.baseline_search_24,
            onTextChanged = {}
        )
        Spacer(modifier = Modifier.height(10.dp))
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            item {
                ChipTheme(title = "Aprendizaje")
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            item {
                CardPublication(
                    title = "Programacion Orientada a Objetos (POO)",
                    description = "Logica de POO explicada con minecraft",
                    sharedBy = "Override",
                    chipTheme = "🎓Aprendizaje",
                    likes = 69,
                    onClickCard = {
                        navController.navigate(Routes.Post.route)
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SearchField(
    value: String,
    @StringRes stringResourceId: Int,
    width: Dp = 350.dp,
    height: Dp = 60.dp,
    @DrawableRes painterResourceId: Int,
    onTextChanged: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = { onTextChanged(it) },
        placeholder = {
            Text(
                text = stringResource(id = stringResourceId),
                style = TextStyle(fontSize = 17.sp)
            )
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        singleLine = true,
        maxLines = 1,
        modifier = Modifier
            .size(width = width, height = height)
            .padding(5.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedTextColor = colorScheme.primary,
            unfocusedTextColor = colorScheme.secondary,
            cursorColor = colorScheme.primary,
            focusedLeadingIconColor = colorScheme.primary,
            unfocusedLeadingIconColor = colorScheme.secondary,
            unfocusedPlaceholderColor = colorScheme.secondary,
            focusedPlaceholderColor = colorScheme.primary,
            unfocusedLabelColor = colorScheme.primary,
            focusedLabelColor = colorScheme.primary,
            unfocusedBorderColor = colorScheme.secondary,
            focusedBorderColor = colorScheme.primary
        ),
        leadingIcon = {
            Icon(
                painter = painterResource(id = painterResourceId),
                contentDescription = null,
                modifier = Modifier.size(40.dp)
            )
        }
    )
}
