package com.example.indeedproject

import android.app.Activity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.ui.AppBarConfiguration
import com.example.indeedproject.databinding.ActivityDeviceManagmentBinding
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp


class DeviceManagment : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityDeviceManagmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DeviceManagementScreen()
        }

    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeviceManagementScreen() {
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
                IconButton(
                    onClick = { (context as? Activity)?.finish()
                    }
                ) {
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

        // Page Title
        Text(
            text = "Device management",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Description
        Text(
            text = "You are currently signed into your Indeed account on these devices.",
            fontSize = 14.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Device Information Table
        DeviceInfoTable()

        Spacer(modifier = Modifier.height(64.dp))

        // Other Settings and Links
        Text(
            text = "abuubaida22dec@gmail.com",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(16.dp))

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
fun DeviceInfoTable() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF5F5F5), shape = MaterialTheme.shapes.small)
            .padding(16.dp)
    ) {
        DeviceInfoRow(label = "Device", value = "Chrome Mobile\nAndroid")
        Divider(color = Color(0xFFE0E0E0))
        DeviceInfoRow(label = "Date Logged In", value = "November 3, 2024")
        Divider(color = Color(0xFFE0E0E0))
        DeviceInfoRow(label = "IP Address", value = "202.142.155.238\nLahore")
        Divider(color = Color(0xFFE0E0E0))
        DeviceInfoRow(label = "Actions", value = "This device", isItalic = true)
    }
}

@Composable
fun DeviceInfoRow(label: String, value: String, isItalic: Boolean = false) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween, // Ensure even spacing
        verticalAlignment = Alignment.Top // Align items to the top of the row
    ) {
        Text(
            text = label,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = value,
            fontSize = 14.sp,
            fontWeight = if (isItalic) FontWeight.Light else FontWeight.Normal,
            color = if (isItalic) Color.Gray else Color.Black,
            modifier = Modifier.weight(1.5f) // Adjust weight to fit the content
        )
    }
}
