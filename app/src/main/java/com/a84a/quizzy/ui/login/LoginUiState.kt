package com.a84a.quizzy.ui.login

data class LoginUiState(
    val schoolId: String = "",
    val studentId: String = "",
    val isLoading: Boolean = false,
    val loggedIn: Boolean = false,
    val error: String? = null
)
