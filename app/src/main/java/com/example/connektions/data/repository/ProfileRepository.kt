package com.example.connektions.data.repository

import com.example.connektions.R
import com.example.connektions.data.model.User
import com.example.connektions.data.model.UserProject

class ProfileRepository {
    fun getUserProfile(): User {
        // Simulating fetching user data from a server
        val user = User(
            name = "John Doe",
            city = "New York",
            aboutMe = "I'm a passionate developer",
            skills = listOf("Java", "Kotlin", "Android"),
            universityName = "ABC University",
            facultyName = "Computer Science",
            partisapatedProjects = listOf<UserProject>(
                UserProject(
                    "Проект 1",
                    "Май 2000 - Май 2003"
                ),
                UserProject(
                    "Проект 2",
                    "Май 2003 - Май 2023"
                )
            ),
            teamUserIsIn = "Team X",
            email = "johndoe@example.com",
            github = "github.com/johndoe",
            telegram = "@johndoe",
            imageScr = R.drawable.artem_placeholder_image
        )
        return user
    }
}

