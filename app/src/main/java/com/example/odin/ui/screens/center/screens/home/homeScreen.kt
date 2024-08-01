package com.example.odin.ui.screens.center.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.InputChip
import androidx.compose.material3.InputChipDefaults
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
import com.example.odin.R
import com.example.odin.ui.mods.cardPublication.CardPublication
import com.example.odin.ui.theme.OdinTheme

@Composable
fun HomeScreen() {
    OdinTheme {
        Screen()
    }
}

@Composable
@Preview
private fun HomeScreenPreview() {
    HomeScreen()
}

@Composable
private fun Screen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            TextFieldShared(
                "",
                R.string.search,
                painterResource = R.drawable.baseline_search_24
            ) {

            }
        }
        LazyRow(
            modifier = Modifier
                .padding(horizontal = 5.dp)
                .fillMaxWidth()
        ) {
            item {
                InputChip(
                    selected = true,
                    onClick = {
                    },
                    label = { Text(text = "🎓Aprendizaje") },
                    colors = InputChipDefaults.inputChipColors(
                        containerColor = colorScheme.primary,
                        labelColor = colorScheme.secondary,
                        leadingIconColor = colorScheme.secondary
                    )
                )
            }
        }
        LazyColumn(
            modifier = Modifier
                .padding(horizontal = 5.dp)
                .fillMaxSize()
        ) {
            item {
                CardPublication()
                Spacer(modifier = Modifier.size(10.dp))
                CardPublication()
                Spacer(modifier = Modifier.size(10.dp))
                CardPublication()
                Spacer(modifier = Modifier.size(10.dp))
                CardPublication()
                Spacer(modifier = Modifier.size(10.dp))
                CardPublication()
                Spacer(modifier = Modifier.size(10.dp))
                CardPublication()
                Spacer(modifier = Modifier.size(10.dp))
                CardPublication()
                Spacer(modifier = Modifier.size(10.dp))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TextFieldShared(
    value: String,
    stringResource: Int,
    width: Dp = 350.dp,
    height: Dp = 60.dp,
    painterResource: Int,
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
        modifier = Modifier
            .size(width = width, height = height),
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