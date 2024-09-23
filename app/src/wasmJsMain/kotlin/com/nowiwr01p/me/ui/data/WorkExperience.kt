package com.nowiwr01p.me.ui.data

import com.nowiwr01p.me.ui.data.work_experience.agc_soft.AgcSoft
import com.nowiwr01p.me.ui.data.work_experience.firreteli_ltd.FirreteliLtd
import com.nowiwr01p.me.ui.data.work_experience.lifehacker.LifeHacker
import com.nowiwr01p.me.ui.data.work_experience.point_pay.PointPay

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
    val details: Details,
    val tasks: List<String>
)

sealed interface Details {

    data class ProjectLink(val link: String) : Details

    data class Dates(
        val startDate: String,
        val endDate: String
    ) : Details
}

internal val workExperienceItems = listOf(
    AgcSoft,
    FirreteliLtd,
    PointPay,
    LifeHacker
)