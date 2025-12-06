package com.a84a.quizzy.core.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.a84a.quizzy.R



val RedditSans = FontFamily(
    Font(R.font.reddit_sans_regular, FontWeight.Normal),
    Font(R.font.reddit_sans_medium, FontWeight.Medium),
    Font(R.font.reddit_sans_semibold, FontWeight.SemiBold),
    Font(R.font.reddit_sans_bold, FontWeight.Bold)
)


val AppTypography = Typography(

    // Headlines
    headlineLarge = TextStyle(
        fontFamily = RedditSans,
        fontWeight = FontWeight.SemiBold,
        fontSize = 32.sp,
        lineHeight = 38.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = RedditSans,
        fontWeight = FontWeight.SemiBold,
        fontSize = 28.sp,
        lineHeight = 34.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = RedditSans,
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp,
        lineHeight = 30.sp
    ),

    // Titles
    titleLarge = TextStyle(
        fontFamily = RedditSans,
        fontWeight = FontWeight.SemiBold,
        fontSize = 22.sp
    ),
    titleMedium = TextStyle(
        fontFamily = RedditSans,
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp
    ),
    titleSmall = TextStyle(
        fontFamily = RedditSans,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp
    ),

    // Body
    bodyLarge = TextStyle(
        fontFamily = RedditSans,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = RedditSans,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    bodySmall = TextStyle(
        fontFamily = RedditSans,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),

    // Labels
    labelLarge = TextStyle(
        fontFamily = RedditSans,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp
    ),
    labelMedium = TextStyle(
        fontFamily = RedditSans,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp
    ),
    labelSmall = TextStyle(
        fontFamily = RedditSans,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp
    )
)
