package com.thoughtworks.assignment.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Contacts
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
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
        actions = {
            IconButton(onClick = { onItemSelected(BottomNavigationItem.CHATS) }, modifier = Modifier.weight(1f)) {
                Column {
                    Icon(Icons.Filled.Chat, contentDescription = "Localized description")
                    Text(text = "Chats")
                }
            }
            IconButton(onClick = { onItemSelected(BottomNavigationItem.CONTACTS) }, modifier = Modifier.weight(1f)) {
                Column {
                    Icon(Icons.Filled.People, contentDescription = "Localized description")
                    Text(text = "Contacts")
                }
            }
            IconButton(onClick = { onItemSelected(BottomNavigationItem.DISCOVER) }, modifier = Modifier.weight(1f)) {
                Column {
                    Icon(Icons.Filled.Explore, contentDescription = "Localized description")
                    Text(text = "Discover")
                }
            }
            IconButton(onClick = { onItemSelected(BottomNavigationItem.ME) }, modifier = Modifier.weight(1f)) {
                Column {
                    Icon(Icons.Filled.Person, contentDescription = "Localized description")
                    Text(text = "Me")
                }
            }
        },
    )
}

@Composable
fun DiscoverPage(modifier: Modifier) {

}

@Composable
fun EmptyPage() {
}