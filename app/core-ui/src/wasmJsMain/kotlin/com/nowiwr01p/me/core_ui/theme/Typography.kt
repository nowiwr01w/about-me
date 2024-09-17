package com.nowiwr01p.me.core_ui.theme

import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

val AppTypography @Composable get() = Typography(
    h1 = TextStyle(
        // Пример: "Добро пожаловать на мой сайт"
        color = Color(0xFFBB86FC), // rgb(187, 134, 252)
        fontFamily = JetBrainsMono,
        fontWeight = FontWeight.ExtraBold, // 700
        fontSize = 36.sp, // 32px
        lineHeight = 36.sp, // 57.599998px
        textAlign = TextAlign.Center
    ),
    h2 = TextStyle(
        // Пример: "Обо мне"
        color = Color(0xFFE0E0E0), // rgb(224, 224, 224)
        fontFamily = JetBrainsMono,
        fontWeight = FontWeight.SemiBold, // 600
        fontSize = 24.sp, // Примерный размер
        lineHeight = 24.sp, // Примерный размер
        textAlign = TextAlign.Center
    ),
    h3 = TextStyle(
        // Пример: "Мои навыки"
        color = Color(0xFFE0E0E0),
        fontFamily = JetBrainsMono,
        fontWeight = FontWeight.Medium, // 500
        fontSize = 20.sp,
        lineHeight = 28.sp,
        textAlign = TextAlign.Start
    ),
    h4 = TextStyle(
        // Пример: "Проекты"
        color = Color(0xFFE0E0E0),
        fontFamily = JetBrainsMono,
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp,
        lineHeight = 24.sp,
        textAlign = TextAlign.Start
    ),
    h5 = TextStyle(
        // Пример: "Контакты"
        color = Color(0xFFE0E0E0),
        fontFamily = JetBrainsMono,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 20.sp,
        textAlign = TextAlign.Start
    ),
    h6 = TextStyle(
        // Пример: "Дополнительная информация"
        color = Color(0xFFE0E0E0),
        fontFamily = JetBrainsMono,
        fontWeight = FontWeight.Normal, // 400
        fontSize = 14.sp,
        lineHeight = 18.sp,
        textAlign = TextAlign.Start
    ),
    subtitle1 = TextStyle(
        // Пример: "Подзаголовок раздела"
        color = Color(0xFFE0E0E0),
        fontFamily = JetBrainsMono,
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp,
        lineHeight = 24.sp,
        textAlign = TextAlign.Start
    ),
    subtitle2 = TextStyle(
        // Пример: "Вторичный подзаголовок"
        color = Color(0xFFE0E0E0),
        fontFamily = JetBrainsMono,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 20.sp,
        textAlign = TextAlign.Start
    ),
    body1 = TextStyle(
        // Пример: "Основной текст"
        color = Color(0xFFE0E0E0),
        fontFamily = JetBrainsMono,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp, // Соответствует предыдущим стилям
        lineHeight = 32.4.sp, // 32.400002px
        textAlign = TextAlign.Justify
    ),
    body2 = TextStyle(
        // Пример: "Малый текст"
        color = Color(0xFFE0E0E0),
        fontFamily = JetBrainsMono,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        textAlign = TextAlign.Justify
    ),
    button = TextStyle(
        // Пример: "Кнопка"
        color = Color(0xFFBB86FC),
        fontFamily = JetBrainsMono,
        fontWeight = FontWeight.Bold, // 700
        fontSize = 16.sp,
        lineHeight = 24.sp,
        textAlign = TextAlign.Center
    ),
    caption = TextStyle(
        // Пример: "Подпись"
        color = Color(0xFFBB86FC),
        fontFamily = JetBrainsMono,
        fontWeight = FontWeight.Medium, // 500
        fontSize = 14.sp,
        lineHeight = 20.sp,
        textAlign = TextAlign.Start
    ),
    overline = TextStyle(
        // Пример: "Сверхстрочный текст"
        color = Color(0xFFBB86FC),
        fontFamily = JetBrainsMono,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        textAlign = TextAlign.Start
    )
)