package com.example.odin.ui.mods

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.odin.R
import com.example.odin.ui.theme.OdinTheme

@Composable
fun ShowInfoPost(
    publishedBy: String = "Override",
    date: String = "12/12/2023",
    chip: @Composable () -> Unit,
    callback: (Boolean) -> Unit
) {
    Dialog(
        onDismissRequest = {
            callback(false)
        },
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            shape = shapes.large,
            colors = cardColors(
                containerColor = colorScheme.onBackground
            )
        ) {
            Column(
                modifier =
                Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Absolute.Right,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TitleCardOdin(text = stringResource(id = R.string.post_information))
                    Spacer(modifier = Modifier.padding(10.dp))
                    IconButton(
                        onClick = {
                            callback(false)
                        }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_close_24),
                            contentDescription = null,
                            tint = colorScheme.secondary
                        )
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Absolute.Left,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    DescriptionOdin(text = "${stringResource(id = R.string.published_by)}: $publishedBy")
                }
                Spacer(modifier = Modifier.padding(10.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Absolute.Left,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    DescriptionOdin(text = "${stringResource(id = R.string.publication_date)}: $date")
                }
                Spacer(modifier = Modifier.padding(10.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Absolute.Left,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    DescriptionOdin(text = "${stringResource(id = R.string.tags)}:")
                    LazyRow (
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Absolute.Left,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        item {
                            chip()
                        }
                    }
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun ShowInfoPostPreview() {
    OdinTheme {
        ShowInfoPost(callback = {

        }, chip = {
            Spacer(modifier = Modifier.padding(5.dp))
            ChipTheme(title = "🎓Aprendizaje")
            Spacer(modifier = Modifier.padding(5.dp))
            ChipTheme(title = "✨IA")
            Spacer(modifier = Modifier.padding(5.dp))
            ChipTheme(title = "☕Java")
        })
    }
}