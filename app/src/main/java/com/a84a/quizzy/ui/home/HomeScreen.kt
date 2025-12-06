package com.a84a.quizzy.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.a84a.quizzy.ui.home.components.SummaryCard
import com.a84a.quizzy.ui.home.components.WeeklyOverviewSection
import com.a84a.quizzy.ui.theme.QuizzyTheme
import com.a84a.quizzy.R
import com.a84a.quizzy.ui.home.components.SmallStatusCard
import com.a84a.quizzy.ui.theme.*


@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel(),
    onNotificationClick: () -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsState()

    HomeContent(
        state = uiState,
        onNotificationClick = onNotificationClick
    )
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeContent(
    state: HomeUiState,
    modifier: Modifier = Modifier,
    onNotificationClick: () -> Unit = {},
    onSummaryCtaClick: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            HomeTopBar(
                name = state.studentName,
                className = state.className,
                onNotificationClick = onNotificationClick
            )
        }
    ) { innerPadding ->
        Surface(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding),
            color = White
        ) {
            when {
                state.isLoading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                state.errorMessage != null -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = state.errorMessage,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }

                else -> {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.White)
                            .padding(horizontal = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {

                        item {
                            StatusCardsRow(state = state)
                        }

                        item {
                            SummaryCard(
                                title = "Today’s Summary",
                                state = state,
                                onCtaClick = onSummaryCtaClick
                            )
                        }

                        item {
                            WeeklyOverviewSection(state = state)
                        }
                    }
                }
            }
        }
    }
}


// ---------- Top Bar ----------
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeTopBar(
    name: String,
    className: String,
    onNotificationClick: () -> Unit
) {
    TopAppBar(
        title = {
            Column(
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Hello $name!",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.SemiBold
                    ),
                    color = Color.Black
                )
                Text(
                    text = className,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color(0xFF6B6B6B)
                )
            }
        },
        actions = {
            IconButton(onClick = onNotificationClick) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_notificationbell),
                    contentDescription = "Notifications",
                    tint = Color.Black
                )
            }
        }
    )
}


// ---------- Status Cards ----------

@Composable
private fun StatusCardsRow(state: HomeUiState) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        SmallStatusCard(
            icon = R.drawable.ic_availability,
            title = "Availability",
            value = state.availabilityText,
            borderColor = Green
        )
        SmallStatusCard(
            icon = R.drawable.ic_quiz,
            title = "Quiz",
            value = state.quizAttemptsText,
            borderColor = Orange
        )
        SmallStatusCard (
            icon = R.drawable.ic_accuracy,
            title = "Accuracy",
            value = "${state.accuracyPercent}%",
            borderColor = Red
        )
    }
}





// ---------- Preview ----------
@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun HomeScreenPreview() {
    val mockState = HomeUiState(
        studentName = "Gaurav",
        className = "10th Class",
        availabilityText = "Present",
        availabilityColor = Color(0xFF31C75A),
        quizAttemptsText = "3 Attempt",
        accuracyPercent = 72,
        todayTitle = "Focused",
        todaySubtitle = "Struggles with Apply-level Math today.",
        todayCtaText = "Watch: Apply Pythagoras Theorem",
        quizStreakCompleted = 4,
        quizStreakTotal = 7,
        accuracyLabel = "68% correct",
        accuracyProgress = 0.68f
    )

    QuizzyTheme {
        HomeContent(state = mockState)
    }
}