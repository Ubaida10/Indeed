package com.example.indeedproject

import android.app.Activity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class SecuritySettings : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SecuritySettingsScreen()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecuritySettingsScreen() {
    val context = LocalContext.current
    val activity = context as? Activity

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Top App Bar
        CenterAlignedTopAppBar(
            title = { Text("Settings", fontSize = 20.sp, fontWeight = FontWeight.SemiBold) },
            navigationIcon = {
                IconButton(onClick = { (context as? Activity)?.finish() }) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                }
            },
            actions = {
                IconButton(onClick = { activity?.finish() }) {
                    Icon(Icons.Filled.Close, contentDescription = "Close")
                }
            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = Color.Transparent,
                titleContentColor = Color.Black
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Section Title with "New" label
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Security settings",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "New",
                color = Color.Blue,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .padding(4.dp)
                    .background(Color(0xFFE0F2FF), shape = MaterialTheme.shapes.small)
                    .padding(horizontal = 8.dp, vertical = 2.dp)
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Account protection option
        SettingOption("Account protection")

        Divider(modifier = Modifier.padding(vertical = 8.dp), color = Color(0xFFE0E0E0))

        // Third-party apps section with description
        SettingOption("Third-party apps")
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "No third-party applications have access to your account",
            fontSize = 13.sp,
            color = Color.Gray,
            //modifier = Modifier.padding(start = 16.dp)
        )

        Divider(modifier = Modifier.padding(vertical = 8.dp), color = Color(0xFFE0E0E0))

        Spacer(modifier = Modifier.padding(top=64.dp))
        // Email section
        Text(
            text = "abuubaida22dec@gmail.com",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Expandable Sections
        Section1(title = "Job Seekers")
        Divider(modifier = Modifier.padding(vertical = 8.dp), color = Color(0xFFE0E0E0))
        Section1(title = "Employers")
        Divider(modifier = Modifier.padding(vertical = 8.dp), color = Color(0xFFE0E0E0))
        Section1(title = "About")

        Spacer(modifier = Modifier.height(24.dp))

        // Footer
        Text(
            text = "©2024 Indeed",
            fontSize = 12.sp,
            color = Color.Gray,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Footer links
        Row(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            FooterLink("Accessibility at Indeed")
            FooterLink("Privacy Center and Ad Choices")
        }
    }
}

@Composable
fun SettingOption(title: String) {
    Text(
        text = title,
        fontSize = 16.sp,
        fontWeight = FontWeight.Medium,
        color = Color.Black,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* Handle click */ }
            .padding(vertical = 12.dp)
    )
}

@Composable
fun Section1(title: String) {
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { expanded = !expanded }
            .padding(vertical = 8.dp)
    ) {
        Text(
            text = title,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black
        )

        if (expanded) {
            Text(
                text = "Additional options",
                fontSize = 13.sp,
                color = Color.Gray,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}

@Composable
fun FooterLink(text: String) {
    ClickableText(
        text = AnnotatedString(text),
        onClick = { /* Handle click */ },
        style = LocalTextStyle.current.copy(
            fontSize = 12.sp,
            color = Color.Gray
        )
    )
}