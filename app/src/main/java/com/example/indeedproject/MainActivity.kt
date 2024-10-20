package com.example.indeedproject

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.indeedproject.ui.theme.IndeedProjectTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IndeedProjectTheme {
                Surface (
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ){
                    DisplayTitlePage()
                    //LoginScreen()
                    //ViewProfile()
                }
            }
        }
    }
}

@Composable
fun DisplayTitlePage()
{
    Column {
        Spacer(modifier = Modifier.height(15.dp))
        MainScreenTitleBar()
        Spacer(modifier = Modifier.padding(5.dp))
        //JobFeedScreen()
    }
}

@SuppressLint("SuspiciousIndentation", "UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenTitleBar() {
    val image: Painter = painterResource(R.drawable.indeed)
    val context = LocalContext.current

    // Create a drawer state
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

    // Modal Navigation Drawer
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                // Your drawer items go here
                ClickableText(
                    AnnotatedString("Home"),
                    onClick = {
                        val intent = Intent(context, MainActivity::class.java)
                        context.startActivity(intent)
                    },
                    modifier = Modifier.padding(16.dp)
                )
                ClickableText(
                    AnnotatedString("Profile"),
                    onClick = {},
                    modifier = Modifier.padding(16.dp)
                )
                ClickableText(
                    AnnotatedString("Settings"),
                    onClick = {
                        val intent = Intent(context, SettingsActivity::class.java) // Replace with your target activity
                        context.startActivity(intent) // Start the activity using the context
                    },
                    modifier = Modifier.padding(16.dp)
                )
                ClickableText(
                    AnnotatedString("Logout"),
                    onClick = {},
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(), // Change height to fillMaxSize
            topBar = {
                TopAppBar(
                    title = {
                        Image(
                            painter = image,
                            contentDescription = "logo",
                            modifier = Modifier.size(85.dp)
                        )
                    },
                    actions = {
                        IconButton(onClick = {
                            val intent = Intent(context, Messages::class.java)
                            context.startActivity(intent)
                        }) {
                            Icon(Icons.Default.Email, contentDescription = "Message")
                        }
                        IconButton(onClick = {
                            val intent = Intent(context, Notifications::class.java)
                            context.startActivity(intent)
                        }) {
                            Icon(Icons.Default.Notifications, contentDescription = "Notifications")
                        }
                        IconButton(onClick = {
                            coroutineScope.launch {
                                drawerState.open()
                            }
                        }) {
                            Icon(Icons.Default.List, contentDescription = "Menu")
                        }
                    }
                )
            }
        ) { innerPadding ->
            // Apply padding to JobFeedScreen
            JobFeedScreen(modifier = Modifier.padding(innerPadding))
        }
    }
}

@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JobFeedScreen(modifier: Modifier) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        SearchSection()
        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "We're working on your personalized job feed",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = "In the meantime, run a search to find your next job.",
            fontSize = 16.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { /* Perform job search */ },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Blue,
                contentColor = Color.White
            )


        ) {
            Text(
                text = "Find Jobs"
            )
        }
        Spacer(modifier = Modifier.height(90.dp))

        ProfileDetail()
        HorizontalLine()
        Spacer(modifier = Modifier.height(10.dp))

        JobSeekers()
        HorizontalLine()
        Spacer(modifier = Modifier.height(10.dp))

        Employers()
        HorizontalLine()
        Spacer(modifier = Modifier.height(10.dp))

        About()
        Spacer(modifier = Modifier.padding(10.dp))
        Footer()
    }
}




@Composable
fun JobSeekers() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically, // Ensure vertical centering
        modifier = Modifier.fillMaxWidth()
    ){
        Text(
            text = "Job Seekers",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            modifier = Modifier.weight(1f)
        )

        Icon(
            Icons.Default.KeyboardArrowDown,
            contentDescription = "Expand",
            modifier = Modifier
                .size(30.dp)
                .padding(end=16.dp),
        )
    }
}


@Composable
private fun ProfileDetail() {
    val context = LocalContext.current
    Row (
        horizontalArrangement = Arrangement.SpaceBetween, // Change to SpaceBetween for better alignment
        verticalAlignment = Alignment.CenterVertically, // Ensure vertical centering
        modifier = Modifier.fillMaxWidth()
    ){
        Text(
            text="My Email Id",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            modifier = Modifier.weight(1f)
        )
        IconButton(onClick = {
            // Create the intent to navigate to another activity
            val intent = Intent(context, SettingsActivity::class.java)
            context.startActivity(intent) // Start the activity using the context
        }) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = "Expand",
                modifier = Modifier
                    .size(35.dp)
                    .padding(end = 16.dp),
            )
        }
    }
}

@Composable
private fun SearchSection() {
    val searchState = remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(8.dp) // Padding for the entire search box
            .background(Color.Transparent)
            .border(width=2.dp, color = Color.Black, shape = MaterialTheme.shapes.large)
    ) {
        // Search Icon
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = "Search",
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 16.dp)
                .size(24.dp)
        )

        // BasicTextField
        BasicTextField(
            value = searchState.value,
            onValueChange = { searchState.value = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 56.dp) // Adjust padding to prevent overlap with the icon
                .align(Alignment.Center), // Center the text field vertically
            //textStyle = LocalTextStyle.current.copy(color = Color.White) // Set text color for user input
        ) { innerTextField ->
            if (searchState.value.isEmpty()) {
                Text(
                    text = "Search",
                    color = Color.Gray// Change the text color for the placeholder
                )
            }
            innerTextField() // Call innerTextField to display the current value
        }
    }
}


@Composable
fun Employers() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween, // Change to SpaceBetween for better alignment
        verticalAlignment = Alignment.CenterVertically, // Ensure vertical centering
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Employers",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(1f)
        )
        Icon(
            imageVector = Icons.Default.KeyboardArrowDown,
            contentDescription = "Expand",
            modifier = Modifier
                .size(30.dp)
                .padding(end = 16.dp), // Add some padding to the right
        )
    }
}



@Composable
fun About() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween, // Change to SpaceBetween for better alignment
        verticalAlignment = Alignment.CenterVertically, // Ensure vertical centering
        modifier = Modifier
            .fillMaxWidth()

    ){
        Text(
            text = "About",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )
        Icon(
            Icons.Default.KeyboardArrowDown,
            contentDescription = "Expand",
            modifier = Modifier
                .size(30.dp)
                .padding(end=16.dp)

        )
    }
}


@Composable
fun HorizontalLine() {
    Divider(
        color = Color.Gray, // Set the line color
        modifier = Modifier.padding(vertical = 5.dp) // Optional: Add padding around the line
    )
}

@Composable
fun Footer() {
    Column (
        modifier = Modifier.fillMaxWidth(),
    ){
        Text(text = "©2024 Indeed", color = Color.Gray)
        Text(text = "Privacy Center and Ad Choices", color = Color.Gray)
        Text(text = "Terms", color = Color.Gray)
    }
}

@Preview(showBackground = true)
@Composable
fun JobFeedScreenPreview() {
    IndeedProjectTheme {
        JobFeedScreen(modifier = Modifier)
    }
}