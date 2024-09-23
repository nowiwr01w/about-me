package com.nowiwr01p.me.ui.data.pet_project

import com.nowiwr01p.me.ui.data.CompanyInfo
import com.nowiwr01p.me.ui.data.Details
import com.nowiwr01p.me.ui.data.ProjectInfo
import com.nowiwr01p.me.ui.data.WorkExperience

data object PetProjectData : WorkExperience(
    companyInfo = CompanyInfo(
        name = "Protest",
        description = "With this application, people can cooperate, organize protests and demonstrations, as well as read news that they will not be told on TV."
    ),
    projectInfo = ProjectInfo(
        position = "",
        details = Details.ProjectLink("https://github.com/nowiwr01w/Protest"),
        tasks = listOf(
            "Created animated Splash Screen which can be changed dynamically without extra releases",
            "Worked with Maps using Google Maps API to cooperate people on protests",
            "Worked on networking with Firebase, threading with Coroutines and local storage with DataStore",
            "Worked with Firebase: Auth, Database, Remote Config, Cloud Messaging, Analytics, and Crashlytics",
            "Implemented Push Notifications to announce meetings ans news",
            "Implemented several animations using Lottie framework and built-in Jetpack Compose animations",
            "Implemented Clean Architecture with MVI design pattern and Koin dependency injection framework"
        )
    )
)