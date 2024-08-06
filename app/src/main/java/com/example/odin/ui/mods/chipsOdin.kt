package com.example.odin.ui.mods

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Icon
import androidx.compose.material3.InputChip
import androidx.compose.material3.InputChipDefaults
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.odin.R
import com.example.odin.ui.mods.cardPublication.CardPublicationViewModel

@Composable
fun ChipTheme(title: String, viewModel: CardPublicationViewModel) {
    // Fila que contiene los InputChips
    LazyRow(
        modifier = Modifier
            .wrapContentWidth() // Ocupa todo el ancho disponible
            .wrapContentHeight(), // Ajusta la altura al contenido
        verticalAlignment = Alignment.CenterVertically, // Centra verticalmente los elementos
        horizontalArrangement = Arrangement.Absolute.Right // Alinea los elementos a la derecha
    ) {
        item {
            // Primer InputChip para comentarios
            InputChip(
                selected = false, // No seleccionado por defecto
                onClick = { viewModel.onClickComment() }, // Acción al hacer clic
                label = {
                    Text(
                        text = stringResource(id = R.string.Want_help), // Texto del chip
                        fontSize = 13.sp // Tamaño de fuente
                    )
                },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_mode_comment_24), // Icono del chip
                        contentDescription = null // Descripción del contenido
                    )
                },
                colors = InputChipDefaults.inputChipColors(
                    containerColor = colorScheme.background, // Color de fondo del chip
                    labelColor = colorScheme.secondary, // Color del texto del chip
                    leadingIconColor = colorScheme.secondary // Color del icono del chip
                )
            )

            // Espacio entre los chips
            Spacer(modifier = Modifier.size(10.dp))

            // Segundo InputChip para el tema
            InputChip(
                selected = true, // Seleccionado por defecto
                onClick = { viewModel.onClickChip() }, // Acción al hacer clic
                label = { DescriptionOdin(text = title) }, // Descripción del chip
                colors = InputChipDefaults.inputChipColors(
                    containerColor = colorScheme.primary, // Color de fondo del chip
                    labelColor = colorScheme.secondary, // Color del texto del chip
                    leadingIconColor = colorScheme.secondary // Color del icono del chip
                )
            )
        }
    }
}

@Composable
fun ChipTheme(title: String) {
    InputChip(
        selected = true,
        onClick = {
        },
        label = { Text(text = title) },
        colors = InputChipDefaults.inputChipColors(
            containerColor = colorScheme.primary,
            labelColor = colorScheme.secondary,
            leadingIconColor = colorScheme.secondary
        )
    )
}