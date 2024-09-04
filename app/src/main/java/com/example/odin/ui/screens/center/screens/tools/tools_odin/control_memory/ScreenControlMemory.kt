package com.example.odin.ui.screens.center.screens.tools.tools_odin.control_memory

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.odin.R
import com.example.odin.ui.mods.DescriptionOdin
import com.example.odin.ui.mods.TextFieldCustom
import com.example.odin.ui.mods.TextFieldCustomIconButton
import com.example.odin.ui.mods.TitleCardOdin
import com.example.odin.ui.theme.OdinTheme

@Composable
fun ScreenControlMemory() = OdinTheme { Screen() }

@Composable
private fun Screen() {
    val context = LocalContext.current
    val viewModel = ControlMemoryViewModel(context)
    val uiState = viewModel.uiState.collectAsState()
    val helpButtonClicked = remember { mutableStateOf(false) }
    ContentScreen(viewModel, uiState)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        contentAlignment = Alignment.BottomEnd
    ) {
        FloatingActionButton(
            onClick = { helpButtonClicked.value = true },
            containerColor = colorScheme.primary,
            modifier = Modifier.padding(15.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_help_24),
                contentDescription = null,
                tint = colorScheme.onBackground,
                modifier = Modifier.size(30.dp),
            )
        }
        if (helpButtonClicked.value) {
            HelpButton {
                helpButtonClicked.value = it
            }
        }
    }
}

@Composable
private fun HelpButton(onClick: (Boolean) -> Unit) {
    Dialog(
        onDismissRequest = { onClick(false) },
        content = {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = colorScheme.background
                ), modifier = Modifier.wrapContentSize()
            ) {
                Column(
                    modifier = Modifier.padding(15.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    TitleCardOdin(text = stringResource(id = R.string.help))
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(id = R.drawable.baseline_add_24) {}
                        DescriptionOdin(text = stringResource(id = R.string.add))
                    }
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(id = R.drawable.baseline_remove_24) {}
                        DescriptionOdin(text = stringResource(id = R.string.delete))
                    }
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Icon(id = R.drawable.edit_24dp_e8eaed_fill0_wght400_grad0_opsz24) {}
                        DescriptionOdin(text = stringResource(id = R.string.modifier))
                    }
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(id = R.drawable.baseline_delete_24) {}
                        DescriptionOdin(text = stringResource(id = R.string.clear))
                    }
                }
            }
        }
    )
}

@Composable
private fun ContentScreen(
    viewModel: ControlMemoryViewModel,
    uiState: State<ControlMemoryViewModel.UiState>
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = colorScheme.onBackground
        ),
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 5.dp)
            .padding(bottom = 19.dp),
    ) {
        LazyColumn(
            modifier = Modifier.wrapContentSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                ControlMemory(viewModel = viewModel, uiState)
                ProgressBar(uiState)
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(600.dp)
                        .padding(10.dp)
                        .background(colorScheme.background),
                    verticalArrangement = Arrangement.Bottom,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Memory(viewModel = viewModel, uiState)
                }
            }
        }
    }
}

@Composable
private fun ProgressBar(uiState: State<ControlMemoryViewModel.UiState>) {
    val targetProgress =
        if (uiState.value.size == 0) 0f else uiState.value.complete.toFloat() / uiState.value.size.toFloat()

    val animatedProgress by animateFloatAsState(
        targetValue = targetProgress,
        animationSpec = tween(durationMillis = 1000),
        label = ""
    )

    Box(
        modifier = Modifier
            .wrapContentHeight()
            .clip(RoundedCornerShape(20.dp))
            .background(colorScheme.background),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(6.dp)
        ) {
            LinearProgressIndicator(
                progress = {
                    animatedProgress
                },
                modifier = Modifier
                    .width(250.dp)
                    .padding(10.dp),
            )
            Spacer(modifier = Modifier.padding(8.dp))
            Text(
                text = "${uiState.value.complete}/${uiState.value.size}",
                fontWeight = FontWeight.Bold,
                color = colorScheme.secondary,
            )
            Spacer(modifier = Modifier.padding(8.dp))
        }
    }
}

@Composable
private fun ControlMemory(
    viewModel: ControlMemoryViewModel,
    uiState: State<ControlMemoryViewModel.UiState>
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(10.dp)
    ) {
        TextFieldsInputs(viewModel = viewModel, uiState = uiState)
        Spacer(modifier = Modifier.padding(5.dp))
        TextFieldCustomIconButton(
            value = uiState.value.size.toString(),
            placeholderRes = R.string.size,
            width = 120.dp,
            keyboardType = KeyboardType.Number,
            label = "Bits",
            leadingIconRes = R.drawable.baseline_check_24,
            onTextFieldChanged = {
                viewModel.onSizeChanged(it)
            },
            onIconButtonClicked = {
                viewModel.onClickChanged()
            })
        Spacer(modifier = Modifier.padding(5.dp))
        Control(viewModel = viewModel, uiState = uiState)
    }
}

@Composable
private fun TextFieldsInputs(
    viewModel: ControlMemoryViewModel,
    uiState: State<ControlMemoryViewModel.UiState>
) {
    TextFieldCustom(
        value = uiState.value.input,
        placeholderRes = R.string.input,
        keyboardType = KeyboardType.Text,
        leadingIconRes = R.drawable.baseline_short_text_24
    ) {
        viewModel.onInputChanged(it)
    }
    Spacer(modifier = Modifier.padding(5.dp))
    TextFieldCustom(
        value = uiState.value.inputCustom,
        placeholderRes = R.string.input_custom,
        keyboardType = KeyboardType.Text,
        leadingIconRes = R.drawable.edit_24dp_e8eaed_fill0_wght400_grad0_opsz24
    ) {
        viewModel.onInputCustomChanged(it)
    }
    Spacer(modifier = Modifier.padding(5.dp))
    TextFieldCustom(
        value = uiState.value.inputSearch,
        placeholderRes = R.string.search,
        keyboardType = KeyboardType.Text,
        leadingIconRes = R.drawable.baseline_search_24
    ) {
        viewModel.onInputSearchChanged(it)
    }
}

@Composable
private fun Control(
    viewModel: ControlMemoryViewModel,
    uiState: State<ControlMemoryViewModel.UiState>
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(
                colorScheme.background
            ),
        contentAlignment = Alignment.Center,
    ) {
        Row(
            modifier = Modifier
                .wrapContentSize()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(id = R.drawable.baseline_add_circle_24) {
                viewModel.add(uiState.value.input)
            }
            Icon(id = R.drawable.baseline_remove_circle_24) {
                viewModel.delete(uiState.value.input)
            }
            Icon(id = R.drawable.edit_24dp_e8eaed_fill0_wght400_grad0_opsz24) {
                viewModel.replace(uiState.value.input, uiState.value.inputCustom)
            }
            Icon(id = R.drawable.baseline_search_24) {
                viewModel.get(uiState.value.inputSearch)
            }
            Icon(id = R.drawable.baseline_delete_24, color = colorScheme.error) {
                viewModel.clear()
            }
        }
    }
}

@Composable
private fun Memory(
    viewModel: ControlMemoryViewModel,
    uiState: State<ControlMemoryViewModel.UiState>
) {
    val matrix = viewModel.matrixState.collectAsState().value
    val numColumns = 4
    LazyVerticalGrid(
        columns = GridCells.Fixed(numColumns),
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(matrix.size) { index ->
            val cell = matrix.values.elementAt(index)
            val direction = matrix.keys.elementAt(index)

            val backgroundColor = when {
                uiState.value.marker.contains(direction.toString()) -> Color.Green
                cell.first != ' ' -> colorScheme.primary
                else -> colorScheme.onBackground
            }

            ModulesMemory(
                pair = cell,
                direction = direction,
                viewModel = viewModel,
                backgroundColor = backgroundColor
            )
        }
    }
}


@Composable
private fun ModulesMemory(
    pair: Pair<Char, String>,
    direction: Long,
    viewModel: ControlMemoryViewModel,
    activity: Boolean = true,
    backgroundColor: Color = colorScheme.onBackground
) {
    var onClick by remember { mutableStateOf(false) }
    AnimatedVisibility(
        visible = onClick && pair.first != ' ' && activity,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        Dialog(
            onDismissRequest = { onClick = false },
            content = {
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = colorScheme.background
                    ),
                    modifier = Modifier
                        .wrapContentHeight()
                        .fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .padding(15.dp)
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        TitleCardOdin(text = stringResource(id = R.string.info))
                        DescriptionOdin(text = "Value: ${pair.first}")
                        DescriptionOdin(text = "Id: ${pair.second}")
                        DescriptionOdin(text = "Direction Memory: $direction")
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(id = R.drawable.baseline_delete_24, color = colorScheme.error) {
                                onClick = false
                                viewModel.deleteElement(pair.second.split("-")[0])
                            }
                            Icon(
                                id = R.drawable.baseline_content_copy_24,
                                color = colorScheme.secondary
                            ) {
                                viewModel.copyToClipboard(direction.toString())
                            }
                        }
                    }
                }
            }
        )
    }
    Card(
        onClick = {
            onClick = true
        }, colors = CardDefaults.cardColors(
            containerColor = backgroundColor
        ), modifier = Modifier
            .size(50.dp)
            .padding(5.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            Text(
                text = "${pair.first}",
                style = TextStyle(
                    color = colorScheme.secondary, fontWeight = FontWeight.Bold, fontSize = 20.sp
                ),
            )
        }
    }
}

@Composable
private fun Icon(@DrawableRes id: Int, color: Color = colorScheme.secondary, onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Icon(
            painter = painterResource(id = id),
            contentDescription = null,
            modifier = Modifier.size(24.dp),
            tint = color
        )
    }
}

@Composable
@Preview
private fun Preview() = ScreenControlMemory()