package com.example.odin.ui.mods.cardPublication

import androidx.annotation.DrawableRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.odin.R
import com.example.odin.ui.mods.ChipTheme
import com.example.odin.ui.mods.DescriptionOdin
import com.example.odin.ui.mods.ShowInfoPost
import com.example.odin.ui.mods.TitleCardOdin
import com.example.odin.ui.theme.OdinTheme

/**
 * Composable que representa una tarjeta de publicación en la interfaz de usuario.
 *
 * @param title El título de la publicación.
 * @param description La descripción de la publicación.
 * @param sharedBy El nombre de quien compartió la publicación.
 * @param chipTheme El tema del chip asociado a la publicación.
 * @param onClickCard Función que se llama cuando se hace clic en la tarjeta.
 * @param likes El número de "me gusta" en la publicación.
 * @param icon Recurso drawable del icono asociado a la publicación.
 * @param viewModel ViewModel asociado a la tarjeta de publicación.
 */
@Composable
fun CardPublication(
    title: String = "Programacion Orientada a Objetos (POO)",
    description: String = "Logica de POO explicada con minecraft",
    sharedBy: String = "Override",
    chipTheme: String = "🎓Aprendizaje",
    onClickCard: () -> Unit,
    likes: Int = 69,
    @DrawableRes icon: Int = R.drawable.baseline_verified_24,
    viewModel: CardPublicationViewModel = CardPublicationViewModel(LocalContext.current),
) {
    Card(
        onClick = onClickCard,
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        shape = Shapes().large,
        colors = CardDefaults.cardColors(containerColor = colorScheme.onBackground)
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HeaderRow(sharedBy, icon, viewModel)
            ContentColumn(
                title = title,
                description = description,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            )
            FooterRow(likes, chipTheme, viewModel)
        }
    }
}

/**
 * Composable que representa la fila superior de la tarjeta con la información de quién compartió la publicación y el botón de información.
 *
 * @param sharedBy El nombre de quien compartió la publicación.
 * @param icon Recurso drawable del icono asociado a la publicación.
 * @param viewModel ViewModel asociado a la tarjeta de publicación.
 */
@Composable
private fun HeaderRow(
    sharedBy: String,
    @DrawableRes icon: Int,
    viewModel: CardPublicationViewModel,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        SharedByOdin(
            text = "${stringResource(id = R.string.shared_by)} $sharedBy",
            icon = icon
        )
        Spacer(Modifier.weight(1f))
        InfoByShared(viewModel)
    }
}

/**
 * Composable que representa la columna central de la tarjeta con el título y la descripción de la publicación.
 *
 * @param title El título de la publicación.
 * @param description La descripción de la publicación.
 * @param modifier Modificador para ajustar el tamaño y disposición de la columna.
 */
@Composable
private fun ContentColumn(title: String, description: String, modifier: Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TitleCardOdin(text = title)
        DescriptionOdin(text = description)
    }
}

/**
 * Composable que representa la fila inferior de la tarjeta con los "me gusta" y el chip asociado.
 *
 * @param likes El número de "me gusta" en la publicación.
 * @param chipTheme El tema del chip asociado a la publicación.
 * @param viewModel ViewModel asociado a la tarjeta de publicación.
 */
@Composable
private fun FooterRow(likes: Int, chipTheme: String, viewModel: CardPublicationViewModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Like(likes, viewModel)
        ChipTheme(chipTheme, viewModel)
    }
}

/**
 * Composable que define el tamaño animado del ícono de "me gusta" en función de si está marcado como "me gusta".
 *
 * @param isLiked Booleano que indica si el ícono está marcado como "me gusta".
 * @return Tamaño del ícono animado.
 */
@Composable
private fun animatedIconSize(isLiked: Boolean): Dp {
    return animateDpAsState(
        targetValue = if (isLiked) 35.dp else 30.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "Icon Size Animation"
    ).value
}

/**
 * Composable que define el color animado del ícono de "me gusta" en función de si está marcado como "me gusta".
 *
 * @param isLiked Booleano que indica si el ícono está marcado como "me gusta".
 * @return Color animado del ícono.
 */
@Composable
private fun animatedTint(isLiked: Boolean): Color {
    val infiniteTransition = rememberInfiniteTransition(label = "Color Transition")
    val colorAnimation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 4000),
            repeatMode = RepeatMode.Reverse
        ),
        label = "Color Animation"
    )
    val rainbowColor = Color.hsv(colorAnimation * 360f, 1f, 1f)
    return animateColorAsState(
        targetValue = if (isLiked) Color(0xFFE93323) else Color.LightGray,
        animationSpec = keyframes {
            durationMillis = 500
            if (isLiked) {
                rainbowColor at 250
            }
        },
        label = "Tint Animation"
    ).value
}

/**
 * Composable que representa el ícono de "me gusta" con la animación de tamaño y color.
 *
 * @param likes El número de "me gusta" en la publicación.
 * @param viewModel ViewModel asociado a la tarjeta de publicación.
 */
@Composable
private fun Like(likes: Int, viewModel: CardPublicationViewModel) {
    val isLiked by viewModel.uiState.collectAsState()
    val iconSize = animatedIconSize(isLiked.isLiked)
    val tint = animatedTint(isLiked.isLiked)

    Box(modifier = Modifier.size(40.dp), contentAlignment = Alignment.Center) {
        BadgedBox(
            badge = {
                Badge(
                    containerColor = tint,
                    contentColor = colorScheme.onBackground
                ) {
                    Text(text = "$likes", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                }
            },
        ) {
            IconButton(
                onClick = {
                    viewModel.onClickLike()
                }
            ) {
                Icon(
                    painter = painterResource(id = if (isLiked.isLiked) R.drawable.baseline_favorite_24 else R.drawable.baseline_favorite_border_24),
                    contentDescription = null,
                    tint = tint,
                    modifier = Modifier.size(iconSize)
                )
            }
        }
    }
}

/**
 * Composable que muestra quién compartió la publicación y el ícono asociado.
 *
 * @param text Texto que muestra quién compartió la publicación.
 * @param icon Recurso drawable del icono asociado a la publicación.
 */
@Composable
private fun SharedByOdin(text: String, @DrawableRes icon: Int) {
    TextButton(onClick = {
        // Acción del botón de texto
    }) {
        Text(
            text = text,
            modifier = Modifier.widthIn(max = 300.dp),
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            style = TextStyle(
                fontStyle = FontStyle.Normal,
                textAlign = TextAlign.Center,
                fontSize = 17.sp,
                color = colorScheme.secondary
            )
        )
    }
    Icon(
        painter = painterResource(id = icon),
        contentDescription = null,
        tint = Color(0xFF75FAFC)
    )
}

/**
 * Composable que muestra el botón de información en la fila superior de la tarjeta.
 *
 * @param viewModel ViewModel asociado a la tarjeta de publicación.
 */
@Composable
private fun InfoByShared(viewModel: CardPublicationViewModel) {
    val isSheetOpen by viewModel.uiState.collectAsState()
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {
        IconButton(onClick = {
            viewModel.onClickInfo(true)
        }) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_info_24),
                contentDescription = null,
                tint = colorScheme.secondary
            )
        }
    }
    if (isSheetOpen.isSheetOpen) {
        ShowInfoPost(
            callback = { viewModel.onClickInfo(it) },
            chip = {},
        )
    }
}
