package com.example.indeedproject

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.indeedproject.databinding.ActivityNotificationsBinding

class Notifications : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityNotificationsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent{
            NotificationsScreen()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_notifications)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}

@Composable
fun NotificationsScreen() {
    Column {
        ViewNotificationsTitleBar()
        ViewNotifications()
    }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewNotificationsTitleBar() {
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
fun ViewNotifications()
{
    val image = painterResource(R.drawable.notification)
    Column(
        modifier = Modifier
            .fillMaxSize() // Fill the entire screen
            .padding(16.dp),
        verticalArrangement = Arrangement.Center, // Center vertically
        horizontalAlignment = Alignment.CenterHorizontally // Center horizontally
    ) {
        Image(
            painter = image,
            contentDescription = "notification",
        )
        Text(
            text = "Nothing right now. Check back later!",
            fontWeight = FontWeight.ExtraBold,
            fontSize = 25.sp,
            modifier = Modifier
                .padding(16.dp)
        )
        Text(
            text = stringResource(R.string.default_notification_2),
            fontSize = 15.sp,
            modifier = Modifier
                .padding(16.dp)
        )
    }
}