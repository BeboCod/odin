package com.example.odin.ui.screens.center.screens.post

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
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
import com.example.odin.model.User
import com.example.odin.ui.mods.ImagesProfile
import com.example.odin.ui.mods.UserNameOdin
import com.example.odin.ui.theme.OdinTheme
import com.example.odin.utils.SocialMedia

@Composable
fun PostScreen(navController: NavController) = OdinTheme { ContentScreen(navController) }

@Composable
private fun ContentScreen(navController: NavController) {
    val viewModel = remember { PostViewModel() }
    val data = viewModel.getData("123")

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(colorScheme.background)
            .padding(horizontal = 10.dp, vertical = 10.dp)
    ) {
        item {
            Header(
                user = data.user,
                verified = data.user.verified,
                navController = navController
            )
            Spacer(modifier = Modifier.height(10.dp))
            Content(
                title = data.title,
                description = data.description,
                urlVideo = data.urlVideo,
                code = data.code,
                conclusion = data.conclusion
            )
            Spacer(modifier = Modifier.height(10.dp))
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
    navController: NavController
) {
    val icon = if (verified) R.drawable.baseline_verified_24 else R.drawable.baseline_person_24

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        IconButton(onClick = { navController.popBackStack() }) {
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
        )
        Spacer(modifier = Modifier.width(10.dp))
        UserNameOdin(text = user.userName)
        Spacer(modifier = Modifier.width(10.dp))
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = Color(0xFF75FAFC)
        )
    }
}

@Composable
private fun Content(
    title: String,
    description: String,
    urlVideo: String,
    code: String,
    conclusion: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
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
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = description,
            style = TextStyle(
                color = colorScheme.secondary,
                fontSize = 15.sp,
                textAlign = TextAlign.Start
            )
        )
        Spacer(modifier = Modifier.height(10.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF272a40))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = formatCode(code),
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
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = conclusion,
            style = TextStyle(
                color = colorScheme.secondary,
                fontSize = 15.sp,
                textAlign = TextAlign.Start
            )
        )
    }
}

@Composable
private fun Footer(
    socialMedia: List<SocialMedia>,
    collaborators: List<User>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Implement Footer content here
    }
}

@Composable
fun formatCode(
    code: String,
    keywordColor: Color = Color(0xFF4F91E0),
    numberColor: Color = colorScheme.error,
    commentColor: Color = Color.Red
): AnnotatedString {
    val keywords = listOf("fun", "val", "var", "if", "else", "for", "while", "when", "return", "import", "class")
    val commentRegex = Regex("//.*")
    val numberRegex = Regex("\\b\\d+\\b")

    return buildAnnotatedString {
        code.split("\n").forEach { line ->
            val lineStart = this.length
            append(line + "\n")

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