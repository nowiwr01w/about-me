package com.nowiwr01p.me.ui.data.work_experience.agc_soft

import com.nowiwr01p.me.ui.data.CompanyInfo
import com.nowiwr01p.me.ui.data.Dates
import com.nowiwr01p.me.ui.data.ProjectInfo
import com.nowiwr01p.me.ui.data.WorkExperience

data object AgcSoft : WorkExperience(
    companyInfo = companyInfo,
    projectInfo = projectInfo
)

private val companyInfo = CompanyInfo(
    name = "AGCSoft",
    description = "International digital agency that provides a full range of business promotion services on the Internet, ensure the presence and promotion of global companies in the digital environment"
)

private val projectInfo = ProjectInfo(
    position = "Senior Android Developer",
    enrolledDates = Dates(
        startDate = "11.2023",
        endDate = "Present"
    ),
    tasks = listOf(
        "Implemented push notifications with redirection to 17 screens from scratch",
        "Rewrote numerous elements and screens from XML to Jetpack Compose",
        "Developed theme switching logic for 3 brands (flavors) and 3 color palettes",
        "Refactored the game and game providers screen",
        "Parallelized API requests on the main application screen, reducing shimmer display time",
        "Added an animated FAB component and implemented show/hide logic for various screens"
    )
)