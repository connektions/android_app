package com.example.connektions.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.connektions.R
import com.example.connektions.data.repository.ProfileRepository
import com.example.connektions.ui.components.ProfileCardItem

val iconsIds = listOf<Int>(
    R.drawable.profile_about_me_icon,
    R.drawable.profile_skills_icon,
    R.drawable.profile_education_icon,
    R.drawable.profile_projects_icon,
    R.drawable.profile_team_icon,
    R.drawable.profile_contacts_icon,
)

val sectionNames = listOf<Int>(
    R.string.about_me,
    R.string.skills,
    R.string.education,
    R.string.projects,
    R.string.teams,
    R.string.contacts,
)

@Composable
fun ProfileScreen(
    userRepository: ProfileRepository
) {
    val user = userRepository.getUserProfile()
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        for (i in 1..6) {
            item {
                ProfileCardItem(
                    iconId = iconsIds[i - 1],
                    sectionName = sectionNames[i - 1],
                    sectionId = i,
                    user = user
                )
            }
        }
    }
}
