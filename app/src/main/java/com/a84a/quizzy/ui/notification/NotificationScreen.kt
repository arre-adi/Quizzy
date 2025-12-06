package com.a84a.quizzy.ui.notification

import SettingRow
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.a84a.quizzy.ui.theme.QuizzyTheme
import com.a84a.quizzy.R
import com.a84a.quizzy.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    onBackClick: () -> Unit = {},
    onLogoutClick: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Notifications & Settings",
                        style = MaterialTheme.typography.titleMedium,
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            painter = painterResource(id = R.drawable.outline_arrow_back_ios_24),
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp, vertical = 10.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Notifications",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.SemiBold
                ),
                color = Color.Black
            )

            NotificationItem(
                title = "Missed quiz in physics in yesterday",
                timeAgo = "2 hours ago",
                backgroundColor = Color(0xFFFFF4E9),
                stripeColor = Orange
            )

            NotificationItem(
                title = "Badge earned",
                timeAgo = "8 hours ago",
                backgroundColor = Color(0xFFF9F1FF),
                stripeColor = Color(0xFFB388FF)
            )

            NotificationItem(
                title = "Teacher Note",
                timeAgo = "1 day ago",
                backgroundColor = Color(0xFFE9FFF2),
                stripeColor = Green
            )

            Spacer(modifier = Modifier.height(8.dp))

            // -------- Settings section ----------
            Text(
                text = "Settings",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.SemiBold
                ),
                color = Color.Black
            )

            SettingRow(
                painter = painterResource(id = R.drawable.ic_switchchild),
                title = "Switch Child",
                subtitle = "Change active child profile"
            )

            SettingRow(
                painter = painterResource(id = R.drawable.ic_language),
                title = "Language",
                subtitle = "English"
            )

            SettingRow(
                painter = painterResource(id = R.drawable.ic_logout),
                title = "Logout",
                subtitle = "Sign out of your account",
                titleColor = Red,
                iconTint = Color(0xFFE53935),
                onClick = onLogoutClick
            )

        }
    }
}




@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun SettingsScreenPreview() {
    QuizzyTheme {
        SettingsScreen()
    }
}
