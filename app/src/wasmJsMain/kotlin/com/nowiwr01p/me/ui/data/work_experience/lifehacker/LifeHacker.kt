package com.nowiwr01p.me.ui.data.work_experience.lifehacker

import com.nowiwr01p.me.ui.data.CompanyInfo
import com.nowiwr01p.me.ui.data.Dates
import com.nowiwr01p.me.ui.data.ProjectInfo
import com.nowiwr01p.me.ui.data.WorkExperience

data object LifeHacker : WorkExperience(
    companyInfo = CompanyInfo(
        name = "LifeHacker",
        description = "Social media dealing with native advertising"
    ),
    projectInfo = ProjectInfo(
        position = "Middle Android Developer",
        enrolledDates = Dates(
            startDate = "01.2021",
            endDate = "08.2022"
        ),
        tasks = listOf(
            "As the only Android Developer for the company, very quickly jumped on-board and took full responsibility of the development and operation of the app",
            "Made a redesign of the entire application with 3 different themes",
            "Integrated push notifications into the app, leading to different user-flows",
            "Introduced a dynamic font feature throughout the entire app",
            "Implemented multiple new features such as 'folders', 'save favorite articles', 'best of the month'",
            "Set up Gitlab CI/CD for a project from scratch that can check code style, compile multiple builds (.aab & .apk) for different build variants and send them to Slack"
        )
    )
)