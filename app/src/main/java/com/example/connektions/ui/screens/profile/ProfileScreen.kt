package com.example.connektions.ui.screens.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.connektions.R
import com.example.connektions.common.Result
import com.example.connektions.ui.viewmodels.ProfileViewModel


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
    viewModel: ProfileViewModel
) {
    val userProfile = viewModel.userProfile.collectAsState().value
    val lazyScrollState = rememberLazyListState()
    val expandedStates = remember { mutableStateMapOf<Int, Boolean>() }
    when (userProfile) {
        is Result.Loading -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        is Result.Success -> {
            val user = userProfile.data
            Column(modifier = Modifier.fillMaxSize()) {
                ProfileTopAppBar(
                    lazyScrollState = lazyScrollState,
                    user = user,
                )

                Spacer(modifier = Modifier.height(16.dp))

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    state = lazyScrollState
                ) {
                    itemsIndexed(sectionNames) { index, section ->
                        val isExpanded = expandedStates[index] ?: false
                        ProfileCardItem(
                            iconId = iconsIds[index],
                            sectionName = section,
                            sectionId = index + 1,
                            user = user,
                            isExpanded = isExpanded, // pass the state to your item
                            onExpandedChange = { expandedStates[index] = it }
                        )
                    }
                }
            }
        }

        is Result.Error -> {
            Text(
                text = "Error: ${userProfile.message}",
                style = MaterialTheme.typography.titleLarge
            )
        }
    }

}
