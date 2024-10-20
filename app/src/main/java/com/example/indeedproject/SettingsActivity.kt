@file:Suppress("DEPRECATION")

package com.example.indeedproject

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.preference.PreferenceFragmentCompat


class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent{
            SettingsFullScreen()
        }
    }

    class SettingsFragment : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey)
        }
    }
}

@Composable
fun SettingsFullScreen() {
    Column {
        // Display the top app bar first
        ViewTitleBar()

        // Display the settings text separately below
        Settings()

        Spacer(modifier = Modifier.height(10.dp))

    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewTitleBar() {
    val context = LocalContext.current
    Scaffold(
        modifier = Modifier.height(50.dp),
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        horizontalArrangement = Arrangement.Start,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        IconButton(onClick = {}) {
                            Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                        }
                        IconButton(onClick = {
                            val intent = Intent(context, MainActivity::class.java)
                            context.startActivity(intent)
                        }) {
                            Icon(Icons.Default.Home, contentDescription = "Home")
                        }
                    }
                },
                actions = {
                    IconButton(
                        onClick = {
                        val intent = Intent(context, Messages::class.java)
                        context.startActivity(intent)
                        }
                    ) {
                        Icon(Icons.Default.Email, contentDescription = "Message")
                    }
                    IconButton(
                        onClick = {
                            val intent = Intent(context, Notifications::class.java)
                            context.startActivity(intent)
                        }
                    ) {
                        Icon(Icons.Default.Notifications, contentDescription = "Notifications")
                    }
                    IconButton(onClick = {}) {
                        Icon(Icons.Default.List, contentDescription = "Menu")
                    }
                }
            )
        }
    ){
        // Add padding to allow space for content below the TopAppBar
        it.calculateBottomPadding()
    }
}


@Composable
fun Settings() {
    // Now this will be displayed separately from the top bar
    Column(
        modifier = Modifier
            .padding(25.dp)
    ) {
        // Settings Title
        Text(
            text = "Settings",
            fontWeight = FontWeight.ExtraBold,
            fontSize = 32.sp,
            modifier = Modifier.padding(bottom = 16.dp) // Add padding below title
        )

        // Settings options in a vertical layout
        SettingOption(
            icon1 = Icons.Default.AccountCircle,
            label1 = "Account Settings",
            icon2 = Icons.Default.ArrowForward,
            label2 = "Your contact information",
            onClick = { /* Handle Account Settings click */ }
        )
        HorizontalLine()
        SettingOption(
            icon1 = Icons.Default.Build,
            label1 = "Security Settings",
            icon2 = Icons.Default.ArrowForward,
            label2 = "Manage your account security",
            onClick = { /* Handle Security Settings click */ }
        )
        HorizontalLine()
        SettingOption(
            icon1 = Icons.Default.Email,
            label1 = "Email Settings",
            icon2 = Icons.Default.ArrowForward,
            label2 = "Preferences for email messages",
            onClick = { /* Handle Email Settings click */ }
        )
        HorizontalLine()
        SettingOption(
            icon1 = Icons.Default.Info,
            label1 = "Device Management",
            icon2 = Icons.Default.ArrowForward,
            label2 = "Manage your active devices",
            onClick = { /* Handle Device Management click */ }
        )
        HorizontalLine()
        SettingOption(
            icon1 = Icons.Default.CheckCircle,
            label1 = "Privacy Settings",
            icon2 = Icons.Default.ArrowForward,
            label2 = "Information about your Privacy",
            onClick = { /* Handle Privacy Settings click */ }
        )
        HorizontalLine()
    }
}


@Composable
fun SettingOption(
    icon1: ImageVector,
    label1: String,
    icon2: ImageVector,
    label2: String,
    onClick: () -> Unit
) {
    // Wrap the entire Row inside the IconButton
    IconButton(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)) {
        Row(
            modifier = Modifier
                .padding(vertical = 8.dp) // Padding around the row
                .fillMaxWidth(), // Ensures the row takes full width
            verticalAlignment = Alignment.CenterVertically // Align items vertically
        ) {
            // First Icon
            Icon(
                icon1,
                contentDescription = label1
            )

            // Column for Text
            Column(
                modifier = Modifier
                    .padding(start = 10.dp)
                    .weight(1f) // Allow the Column to take remaining space
            ) {
                // Text for label1
                Text(
                    text = label1,
                    fontSize = 20.sp, // Adjust font size as needed
                    fontWeight = FontWeight.ExtraBold
                )
                // Add padding to label2 for visibility
                Text(
                    text = label2,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.padding(top = 2.dp) // Add some space above label2
                )
            }

            // Second Icon
            Icon(
                icon2,
                contentDescription = label2
            )
        }
    }
}
