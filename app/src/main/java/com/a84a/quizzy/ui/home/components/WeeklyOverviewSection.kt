package com.a84a.quizzy.ui.home.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.a84a.quizzy.ui.home.HomeUiState
import com.a84a.quizzy.ui.theme.*
import com.a84a.quizzy.R
import com.a84a.quizzy.ui.theme.QuizzyTheme

@Composable
fun WeeklyOverviewSection(
    state: HomeUiState
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Weekly Overview",
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.W700
            ),
            color = Black,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        Surface(
            shape = RoundedCornerShape(16.dp),
            color = White,
            shadowElevation = 0.dp,
            border = BorderStroke(1.dp, Color(0xFFE5E5E5))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                // ---------- Quiz Streak header ----------
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Quiz Streak",
                        style = MaterialTheme.typography.titleSmall,
                        color = Color.Black,
                        modifier = Modifier.weight(1f)
                    )

                    // top-right illustrations
                    Image(
                        painter = painterResource(id = R.drawable.quizstreak_img),
                        contentDescription = null,
                        modifier = Modifier.size(36.dp)
                    )
                }

                // ---------- Quiz Streak icons row ----------
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    val weekdayLabels = listOf("M", "T", "W", "T", "F", "S", "S")

                    repeat(state.quizStreakTotal) { index ->
                        val done = index < state.quizStreakCompleted
                        val label = weekdayLabels.getOrNull(index) ?: ""

                        if (done) {
                            StreakDoneIcon()
                        } else {
                            StreakPendingIcon(label = label)
                        }
                    }
                }

                // ---------- Accuracy header ----------
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Accuracy",
                        style = MaterialTheme.typography.titleSmall,
                        color = Color.Black,
                        modifier = Modifier.weight(1f)
                    )

                    Image(
                        painter = painterResource(id = R.drawable.accuracy_img),
                        contentDescription = null,
                        modifier = Modifier.size(32.dp)
                    )
                }

                // ---------- Accuracy details ----------
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = state.accuracyLabel,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    LinearProgressIndicator(
                        progress = { state.accuracyProgress },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(6.dp),
                        color = Red,
                        trackColor = Color(0xFFFFE5E5)
                    )
                }
            }
        }
    }
}

@Composable
private fun StreakDoneIcon() {
    Box(
        modifier = Modifier
            .size(30.dp)
            .background(color = Green, shape = CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Icon(painterResource(R.drawable.ic_check),
            contentDescription = null,
            tint = White,
            modifier = Modifier.size(30.dp))
    }
}

@Composable
private fun StreakPendingIcon(label: String) {
    Box(
        modifier = Modifier
            .size(30.dp)
            .border(
                width = 1.dp,
                color = Color(0xFFB0B0B0),
                shape = CircleShape,
            ),
        contentAlignment = Alignment.Center
    ) {
        if (label.isNotBlank()) {
            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall,
                color = Color(0xFF8A8A8A),
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun WeeklyOverviewPreview() {
    val mockState = HomeUiState(
        // --- Top section ---
        studentName = "Gaurav",
        className = "10th Class",

        // --- Streak ---
        quizStreakCompleted = 4,
        quizStreakTotal = 7,

        // --- Accuracy ---
        accuracyLabel = "68% correct",
        accuracyProgress = 0.68f,


        availabilityText = "Present",
        availabilityColor = Color.Green,
        quizAttemptsText = "3 Attempt",

        // --- Summary card ---
        todayTitle = "Focused",
        todaySubtitle = "Struggles with Apply-level Math today.",
        todayCtaText = "Watch: Apply Pythagoras Theorem"
    )

    QuizzyTheme {
        Surface(color = Color(0xFFF7F7F7)) {
            Column(modifier = Modifier.padding(16.dp)) {
                WeeklyOverviewSection(state = mockState)
            }
        }
    }
}
