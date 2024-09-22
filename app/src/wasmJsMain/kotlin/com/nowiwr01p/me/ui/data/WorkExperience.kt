package com.nowiwr01p.me.ui.data

open class WorkExperience(
    val companyInfo: CompanyInfo,
    val projectInfo: ProjectInfo
)

data class CompanyInfo(
    val name: String,
    val description: String
)

data class ProjectInfo(
    val position: String,
    val enrolledDates: Dates,
    val tasks: List<String>,
    val stack: String
)

data class Dates(
    val startDate: String,
    val endDate: String
)