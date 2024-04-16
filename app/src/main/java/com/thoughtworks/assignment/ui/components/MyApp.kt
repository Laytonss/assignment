package com.thoughtworks.assignment.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp


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
fun AppBottomBar(selectedItem: BottomNavigationItem, onItemSelected: (BottomNavigationItem) -> Unit) {
    BottomAppBar(
        containerColor = Color(0xFF19191E),
        actions = {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(5.dp)
            ) {
                val weightModifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
                BottomBarItem(
                    modifier = weightModifier,
                    onClick = { onItemSelected(BottomNavigationItem.CHATS) },
                    icon = Icons.Filled.Chat,
                    text = "Chats",
                    isSelected = selectedItem == BottomNavigationItem.CHATS
                )
                BottomBarItem(
                    modifier = weightModifier,
                    onClick = { onItemSelected(BottomNavigationItem.CONTACTS) },
                    icon = Icons.Filled.Group,
                    text = "Contacts",
                    isSelected = selectedItem == BottomNavigationItem.CONTACTS
                )
                BottomBarItem(
                    modifier = weightModifier,
                    onClick = { onItemSelected(BottomNavigationItem.DISCOVER) },
                    icon = Icons.Filled.Explore,
                    text = "Discover",
                    isSelected = selectedItem == BottomNavigationItem.DISCOVER
                )
                BottomBarItem(
                    modifier = weightModifier,
                    onClick = { onItemSelected(BottomNavigationItem.ME) },
                    icon = Icons.Filled.Person,
                    text = "Me",
                    isSelected = selectedItem == BottomNavigationItem.ME
                )
            }
        }
    )
}

@Composable
fun BottomBarItem(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    icon: ImageVector,
    text: String,
    isSelected: Boolean
) {
    val currentColor = if (isSelected) {
        Color.Green
    } else {
        Color.White
    }
    Column(
        modifier = modifier.clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(icon, contentDescription = text, tint = currentColor)
        Text(text = text, color = currentColor)
    }
}

@Composable
fun DiscoverPage(modifier: Modifier) {

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