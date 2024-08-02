package com.example.odin.ui.mods

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
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
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Absolute.Right
    ) {
        InputChip(
            selected = false,
            onClick = {
                viewModel.onClickComment()
            },
            label = { Text(text = stringResource(id = R.string.Want_help), fontSize = 13.sp) },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_mode_comment_24),
                    contentDescription = null
                )
            },
            colors = InputChipDefaults.inputChipColors(
                containerColor = colorScheme.background,
                labelColor = colorScheme.secondary,
                leadingIconColor = colorScheme.secondary,
            )
        )
        Spacer(modifier = Modifier.size(10.dp))
        InputChip(
            selected = true,
            onClick = {
                viewModel.onClickChip()
            },
            label = { DescriptionOdin(text = title) },
            colors = InputChipDefaults.inputChipColors(
                containerColor = colorScheme.primary,
                labelColor = colorScheme.secondary,
                leadingIconColor = colorScheme.secondary
            )
        )
    }
}

@Composable
fun ChipTheme(title: String) {
    InputChip(
        selected = true,
        onClick = {
        },
        label = { Text(text = "$title") },
        colors = InputChipDefaults.inputChipColors(
            containerColor = colorScheme.primary,
            labelColor = colorScheme.secondary,
            leadingIconColor = colorScheme.secondary
        )
    )
}