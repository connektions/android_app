package com.example.connektions.ui.components

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.example.connektions.R
import com.example.connektions.data.model.User
import com.example.connektions.data.model.UserProject
import com.example.connektions.ui.theme.Shapes

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ProfileCardItem(
    @DrawableRes iconId: Int,
    @StringRes sectionName: Int,
    sectionId: Int = 1,
    user: User
) {
    var isExpanded by remember { mutableStateOf(false) }

    Card(
        shape = Shapes.medium,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 16.dp)
            .clickable { isExpanded = !isExpanded }
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            ),
    ) {
        Column(
            modifier = Modifier
                .background(Color.White)
                .padding(horizontal = 10.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(vertical = 16.dp)
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = iconId),
                    tint = Color.Blue,
                    contentDescription = null
                )
                Text(
                    text = stringResource(id = sectionName),
                    modifier = Modifier.padding(start = 8.dp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    imageVector = ImageVector.vectorResource(if (!isExpanded) R.drawable.profile_plus_icon else R.drawable.profile_update_button),
                    tint = Color.Blue,
                    contentDescription = "Expand/Collapse Icon",
                    modifier = Modifier
                        .clickable { isExpanded = !isExpanded }
                        .drawBehind {
                            drawCircle(
                                color = Color(0xFFD8E1FE),
                                radius = this.size.maxDimension
                            )
                        }
                )
            }
            if (isExpanded) {
                Divider(
                    modifier = Modifier
                        .height(1.dp)
                        .fillMaxWidth(0.965f)
                )
                when (sectionId) {
                    1 -> Text(
                        text = user.aboutMe,
                        modifier = Modifier.padding(vertical = 4.dp),
                        maxLines = 5,
                        overflow = TextOverflow.Ellipsis
                    )

                    2 -> FlowRow(
                        modifier = Modifier.padding(4.dp),
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                    ) {
                        user.skills.forEach { skill ->
                            SuggestionChip(
                                onClick = { Log.d("user_profile_screen", "Chip touch") },
                                label = {
                                    Text(text = skill)
                                },
                                border = null,
                                colors = SuggestionChipDefaults.suggestionChipColors(
                                    containerColor = MaterialTheme.colorScheme.primaryContainer
                                )
                            )
                        }
                    }

                    3 -> ColumnWithText(title = user.facultyName, desc = user.universityName)

                    4 -> FlowColumn() {
                        user.partisapatedProjects.forEach { project ->
                            ColumnWithText(project.ProjectName, project.ProjectDatesOfParticipants)

                        }
                    }

                    5 -> {
                        ColumnWithText(title = user.facultyName, desc = user.universityName)
                    }

                    6 ->
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 10.dp, vertical = 6.dp)
                        ) {
                            val emailFormatted = buildAnnotatedString {
                                // First text style
                                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                    append("Почта: ")
                                }

                                // Second text style
                                withStyle(
                                    style = SpanStyle(
                                        fontWeight = FontWeight.Normal,
                                    )
                                ) {
                                    append(user.email)
                                }
                            }
                            val gitFormatted = buildAnnotatedString {
                                // First text style
                                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                    append("GitHub: ")
                                }

                                // Second text style
                                withStyle(
                                    style = SpanStyle(
                                        fontWeight = FontWeight.Normal,
                                    )
                                ) {
                                    append(user.github)
                                }
                            }
                            val telegramFormatted = buildAnnotatedString {
                                // First text style
                                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                    append("Телеграм: ")
                                }

                                // Second text style
                                withStyle(
                                    style = SpanStyle(
                                        fontWeight = FontWeight.Normal,
                                    )
                                ) {
                                    append(user.telegram)
                                }
                            }
                            Text(text = emailFormatted)
                            Text(text = telegramFormatted)
                            Text(text = gitFormatted)

                        }
                }
            }
        }
    }
}

@Composable
private fun ColumnWithText(title: String?, desc: String?) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 6.dp)
    ) {
        Text(
            text = title ?: "Ошибка - текст не был найден",
            fontWeight = FontWeight.Bold
        )
        Text(text = desc ?: "Ошибка - текст не был найден")
    }
}
