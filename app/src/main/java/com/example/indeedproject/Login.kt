package com.example.indeedproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.nio.file.WatchEvent

class Login : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginScreen()
        }
    }
}

@Composable
fun LoginScreen() {
    // Use a white background
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LoginCard()
        Spacer(modifier = Modifier.height(10.dp)) // Increased spacing for better UI
        SimpleTextField()


        Button(
            onClick = { /* Perform login */ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp, top = 5.dp),
            //border = BorderStroke(2.dp, Color.White),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Blue, // Primary color for the button
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(12.dp) // Rounded corners for the button
        ) {
            Text(text = "Continue", fontWeight = FontWeight.Bold)
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleTextField() {
    // State to hold the email input
    val emailState = remember { mutableStateOf("") }
    val isEmailValid = android.util.Patterns.EMAIL_ADDRESS.matcher(emailState.value).matches()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        // Label for the email input field
        Text(
            text = "Email Address: *",
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            fontWeight = FontWeight.Bold
        )

        // Box to handle placeholder and text field together
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(2.dp, Color.Black, shape = MaterialTheme.shapes.large)
                .padding(8.dp) // Add padding inside the border
        ) {
            // BasicTextField for email input
            BasicTextField(
                value = emailState.value,
                onValueChange = { emailState.value = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 4.dp), // Adjust padding to prevent overlap with placeholder

                decorationBox = { innerTextField ->
                    if (emailState.value.isEmpty()) {
                        // Placeholder text when the field is empty
                        Text(
                            text = "Enter your email",
                            color = Color.Gray,
                            modifier = Modifier.padding(start = 4.dp),
                            fontSize = MaterialTheme.typography.bodyLarge.fontSize
                        )
                    }
                    innerTextField() // Display the entered text
                }
            )
        }

        /*// Optional error message if the email is invalid
        if (!isEmailValid && emailState.value.isNotEmpty()) {
            Text(
                text = "Please enter a valid email address",
                color = Color.Red,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(top = 4.dp)
            )
        }*/
    }
}


@Composable
private fun LoginCard() {
    val logo = painterResource(R.drawable.indeed)
    val image = painterResource(R.drawable.google)

    Column(
        modifier = Modifier
            .background(Color.Transparent)
            .padding(top = 2.dp)

    ) {
        Row (
            modifier = Modifier.fillMaxWidth(), // Makes the Row take up the full width
            verticalAlignment = Alignment.CenterVertically // Aligns items vertically in the center of the row
        ){
            IconButton(onClick = {}) {
                Icon(
                    Icons.Default.ArrowBack,
                    contentDescription = "Back"
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Image(
                painter = logo,
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    //.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.weight(1f))
        }
        Text(
            text = stringResource(R.string.heading1),
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = stringResource(R.string.sign_in_head2),
            color = Color.Gray,
            fontWeight = FontWeight.Normal
        )
        Text(
            text = stringResource(R.string.para),
            color = Color.Gray,
            modifier = Modifier.padding(bottom = 16.dp) // Add space below the paragraph
        )
        Button(
            onClick = { /* Continue with google */ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.DarkGray,
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(12.dp) // Rounded corners for button
        ) {
            Box(
                modifier = Modifier
                    .height(40.dp)
                    .padding(8.dp) // Padding for the entire search box

                    //.border(width = 2.dp, color = Color.White, shape = MaterialTheme.shapes.large)
            ){
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Icon(
                        painter = image,
                        contentDescription = "Google",
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .size(20.dp)
                            //.background(color = Color.White)
                    )
                    Text(
                        text = "Continue with Google",
                        modifier = Modifier
                            //.background(color = Color.White)
                    )
                }
            }
        }
        Text(
            text = "or",
            color = Color.Gray,
            modifier = Modifier
                .padding(top = 16.dp)
                .align(Alignment.CenterHorizontally)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}
