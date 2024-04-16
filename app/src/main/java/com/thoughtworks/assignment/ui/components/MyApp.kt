package com.thoughtworks.assignment.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color



enum class BottomNavigationItem {
    CHATS, CONTACTS, DISCOVER, ME
}

@Composable
fun MyApp() {
    val (selectedItem, setSelectedItem) = remember { mutableStateOf(BottomNavigationItem.DISCOVER) }
    Scaffold(
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
            .background(color = Color.Black)
    ) {
    }
}