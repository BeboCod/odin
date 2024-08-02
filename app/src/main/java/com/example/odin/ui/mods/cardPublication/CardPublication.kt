package com.example.odin.ui.mods.cardPublication

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
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.odin.R
import com.example.odin.ui.mods.ChipTheme
import com.example.odin.ui.mods.DescriptionOdin
import com.example.odin.ui.mods.ShowInfoPost
import com.example.odin.ui.mods.TitleCardOdin
import com.example.odin.ui.theme.OdinTheme

@Composable
fun CardPublication(
    title: String = "Programacion Orientada a Objetos (POO)",
    description: String = "Logica de POO explicada con minecraft",
    sharedBy: String = "Override",
    chipTheme: String = "🎓Aprendizaje",
    likes: Int = 69,
    icon: Int = R.drawable.baseline_verified_24
) {
    val viewModel = CardPublicationViewModel(LocalContext.current)
    Card(
        onClick = { viewModel.onClickCard() },
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
                .padding(10.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Absolute.Left
            ) {
                SharedByOdin(text = "${stringResource(id = R.string.shared_by)} $sharedBy", icon = icon)
                InfoByShared(viewModel)
            }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                TitleCardOdin(text = title)
                DescriptionOdin(text = description)
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Like(likes, viewModel)
                ChipTheme(chipTheme, viewModel)
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun CardPublicationPreview() {
    OdinTheme {
        CardPublication(
            "Programacion Orientada a Objetos (POO)",
            "Logica de POO explicada con minecraft",
            "Override",
            "🎓Aprendizaje",
            800,
            R.drawable.baseline_person_24
        )
    }
}

@Composable
private fun Like(likes: Int, viewModel: CardPublicationViewModel) {
    val isLiked = viewModel.uiState.collectAsState().value.isLiked

    val iconSize by animateDpAsState(
        targetValue = if (isLiked) 35.dp else 30.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = ""
    )

    val infiniteTransition = rememberInfiniteTransition()
    val colorAnimation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 4000),
            repeatMode = RepeatMode.Reverse
        ),
        label = ""
    )

    val rainbowColor = Color.hsv(colorAnimation * 360f, 1f, 1f)

    val tint by animateColorAsState(
        targetValue = if (isLiked) Color(0xFFE93323) else Color.LightGray,
        animationSpec = keyframes {
            durationMillis = 500
            if (isLiked) {
                rainbowColor at 250
            }
        }
    )

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
                    painter = painterResource(id = if (isLiked) R.drawable.baseline_favorite_24 else R.drawable.baseline_favorite_border_24),
                    contentDescription = null,
                    tint = tint,
                    modifier = Modifier.size(iconSize)
                )
            }
        }
    }
}

@Composable
private fun SharedByOdin(text: String, icon: Int) {
    TextButton(onClick = { }) {
        Text(
            text = text,
            fontStyle = FontStyle.Normal,
            textAlign = TextAlign.Center,
            modifier = Modifier.widthIn(max = 300.dp),
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            style = TextStyle(
                fontSize = 17.sp,
            ),
            color = colorScheme.secondary
        )
    }
    Icon(
        painter = painterResource(id = icon),
        contentDescription = null,
        tint = Color(0xFF75FAFC)
    )
}

@Composable
private fun InfoByShared(viewModel: CardPublicationViewModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Absolute.Right
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
    if (viewModel.uiState.collectAsState().value.isSheetOpen) {
        ShowInfoPost(
            callback = { isClose ->
                viewModel.onClickInfo(isClose)
            },
            chip = {
            }
        )
    }
}
