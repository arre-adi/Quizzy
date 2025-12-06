package com.a84a.quizzy.ui.home.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.a84a.quizzy.R
import com.a84a.quizzy.ui.home.HomeUiState
import com.a84a.quizzy.ui.theme.White

@Composable
fun SummaryCard(
    title: String,
    state: HomeUiState,
    onCtaClick: () -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.W700
            ),
            color = Color.Black,
            modifier = Modifier.padding(bottom = 12.dp),
        )

        Surface(
            shape = RoundedCornerShape(16.dp),
            color = Color(0xA1EFE4FF),
            shadowElevation = 0.dp,
            border = BorderStroke(1.dp,Color(0xFF9C4ACC))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                Image(
                    painter = painterResource(id = R.drawable.focused_emote),
                    contentDescription = null,
                    modifier = Modifier.size(72.dp)
                )

                Text(
                    text = state.todayTitle,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.SemiBold
                    ),
                    color = Color(0xFF7B44C8)
                )

                Text(
                    text = state.todaySubtitle,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color(0xFF555555),
                    textAlign = TextAlign.Center
                )

                Button(
                    onClick = onCtaClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(44.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black
                    )
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_play),
                        contentDescription = null,
                        tint = White,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = state.todayCtaText,
                        color = White,
                        style = MaterialTheme.typography.labelLarge
                    )
                }
            }
        }
    }
}
