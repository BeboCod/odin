package com.example.odin.ui.screens.center.screens.home

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
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
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            TextFieldShared(
                value= "",
                stringResource = R.string.search,
                painterResource = R.drawable.baseline_search_24,
                onTextFieldChaged = {}
            )
        }
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            item {
            }
        }
        LazyColumn(
            modifier = Modifier
                .padding(horizontal = 15.dp)
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
private fun TextFieldShared(
    value: String,
    @StringRes stringResource: Int,
    width: Dp = 350.dp,
    height: Dp = 60.dp,
    @DrawableRes painterResource: Int,
    onTextFieldChaged: (String) -> Unit,
) {
    OutlinedTextField(
        value = value,
        onValueChange = { onTextFieldChaged(it) },
        placeholder = {
            Text(
                text = stringResource(id = stringResource),
                style = TextStyle(
                    fontSize = 17.sp,
                ),
            )
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        singleLine = true,
        maxLines = 1,
        modifier = Modifier.size(width = width, height = height),
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
            focusedBorderColor = colorScheme.primary,
        ),
        leadingIcon = {
            Icon(
                painter = painterResource(id = painterResource),
                contentDescription = null,
                modifier = Modifier
                    .padding(end = 10.dp)
                    .size(40.dp)
                    .padding(5.dp),
            )
        },
    )
}