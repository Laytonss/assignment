package com.thoughtworks.assignment.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign


enum class BottomNavigationItem {
    CHATS, CONTACTS, DISCOVER, ME
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApp() {
    val (selectedItem, setSelectedItem) = remember { mutableStateOf(BottomNavigationItem.DISCOVER) }
    val title = when (selectedItem) {
        BottomNavigationItem.CHATS -> "Chats"
        BottomNavigationItem.CONTACTS -> "Contacts"
        BottomNavigationItem.DISCOVER -> "Discover"
        BottomNavigationItem.ME -> "Me"
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = title) },
                colors = topAppBarColors(containerColor = Color.Black, titleContentColor = Color.White)
            )
        },
        bottomBar = {
            AppBottomBar(selectedItem) { newItem -> setSelectedItem(newItem) }
        },
    ) { innerPadding ->
        when (selectedItem) {
            BottomNavigationItem.CHATS -> {
                EmptyPage()
            }

            BottomNavigationItem.CONTACTS -> {
                EmptyPage()
            }

            BottomNavigationItem.DISCOVER -> {
                DiscoverPage(modifier = Modifier.padding(innerPadding))
            }

            BottomNavigationItem.ME -> {
                EmptyPage()
            }
        }
    }
}

@Composable
fun EmptyPage() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Gray)
    ) {
    }
}