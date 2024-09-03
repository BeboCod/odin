package com.example.odin.ui.screens.center.screens.tools.ToolsOdin.ControlMemory

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
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
import com.example.odin.ui.mods.TextFieldCustom
import com.example.odin.ui.mods.TextFieldCustomIconButton
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
            onClick = {
                helpButtonClicked.value = true
            },
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
        if (helpButtonClicked.value){
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
                    Text(
                        text = stringResource(id = R.string.help),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 20.sp,
                        color = colorScheme.secondary,
                    )
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_add_24),
                            contentDescription = null,
                            tint = colorScheme.secondary
                        )
                        Text(
                            text = stringResource(id = R.string.add),
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 20.sp,
                            color = colorScheme.secondary,
                        )
                    }
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_remove_24),
                            contentDescription = null,
                            tint = colorScheme.secondary
                        )
                        Text(
                            text = stringResource(id = R.string.delete),
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 20.sp,
                            color = colorScheme.secondary,
                        )
                    }
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.edit_24dp_e8eaed_fill0_wght400_grad0_opsz24),
                            contentDescription = null,
                            tint = colorScheme.secondary
                        )
                        Text(
                            text = stringResource(id = R.string.modifier),
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 20.sp,
                            color = colorScheme.secondary,
                        )
                    }
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_delete_24),
                            contentDescription = null,
                            tint = colorScheme.secondary
                        )
                        Text(
                            text = stringResource(id = R.string.clear),
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 20.sp,
                            color = colorScheme.secondary,
                        )
                    }
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Card(
                            onClick = {
                            },
                            colors = CardDefaults.cardColors(
                                containerColor = colorScheme.primary
                            ),
                            modifier = Modifier
                                .size(50.dp)
                                .padding(5.dp)
                        ) {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "Z",
                                    style = TextStyle(
                                        color = colorScheme.secondary,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 20.sp
                                    ),
                                )
                            }
                        }
                        Text(
                            text = stringResource(id = R.string.get),
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 20.sp,
                            color = colorScheme.secondary,
                        )
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
        Column(
            modifier = Modifier.wrapContentSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ControlMemory(viewModel = viewModel, uiState)
            ProgressBar(uiState)
            Memory(viewModel = viewModel)
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
        leadingIconRes = R.drawable.edit_24dp_e8eaed_fill0_wght400_grad0_opsz24
    ) {
        viewModel.onInputChanged(it)
    }
    Spacer(modifier = Modifier.size(10.dp))
    TextFieldCustom(
        value = uiState.value.inputCustom,
        placeholderRes = R.string.input_custom,
        keyboardType = KeyboardType.Text,
        leadingIconRes = R.drawable.edit_24dp_e8eaed_fill0_wght400_grad0_opsz24
    ) {
        viewModel.onInputCustomChanged(it)
    }
}

@Composable
private fun Control(
    viewModel: ControlMemoryViewModel,
    uiState: State<ControlMemoryViewModel.UiState>
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxWidth()
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
                IconButton(onClick = { viewModel.add(uiState.value.input) }) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_add_24),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp),
                        tint = colorScheme.secondary
                    )
                }
                IconButton(onClick = { viewModel.delete(uiState.value.input) }) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_remove_24),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp),
                        tint = colorScheme.secondary
                    )
                }
                IconButton(onClick = {
                    viewModel.replace(
                        uiState.value.input, uiState.value.inputCustom
                    )
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.edit_24dp_e8eaed_fill0_wght400_grad0_opsz24),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp),
                        tint = colorScheme.secondary
                    )
                }
                IconButton(onClick = {
                    viewModel.clear()
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_delete_24),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp),
                        tint = colorScheme.error
                    )
                }
            }
        }
    }
}

@Composable
private fun Memory(viewModel: ControlMemoryViewModel) {
    val matrix = viewModel.matrixState.collectAsState().value
    val numColumns = 4
    LazyVerticalGrid(
        columns = GridCells.Fixed(numColumns),
        modifier = Modifier
            .wrapContentSize()
            .padding(10.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(matrix.size) { index ->
            val cell = matrix[index]
            ModulesMemory(pair = cell, viewModel)
        }
    }
}

@Composable
private fun ModulesMemory(pair: Pair<Char, String>, viewModel: ControlMemoryViewModel) {
    var onClick by remember { mutableStateOf(false) }
    AnimatedVisibility(onClick && pair.first != ' ') {
        Dialog(
            onDismissRequest = { onClick = false },
            content = {
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = colorScheme.primary
                    ), modifier = Modifier.wrapContentSize()
                ) {
                    Column(
                        modifier = Modifier.padding(15.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = stringResource(id = R.string.info),
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 20.sp,
                            color = colorScheme.secondary,
                        )
                        Text(
                            text = "Value: ${pair.first}",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 20.sp,
                            color = colorScheme.secondary,
                        )
                        Text(
                            text = "Id: ${pair.second}",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 20.sp,
                            color = colorScheme.secondary,
                        )
                        IconButton(onClick = {
                            onClick = false
                            viewModel.clear()
                        }) {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_delete_24),
                                contentDescription = null,
                                modifier = Modifier.size(24.dp),
                                tint = colorScheme.error
                            )
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
            containerColor = if (pair.first == ' ') colorScheme.background else colorScheme.primary
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
@Preview
private fun Preview() = ScreenControlMemory()