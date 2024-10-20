package com.example.indeedproject

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import kotlinx.coroutines.launch
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController



import com.example.indeedproject.databinding.ActivityMessagesBinding
import kotlinx.coroutines.launch

class Messages : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMessagesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent{
            MessageFullScreen()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_messages)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}



@Composable
fun MessageFullScreen() {
    Column {
        // Display the top app bar first
        ViewMessageTitleBar()

        Spacer(modifier = Modifier.height(10.dp))

        DisplayMessages()
    }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewMessageTitleBar() {
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
fun DisplayMessages()
{
    Options()
}


@Composable
fun Options() {
    val radioOptions = listOf("Inbox", "Archive", "Spam")
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[0]) }

    Column(
        verticalArrangement = Arrangement.Center, // Center vertically
        horizontalAlignment = Alignment.CenterHorizontally, // Center horizontally
        modifier = Modifier
            .fillMaxSize()
            //.padding(16.dp)
    ) {
        // Display radio buttons in a Row (to arrange them horizontally)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly // Even spacing between radio buttons
        ) {
            radioOptions.forEach { option ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 8.dp) // Horizontal padding
                ) {
                    RadioButton(
                        selected = (option == selectedOption),
                        onClick = { onOptionSelected(option) }
                    )
                    Text(
                        text = option,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
        }

        // Add some space between the row of radio buttons and the content
        Spacer(modifier = Modifier.height(16.dp))

        // Conditionally display the content based on the selected radio button
        when (selectedOption) {
            "Inbox" -> Inbox()
            "Archive" -> Archive()
            "Spam" -> Spam()
        }
    }
}

@Composable
fun Inbox() {
    val image = painterResource(id = R.drawable.inbox)
    val description = "Inbox"

    // Center the content both vertically and horizontally
    Column(
        modifier = Modifier
            .fillMaxSize() // Fill the entire screen
            .padding(16.dp),
        verticalArrangement = Arrangement.Center, // Center vertically
        horizontalAlignment = Alignment.CenterHorizontally // Center horizontally
    ) {
        Text(
            text = "Messages",
            fontWeight = FontWeight.ExtraBold,
            fontSize = 32.sp,
            modifier = Modifier.padding(bottom = 16.dp) // Add padding below title
        )

        Image(
            painter = image,
            contentDescription = description,
            modifier = Modifier.size(100.dp)
        )

        Text(
            text = stringResource(R.string.inbox_default),
            modifier = Modifier.padding(bottom = 8.dp)
        )
    }
}


@Composable
fun Archive() {
    val image = painterResource(id = R.drawable.archive)
    val description = "Archive"
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center, // Center vertically
        horizontalAlignment = Alignment.CenterHorizontally // Center horizontally
    ) {
        Text(
            text = "Messages",
            fontWeight = FontWeight.ExtraBold,
            fontSize = 32.sp,
            modifier = Modifier.padding(bottom = 16.dp) // Add padding below title
        )

        Image(
            painter = image,
            contentDescription = description,
            modifier = Modifier.size(100.dp)
        )

        Text(
            text = stringResource(R.string.archive_default),
            modifier = Modifier.padding(bottom = 8.dp)
        )
    }
}

@Composable
fun Spam() {
    val image = painterResource(id = R.drawable.spam)
    val description = "Spam"
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center, // Center vertically
        horizontalAlignment = Alignment.CenterHorizontally // Center horizontally
    ) {
        Text(
            text = "Messages",
            fontWeight = FontWeight.ExtraBold,
            fontSize = 32.sp,
            modifier = Modifier.padding(bottom = 16.dp) // Add padding below title
        )

        Image(
            painter = image,
            contentDescription = description,
            modifier = Modifier.size(100.dp)
        )

        Text(
            text = stringResource(R.string.spam_default),
            modifier = Modifier.padding(bottom = 8.dp)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MessageFullScreen()
}