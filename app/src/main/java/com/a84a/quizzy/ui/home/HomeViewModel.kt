package com.a84a.quizzy.ui.home

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.io.IOException
import java.net.URL

class HomeViewModel : ViewModel() {

    companion object {
        private const val DASHBOARD_URL =
            "https://firebasestorage.googleapis.com/v0/b/user-contacts-ade83.appspot.com/o/student_dashboard.json?alt=media&token=0091b4c2-2ee2-4326-99cd-96d5312b34bd"
    }

    private val _uiState = MutableStateFlow(HomeUiState(isLoading = true))
    val uiState: StateFlow<HomeUiState> = _uiState

    init {
        loadDashboard()
    }

    fun loadDashboard() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }

            try {
                val json = URL(DASHBOARD_URL).readText() // simple HTTP GET
                val newState = parseDashboardToUiState(json)

                withContext(Dispatchers.Main) {
                    _uiState.value = newState
                }
            } catch (e: IOException) {
                e.printStackTrace()
                withContext(Dispatchers.Main) {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = "Failed to load dashboard. Please try again."
                        )
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                withContext(Dispatchers.Main) {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = e.message ?: "Something went wrong"
                        )
                    }
                }
            }
        }
    }

    //  JSON → HomeUiState mapping
    private fun parseDashboardToUiState(json: String): HomeUiState {
        val root = JSONObject(json)

        // ----- student -----
        val student = root.getJSONObject("student")
        val name = student.getString("name")
        val klass = student.getString("class")

        val availability = student
            .getJSONObject("availability")
            .getString("status")

        val quizAttempts = student
            .getJSONObject("quiz")
            .getInt("attempts")

        val accuracyCurrentStr = student
            .getJSONObject("accuracy")
            .getString("current") // e.g. "72%"

        val accuracyCurrent = accuracyCurrentStr
            .trim()
            .removeSuffix("%")
            .toIntOrNull() ?: 0

        // availability color
        val availabilityColor = when (availability.lowercase()) {
            "present" -> Color(0xFF31C75A)
            "absent" -> Color(0xFFE57373)
            else -> Color(0xFFB0B0B0)
        }

        // ----- todaySummary -----
        val today = root.getJSONObject("todaySummary")
        val mood = today.getString("mood") // "Focused"
        val description = today.getString("description")

        val recommendedVideo = today.getJSONObject("recommendedVideo")
        val ctaText = recommendedVideo.getString("actionText")


        // ----- weeklyOverview -----
        val weekly = root.getJSONObject("weeklyOverview")

        // quiz streak
        val streakArray = weekly.getJSONArray("quizStreak")
        val streakTotal = streakArray.length()
        var streakDone = 0
        for (i in 0 until streakTotal) {
            val item = streakArray.getJSONObject(i)
            if (item.getString("status").equals("done", ignoreCase = true)) {
                streakDone++
            }
        }

        // overall accuracy
        val overallAccuracy = weekly.getJSONObject("overallAccuracy")
        val overallPercent = overallAccuracy.getInt("percentage") // 68
        val overallLabel = overallAccuracy.getString("label")     // "68% correct"
        val overallProgress = overallPercent / 100f               // 0.68f

        return HomeUiState(
            studentName = name,
            className = klass,
            availabilityText = availability,
            availabilityColor = availabilityColor,
            quizAttemptsText = "$quizAttempts Attempt",
            accuracyPercent = accuracyCurrent,

            todayTitle = mood,
            todaySubtitle = description,
            todayCtaText = ctaText,

            quizStreakCompleted = streakDone,
            quizStreakTotal = streakTotal,

            accuracyLabel = overallLabel,
            accuracyProgress = overallProgress,

            isLoading = false,
            errorMessage = null
        )
    }
}
