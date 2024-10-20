package com.example.indeedproject

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.ui.AppBarConfiguration
import com.example.indeedproject.databinding.ActivityNvDrawerBinding
import kotlinx.coroutines.launch


class NvDrawer : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityNvDrawerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}



@OptIn(ExperimentalMaterial3Api::class) // Required for using ModalNavigationDrawer
@Composable
fun NavigationMenu(onOpenDrawer: () -> Unit) {
    // State to manage drawer state
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

    // ModalNavigationDrawer
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                // Drawer Header
                Text("Drawer title", modifier = Modifier.padding(16.dp))
                HorizontalDivider()
                // Drawer Items
                NavigationDrawerItem(
                    label = { Text(text = "Home") },
                    selected = false,
                    onClick = { /* Handle Home Click */ }
                )
                NavigationDrawerItem(
                    label = { Text(text = "Profile") },
                    selected = false,
                    onClick = { /* Handle Profile Click */ }
                )
                // Add more items as needed...
            }
        }
    ) {
        // Main content of the screen
        Box(modifier = Modifier.fillMaxSize()) {
            // Button to open the drawer (optional, for demonstration)
            Button(onClick = {
                coroutineScope.launch { drawerState.open() }
            }) {
                Text("Open Drawer")
            }
        }

        // This function will be called when the IconButton is clicked
        LaunchedEffect(drawerState.isOpen) {
            if (drawerState.isOpen) {
                onOpenDrawer() // Call the passed function to handle open state
            }
        }
    }
}

@Composable
fun HorizontalDivider() {
    Divider(modifier = Modifier.padding(vertical = 8.dp))
}