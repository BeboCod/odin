package com.example.odin.ui.screens.center.screens.post

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.odin.R
import com.example.odin.data.service.User
import com.example.odin.ui.mods.ImagesProfile
import com.example.odin.ui.mods.UserNameOdin
import com.example.odin.ui.theme.OdinTheme
import com.example.odin.utils.SocialMedia

@Composable
fun PostScreen(navController: NavController) = OdinTheme { ContentScreen(navController) }

@Composable
private fun ContentScreen(navController: NavController) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(colorScheme.background)
            .padding(horizontal = 10.dp)
            .padding(bottom = 20.dp, top = 10.dp),
    ) {
        val viewModel = PostViewModel()
        val data = viewModel.getData("123")
        item {
            Header(
                user = data.user,
                verified = data.user.verified,
                navController = navController
            )
            Spacer(modifier = Modifier.padding(10.dp))
            Content(
                title = data.title,
                description = data.description,
                urlVideo = data.urlVideo,
                code = data.code,
                conclusion = data.conclusion
            )
            Footer(
                socialMedia = listOf(SocialMedia("X", "x.com", 0)),
                collaborators = data.collaborators
            )
        }
    }
}

@Composable
private fun Header(
    user: User,
    verified: Boolean,
    navController: NavController,
) {
    val icon = if (verified) R.drawable.baseline_verified_24 else R.drawable.baseline_person_24
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        IconButton(onClick = {
            navController.popBackStack()
        }) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_arrow_back_ios_24),
                contentDescription = null,
                modifier = Modifier.size(50.dp),
                tint = colorScheme.primary
            )
        }
        ImagesProfile(
            imageUser = painterResource(id = R.drawable.ic_launcher_background),
            modifier = Modifier.size(50.dp)
        )//Remplazar por imageUser
        Spacer(modifier = Modifier.padding(5.dp))
        UserNameOdin(text = user.userName)
        Spacer(modifier = Modifier.padding(5.dp))
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = Color(0xFF75FAFC)
        )
    }
}

@Composable
private fun Content(
    description: String,
    urlVideo: String,//Agregar logica de video
    code: String,
    conclusion: String,
    title: String,
) {
    Column(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            style = TextStyle(
                color = colorScheme.secondary,
                fontSize = 23.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
        )
        Spacer(modifier = Modifier.padding(10.dp))
        Text(
            text = description,
            style = TextStyle(
                color = colorScheme.secondary,
                fontSize = 15.sp,
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Normal
            )
        )
        //Agregar logica de video
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF272a40)
            )
        ) {
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.Start,
            ) {
                Text(
                    text = formatCode(code, Color(0xFF4F91E0), colorScheme.error, Color.Red),
                    color = colorScheme.secondary,
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 15.sp,
                    lineHeight = 20.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
            }
        }
        Spacer(modifier = Modifier.padding(10.dp))
        Text(
            text = conclusion,
            style = TextStyle(
                color = colorScheme.secondary,
                fontSize = 15.sp,
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Normal
            )
        )
    }
}

@Composable
private fun Footer(
    socialMedia: List<SocialMedia>,
    collaborators: List<User>,
) {
    Column(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

    }
}

fun formatCode(
    code: String,
    keywordColor: Color,
    numberColor: Color,
    commentColor: Color,
): AnnotatedString {
    val keywords =
        listOf("fun", "val", "var", "if", "else", "for", "while", "when", "return", "import", "class")
    val commentRegex = Regex("//.*")
    val numberRegex = Regex("\\b\\d+\\b")

    return buildAnnotatedString {
        code.split("\n").forEach { line ->
            val lineStart = this.length

            append(line + "\n") // Append the line and a newline character

            if (commentRegex.containsMatchIn(line)) {
                val commentStart = line.indexOf("//")
                addStyle(
                    SpanStyle(color = commentColor),
                    lineStart + commentStart,
                    lineStart + line.length
                )
            } else {
                keywords.forEach { keyword ->
                    var startIndex = line.indexOf(keyword)
                    while (startIndex >= 0) {
                        val endIndex = startIndex + keyword.length
                        if ((startIndex == 0 || !line[startIndex - 1].isLetterOrDigit()) &&
                            (endIndex == line.length || !line[endIndex].isLetterOrDigit())
                        ) {
                            addStyle(
                                SpanStyle(color = keywordColor, fontWeight = FontWeight.Bold),
                                lineStart + startIndex,
                                lineStart + endIndex
                            )
                        }
                        startIndex = line.indexOf(keyword, startIndex + 1)
                    }
                }

                numberRegex.findAll(line).forEach {
                    addStyle(
                        SpanStyle(color = numberColor),
                        lineStart + it.range.first,
                        lineStart + it.range.last + 1
                    )
                }
            }
        }
    }
}


@Composable
@Preview
private fun PostScreenPreview() = PostScreen(rememberNavController())