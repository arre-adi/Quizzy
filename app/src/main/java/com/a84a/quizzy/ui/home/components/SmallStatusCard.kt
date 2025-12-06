package com.a84a.quizzy.ui.home.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.a84a.quizzy.ui.theme.Grey

@Composable
fun SmallStatusCard(
    icon: Int,
    title: String,
    value: String,
    borderColor: Color
) {
    Surface(
        modifier = Modifier
            .height(105.dp),
        shape = RoundedCornerShape(16.dp),
        color = borderColor.copy(alpha = 0.2f),
        shadowElevation = 0.dp,
        border = BorderStroke(1.dp, borderColor)
    ) {
        Column(
            modifier = Modifier
                .width(112.dp)
                .padding(horizontal = 12.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            // ICON
            Image(
                painter = painterResource(id = icon),
                contentDescription = null,
                modifier = Modifier
                    .size(20.dp)
            )

            // TITLE
            Text(
                text = title,
                style = MaterialTheme.typography.labelLarge,
                color = Grey
            )

            // VALUE
            Text(
                text = value,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.SemiBold
                ),
                color = Color.Black
            )
        }
    }
}
