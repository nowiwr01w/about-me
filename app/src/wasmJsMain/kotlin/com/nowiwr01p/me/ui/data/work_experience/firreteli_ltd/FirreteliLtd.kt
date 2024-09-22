package com.nowiwr01p.me.ui.data.work_experience.firreteli_ltd

import com.nowiwr01p.me.ui.data.CompanyInfo
import com.nowiwr01p.me.ui.data.Dates
import com.nowiwr01p.me.ui.data.ProjectInfo
import com.nowiwr01p.me.ui.data.WorkExperience

data object FirreteliLtd : WorkExperience(
    companyInfo = CompanyInfo(
        name = "Firreteli LTD",
        description = "VPN service provider"
    ),
    projectInfo = ProjectInfo(
        position = "Senior Android Developer",
        enrolledDates = Dates(
            startDate = "03.2023",
            endDate = "10.2023"
        ),
        tasks = listOf(
            "Built app from almost scratch",
            "Implemented local data and HTTP Body custom encryption",
            "Integrated in-app subscription using Google Play Billing",
            "Added support for multiple VPN protocols"
        )
    )
)