package com.a84a.quizzy.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import com.a84a.quizzy.core.theme.AppTypography

private val WhiteColorScheme = lightColorScheme(
       background = White,
    surface = White,
    onBackground = Black,
    onSurface = Black,
)


@Composable
fun QuizzyTheme(
    darkTheme: Boolean = false,
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = WhiteColorScheme,
        typography = AppTypography,
        content = content
    )
}
