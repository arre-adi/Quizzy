import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.a84a.quizzy.ui.login.LoginViewModel
import com.a84a.quizzy.ui.theme.QuizzyTheme
import com.a84a.quizzy.R
import com.a84a.quizzy.ui.theme.Red
import com.a84a.quizzy.ui.theme.White

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = viewModel(),
    onLoginSuccess: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(uiState.loggedIn) {
        if (uiState.loggedIn) {
            onLoginSuccess()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {

            // Top creative
            Box(
                modifier = Modifier.wrapContentSize(),
            ) {
                Image(
                    painter = painterResource(id = R.drawable.bg_pattern),
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.Crop,
                )
                Image(
                    painter = painterResource(id = R.drawable.top_creative),
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.Crop,
                )
            }

            // Title
            Text(
                text = "Welcome to \nQuizzy!",
                textAlign = TextAlign.Center,
                color = White,
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold
            )

            // Bottom card
            Box(
                modifier = Modifier.wrapContentSize(),
            ) {
                Image(
                    painter = painterResource(id = R.drawable.login_card_bg),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 6.dp),
                    contentScale = ContentScale.Crop,
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Let's Get you Signed in",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.W700
                        ),
                        color = Color.Black,
                        modifier = Modifier.padding(bottom = 24.dp)
                    )

                    QuizzyTextField(
                        value = uiState.schoolId,
                        onValueChange = viewModel::onSchoolIdChange,
                        label = "School ID"
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    QuizzyTextField(
                        value = uiState.studentId,
                        onValueChange = viewModel::onStudentIdChange,
                        label = "Student ID"
                    )

                    Spacer(modifier = Modifier.height(30.dp))

                    Button(
                        onClick = { viewModel.onLoginClick() },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent
                        ),
                        shape = RoundedCornerShape(28.dp)
                    ) {
                        Text(
                            text = "Sign in",
                            color = White,
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontWeight = FontWeight.SemiBold
                            )
                        )
                    }

                    uiState.error?.let { errorMsg ->
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = errorMsg,
                            color = Red,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginScreenPreview() {
    QuizzyTheme {
        LoginScreen(onLoginSuccess = {})
    }
}
