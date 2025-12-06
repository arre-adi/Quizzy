import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.a84a.quizzy.ui.theme.Grey
import com.a84a.quizzy.ui.theme.White

@Composable
fun QuizzyTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String
) {
    var isFocused by remember { mutableStateOf(false) }

    val borderColor = if (isFocused || value.isNotEmpty()) Color.Black else Grey

    TextField(
        value = value,
        onValueChange = onValueChange,
        label = if (!isFocused && value.isEmpty()) {
            { Text(label, color = Grey, style = MaterialTheme.typography.bodyMedium) }
        } else null,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .border(1.dp, borderColor, RoundedCornerShape(12.dp))
            .onFocusChanged { isFocused = it.isFocused },
        singleLine = true,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = White,
            unfocusedContainerColor = Color(0xFFF5F5F5),
            focusedIndicatorColor = Color.Transparent, // Hide default indicator
            unfocusedIndicatorColor = Color.Transparent, // Hide default indicator
            cursorColor = Color.Black,
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black
        ),
        shape = RoundedCornerShape(12.dp)
    )
}

@Preview(showBackground = true)
@Composable
private fun QuizzyTextFieldPreview() {
    var schoolId by rememberSaveable { mutableStateOf("") }
    QuizzyTextField(
        value = schoolId,
        onValueChange = { schoolId = it },
        label = "School ID"
    )
}
