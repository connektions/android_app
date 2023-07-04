package com.example.connektions.data.model

import androidx.annotation.DrawableRes

// All this information later will be fetched from server
data class User(
    var name: String,
    var surname: String,
    var city: String,
    var aboutMe: String, // this is sectionText
    var skills: List<String>,
    var universityName: String,
    var facultyName: String,
    var partisapatedProjects: List<UserProject>,
    var teamUserIsIn: String,
    var email: String,
    var github: String,
    var telegram: String,
    @DrawableRes var profileImage: Int
)

