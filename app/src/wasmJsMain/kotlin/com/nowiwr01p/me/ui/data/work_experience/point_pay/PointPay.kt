package com.nowiwr01p.me.ui.data.work_experience.point_pay

import com.nowiwr01p.me.ui.data.*

data object PointPay : WorkExperience(
    companyInfo = CompanyInfo(
        name = "PointPay",
        description = "The ecosystem provides all-in-one crypto solutions, including cryptobank, exchange, wallet, payment system, launchpad, and crypto school"
    ),
    projectInfo = ProjectInfo(
        position = "Senior Android Developer",
        details = Details.Dates(
            startDate = "08.2022",
            endDate = "02.2023"
        ),
        tasks = listOf(
            "Created a lot of screens from scratch. Implemented design, logic, and client-server integration with the backend using the Retrofit library",
            "Covered each screen with Unit Tests",
            "Implemented several reusable components such as BottomSheet and animated SnackBar that can be used in any part of the application",
            "Collaborated with designers to create a new design system with beautiful animations and smooth transitions between screens. Implemented these animations using the Lottie library and built-in Jetpack Compose tools, and added smooth transitions with the Jetpack Navigation Component"
        )
    )
)