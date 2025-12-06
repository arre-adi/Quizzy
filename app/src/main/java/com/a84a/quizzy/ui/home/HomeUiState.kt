// ui/home/HomeUiState.kt
package com.a84a.quizzy.ui.home

import androidx.compose.ui.graphics.Color

data class HomeUiState(
    val studentName: String = "",
    val className: String = "",
    val availabilityText: String = "",
    val availabilityColor: Color = Color.Unspecified,
    val quizAttemptsText: String = "",
    val accuracyPercent: Int = 0,

    val todayTitle: String = "",
    val todaySubtitle: String = "",
    val todayCtaText: String = "",

    val quizStreakCompleted: Int = 0,
    val quizStreakTotal: Int = 0,

    val accuracyLabel: String = "",
    val accuracyProgress: Float = 0f,

    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
