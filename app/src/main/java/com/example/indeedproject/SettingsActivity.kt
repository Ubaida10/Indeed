@file:Suppress("DEPRECATION")

package com.example.indeedproject

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
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
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.preference.PreferenceFragmentCompat
import kotlinx.coroutines.launch


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
        //Settings()

        Spacer(modifier = Modifier.height(10.dp))

    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewTitleBar() {
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
                        Row(
                            horizontalArrangement = Arrangement.Start,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            IconButton(
                                onClick = {
                                    (context as? Activity)?.finish()
                                }
                            ) {
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
            Settings(modifier = Modifier.padding(innerPadding))
        }
    }
}


@Composable
fun Settings(modifier: Modifier) {
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
        var context = LocalContext.current
        // Settings options in a vertical layout
        SettingOption(
            icon1 = Icons.Default.AccountCircle,
            label1 = "Account Settings",
            icon2 = Icons.Default.ArrowForward,
            label2 = "Your contact information",
            onClick = {
                val intent = Intent(context, AccountSettings::class.java)
                context.startActivity(intent) // Start the activity using the context
            }
        )
        HorizontalLine()
        context = LocalContext.current
        SettingOption(
            icon1 = Icons.Default.Build,
            label1 = "Security Settings",
            icon2 = Icons.Default.ArrowForward,
            label2 = "Manage your account security",
            onClick = {
                val intent = Intent(context, SecuritySettings::class.java)
                context.startActivity(intent) // Start the activity using the context
            }
        )
        context = LocalContext.current
        HorizontalLine()
        SettingOption(
            icon1 = Icons.Default.Email,
            label1 = "Email Settings",
            icon2 = Icons.Default.ArrowForward,
            label2 = "Preferences for email messages",
            onClick = { /* Handle Email Settings click */ }
        )
        HorizontalLine()
        context = LocalContext.current
        SettingOption(
            icon1 = Icons.Default.Info,
            label1 = "Device Management",
            icon2 = Icons.Default.ArrowForward,
            label2 = "Manage your active devices",
            onClick = {
                val intent = Intent(context, DeviceManagment::class.java)
                context.startActivity(intent) // Start the activity using the context
            }
        )
        HorizontalLine()
        context = LocalContext.current
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
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon1,
            contentDescription = null,
            modifier = Modifier.padding(end = 16.dp)
        )
        Column(modifier = Modifier.weight(1f)) {
            Text(text = label1, fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Text(text = label2, color = Color.Gray, fontSize = 14.sp)
        }
        Icon(imageVector = icon2, contentDescription = null)
    }
}