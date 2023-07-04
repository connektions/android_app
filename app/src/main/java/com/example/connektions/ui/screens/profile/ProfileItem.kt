package com.example.connektions.ui.screens.profile

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.connektions.R
import com.example.connektions.data.model.User
import com.example.connektions.ui.theme.Shapes

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ProfileCardItem(
    @DrawableRes iconId: Int,
    @StringRes sectionName: Int,
    sectionId: Int = 1,
    user: User,
    isExpanded: Boolean,
    onExpandedChange: (Boolean) -> Unit
) {

    Card(
        shape = Shapes.medium,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 16.dp)
            .clickable { onExpandedChange(!isExpanded) }
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
                .padding(horizontal = 12.dp)
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
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = MaterialTheme.typography.titleSmall,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    imageVector = ImageVector.vectorResource(if (!isExpanded) R.drawable.profile_plus_icon else R.drawable.profile_update_button),
                    tint = Color.Blue,
                    contentDescription = "Expand/Collapse Icon",
                    modifier = Modifier
                        .clickable { onExpandedChange(!isExpanded) }
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
                        .fillMaxWidth(0.98f)
                )
                when (sectionId) {
                    1 -> Text(
                        text = user.aboutMe,
                        modifier = Modifier.padding(vertical = 4.dp),
                        maxLines = 20,
                        overflow = TextOverflow.Ellipsis,
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.bodyMedium
                    )

                    2 -> FlowRow(
                        modifier = Modifier.padding(4.dp),
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                    ) {
                        user.skills.forEach { skill ->
                            SuggestionChip(
                                onClick = { },
                                label = {
                                    Text(
                                        text = skill,
                                        color = MaterialTheme.colorScheme.onSecondary,
                                        style = MaterialTheme.typography.labelSmall
                                    )
                                },
                                border = null,
                                colors = SuggestionChipDefaults.suggestionChipColors(
                                    containerColor = MaterialTheme.colorScheme.primary
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
                            RowWithText("Почта: ", user.email)
                            RowWithText("GitHub: ", user.github)
                            Spacer(modifier = Modifier.height(100.dp))
                            RowWithText("Телеграм: ", user.telegram)
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
            color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.labelMedium
        )
        Text(
            text = desc ?: "Ошибка - текст не был найден",
            color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
private fun RowWithText(title: String?, desc: String?) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title ?: "Ошибка - текст не был найден",
            color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.labelMedium
        )
        Text(
            text = desc ?: "Ошибка - текст не был найден",
            color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}