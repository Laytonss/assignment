package com.thoughtworks.assignment.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.thoughtworks.assignment.ui.utils.Utils.getCurrentRoute

@Composable
fun AppBottomBar(navController: NavHostController) {
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
                    onClick = { navController.navigate("Chats") },
                    icon = Icons.Filled.Chat,
                    text = "Chats",
                    isSelected = getCurrentRoute(navController) == "Chats"
                )
                BottomBarItem(
                    modifier = weightModifier,
                    onClick = { navController.navigate("Contacts") },
                    icon = Icons.Filled.Group,
                    text = "Contacts",
                    isSelected = getCurrentRoute(navController) == "Contacts"
                )
                BottomBarItem(
                    modifier = weightModifier,
                    onClick = { navController.navigate("Discover") },
                    icon = Icons.Filled.Explore,
                    text = "Discover",
                    isSelected = getCurrentRoute(navController) == "Discover"
                )
                BottomBarItem(
                    modifier = weightModifier,
                    onClick = { navController.navigate("Me") },
                    icon = Icons.Filled.Person,
                    text = "Me",
                    isSelected = getCurrentRoute(navController) == "Me"
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
    val iconAndTextColor = if (isSelected) {
        Color.Green
    } else {
        Color.White
    }
    Column(
        modifier = modifier.clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(icon, contentDescription = text, tint = iconAndTextColor)
        Text(text = text, color = iconAndTextColor)
    }
}

