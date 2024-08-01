package com.example.odin.ui.mods

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.odin.R

@Composable
fun TextFieldCustom(
    value: String,
    stringResource: Int,
    keyboardType: KeyboardType,
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
                    fontSize = 14.sp,
                    color = colorScheme.primary,
                    shadow = Shadow(
                        color = colorScheme.secondary,
                        offset = Offset(0.0f, 0.0f),
                        blurRadius = 5f
                    )
                ),
            )
        },
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        singleLine = true,
        maxLines = 1,
        modifier = Modifier
            .size(width = width, height = height),
        colors = TextFieldDefaults.colors(
            focusedTextColor = colorScheme.primary,
            unfocusedTextColor = colorScheme.secondary,
            unfocusedContainerColor = Color.Transparent,
            focusedContainerColor = Color.Transparent,
            cursorColor = colorScheme.primary,
            focusedLeadingIconColor = colorScheme.secondary,
            unfocusedLeadingIconColor = colorScheme.primary,
            unfocusedPlaceholderColor = colorScheme.secondary,
            focusedPlaceholderColor = colorScheme.primary,
            unfocusedLabelColor = colorScheme.primary,
            focusedLabelColor = colorScheme.primary
        ),
        shape = RoundedCornerShape(20.dp),
        leadingIcon = {
            Icon(
                painter = painterResource(id = painterResource),
                contentDescription = null,
                modifier = Modifier.padding(end = 10.dp),
            )
        }
    )
}

@Composable
fun TextFieldPasswordCustom(
    value: String,
    stringResource: Int,
    width: Dp = 350.dp,
    height: Dp = 60.dp,
    onTextFieldChaged: (String) -> Unit,
) {
    val passwordVisibility = remember { mutableStateOf(false) }
    val icon = remember { mutableIntStateOf(R.drawable.baseline_remove_red_eye_24) }
    OutlinedTextField(
        value = value,
        onValueChange = { onTextFieldChaged(it) },
        placeholder = {
            Text(
                text = stringResource(id = stringResource),
                style = TextStyle(
                    fontSize = 14.sp,
                    color = colorScheme.primary,
                    shadow = Shadow(
                        color = colorScheme.secondary,
                        offset = Offset(0.0f, 0.0f),
                        blurRadius = 5f
                    )
                ),
            )
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        singleLine = true,
        maxLines = 1,
        modifier = Modifier
            .size(width = width, height = height),
        colors = TextFieldDefaults.colors(
            focusedTextColor = colorScheme.primary,
            unfocusedTextColor = colorScheme.secondary,
            unfocusedContainerColor = Color.Transparent,
            focusedContainerColor = Color.Transparent,
            cursorColor = colorScheme.primary,
            focusedLeadingIconColor = colorScheme.secondary,
            unfocusedLeadingIconColor = colorScheme.primary,
            unfocusedPlaceholderColor = colorScheme.secondary,
            focusedPlaceholderColor = colorScheme.primary,
            unfocusedLabelColor = colorScheme.primary,
            focusedLabelColor = colorScheme.primary
        ),
        shape = RoundedCornerShape(20.dp),
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Lock,
                contentDescription = null,
                modifier = Modifier.padding(end = 10.dp),
            )
        },
        trailingIcon = {
            IconButton(onClick = {
                passwordVisibility.value = !passwordVisibility.value
                icon.intValue = if (passwordVisibility.value) {
                    R.drawable.baseline_panorama_fish_eye_24
                } else {
                    R.drawable.baseline_remove_red_eye_24
                }
            }) {
                Icon(
                    painter = painterResource(id = icon.intValue),
                    contentDescription = null,
                    tint = colorScheme.primary
                )
            }
        },
        visualTransformation = if (passwordVisibility.value) VisualTransformation.None else PasswordVisualTransformation()
    )
}