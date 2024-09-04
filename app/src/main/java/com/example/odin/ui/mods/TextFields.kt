package com.example.odin.ui.mods

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.odin.R

/**
 * Composable que muestra un campo de texto con un ícono de inicio.
 *
 * @param value Valor actual del campo de texto.
 * @param placeholderRes Recurso de cadena para el texto de marcador de posición.
 * @param keyboardType Tipo de teclado a mostrar.
 * @param width Ancho del campo de texto.
 * @param height Altura del campo de texto.
 * @param leadingIconRes Recurso de dibujo para el ícono de inicio.
 * @param onTextFieldChanged Función que se llama cuando cambia el valor del campo de texto.
 */
@Composable
fun TextFieldCustom(
    value: String,
    @StringRes placeholderRes: Int,
    keyboardType: KeyboardType,
    width: Dp = 350.dp,
    height: Dp = 60.dp,
    @DrawableRes leadingIconRes: Int,
    onTextFieldChanged: (String) -> Unit,
) {
    OutlinedTextField(
        value = value,
        onValueChange = { onTextFieldChanged(it) },
        placeholder = {
            Text(
                text = stringResource(id = placeholderRes),
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
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = keyboardType),
        singleLine = true,
        maxLines = 1,
        modifier = Modifier.size(width = width, height = height),
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
                painter = painterResource(id = leadingIconRes),
                contentDescription = null,
                modifier = Modifier.padding(end = 10.dp)
            )
        }
    )
}

/**
 * Composable que muestra un campo de texto para contraseñas con visibilidad controlable.
 *
 * @param value Valor actual del campo de texto.
 * @param placeholderRes Recurso de cadena para el texto de marcador de posición.
 * @param width Ancho del campo de texto.
 * @param height Altura del campo de texto.
 * @param onTextFieldChanged Función que se llama cuando cambia el valor del campo de texto.
 */
@Composable
fun TextFieldPasswordCustom(
    value: String,
    @StringRes placeholderRes: Int,
    width: Dp = 350.dp,
    height: Dp = 60.dp,
    onTextFieldChanged: (String) -> Unit,
) {
    val passwordVisibility = remember { mutableStateOf(false) }
    val icon = if (passwordVisibility.value) {
        R.drawable.baseline_remove_red_eye_24
    } else {
        R.drawable.baseline_panorama_fish_eye_24
    }

    OutlinedTextField(
        value = value,
        onValueChange = { onTextFieldChanged(it) },
        placeholder = {
            Text(
                text = stringResource(id = placeholderRes),
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
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
        singleLine = true,
        maxLines = 1,
        modifier = Modifier.size(width = width, height = height),
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
                modifier = Modifier.padding(end = 10.dp)
            )
        },
        trailingIcon = {
            IconButton(onClick = { passwordVisibility.value = !passwordVisibility.value }) {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = null,
                    tint = colorScheme.primary
                )
            }
        },
        visualTransformation = if (passwordVisibility.value) VisualTransformation.None else PasswordVisualTransformation()
    )
}

/**
 * Composable que muestra un campo de texto con un botón de icono al lado.
 *
 * @param value Valor actual del campo de texto.
 * @param placeholderRes Recurso de cadena para el texto de marcador de posición.
 * @param keyboardType Tipo de teclado a mostrar.
 * @param width Ancho del campo de texto.
 * @param height Altura del campo de texto.
 * @param label Texto de la etiqueta.
 * @param leadingIconRes Recurso de dibujo para el ícono de inicio.
 * @param onTextFieldChanged Función que se llama cuando cambia el valor del campo de texto.
 * @param onIconButtonClicked Función que se llama cuando se hace clic en el botón de ícono.
 */
@Composable
fun TextFieldCustomIconButton(
    value: String,
    @StringRes placeholderRes: Int,
    keyboardType: KeyboardType,
    width: Dp = 350.dp,
    height: Dp = 60.dp,
    label: String = "",
    @DrawableRes leadingIconRes: Int,
    onTextFieldChanged: (String) -> Unit,
    onIconButtonClicked: () -> Unit,
) {
    OutlinedTextField(
        value = value,
        label = { Text(text = label) },
        onValueChange = { onTextFieldChanged(it) },
        placeholder = {
            Text(
                text = stringResource(id = placeholderRes),
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
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = keyboardType),
        singleLine = true,
        maxLines = 1,
        modifier = Modifier.size(width = width, height = height),
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
            IconButton(onClick = { onIconButtonClicked() }) {
                Icon(
                    painter = painterResource(id = leadingIconRes),
                    contentDescription = null,
                    modifier = Modifier.padding(end = 10.dp)
                )
            }
        }
    )
}
