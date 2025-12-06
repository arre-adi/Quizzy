package com.a84a.quizzy.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.a84a.quizzy.data.auth.FirebaseAuthDataSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val auth = FirebaseAuthDataSource()

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun onSchoolIdChange(value: String) {
        _uiState.update { it.copy(schoolId = value, error = null) }
    }

    fun onStudentIdChange(value: String) {
        _uiState.update { it.copy(studentId = value, error = null) }
    }

    fun onLoginClick() {
        val state = _uiState.value

        if (state.schoolId.isBlank() || state.studentId.isBlank()) {
            _uiState.update { it.copy(error = "Please fill both fields") }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }

            try {
                auth.signIn(
                    email = state.schoolId.trim(),
                    password = state.studentId.trim()
                )

                _uiState.update {
                    it.copy(
                        isLoading = false,
                        loggedIn = true
                    )
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        error = e.message ?: "Login failed"
                    )
                }
            }
        }
    }
}
