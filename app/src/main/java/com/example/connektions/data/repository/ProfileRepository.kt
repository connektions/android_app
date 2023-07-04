package com.example.connektions.data.repository

import com.example.connektions.R
import com.example.connektions.data.model.User
import com.example.connektions.data.model.UserProject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import com.example.connektions.common.Result

class ProfileRepository {

    suspend fun getUserProfile(): Result<User> = withContext(Dispatchers.IO) {
        // Simulate network latency
        delay(2000)

        return@withContext try {
            Result.Success(getDummyUser())
        } catch (e: Exception) {
            Result.Error("Failed to fetch user data.")
        }
    }

    fun getDummyUser(): User {
        // Simulating fetching user data from a server

        val user = User(
            name = "Артем",
            surname = "Золотухин",
            city = "Санкт-Петербург",
            aboutMe = "Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur, ",
            skills = listOf("Java", "Kotlin", "Android"),
            universityName = "ITMO University",
            facultyName = "Мобильные и сетевые технологии",
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
            teamUserIsIn = "Team Connections",
            email = "artem.zolotukhin@emample.com",
            github = "github.com/example",
            telegram = "@ExampleUser",
            profileImage = R.drawable.artem_placeholder_image
        )
        return user
    }
}

