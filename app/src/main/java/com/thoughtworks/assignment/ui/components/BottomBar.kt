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
import com.thoughtworks.assignment.Constants
import com.thoughtworks.assignment.ui.theme.GrayBlackGround
import com.thoughtworks.assignment.ui.utils.Utils.getCurrentRoute

@Composable
fun AppBottomBar(navController: NavHostController) {
    BottomAppBar(
        containerColor = GrayBlackGround,
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
                    onClick = { navController.navigate(Constants.BOTTOM_CHATS) },
                    icon = Icons.Filled.Chat,
                    text = Constants.BOTTOM_CHATS,
                    isSelected = getCurrentRoute(navController) == Constants.BOTTOM_CHATS
                )
                BottomBarItem(
                    modifier = weightModifier,
                    onClick = { navController.navigate(Constants.BOTTOM_CONTACTS) },
                    icon = Icons.Filled.Group,
                    text = Constants.BOTTOM_CONTACTS,
                    isSelected = getCurrentRoute(navController) == Constants.BOTTOM_CONTACTS
                )
                BottomBarItem(
                    modifier = weightModifier,
                    onClick = { navController.navigate(Constants.BOTTOM_DISCOVER) },
                    icon = Icons.Filled.Explore,
                    text = Constants.BOTTOM_DISCOVER,
                    isSelected = getCurrentRoute(navController) == Constants.BOTTOM_DISCOVER
                )
                BottomBarItem(
                    modifier = weightModifier,
                    onClick = { navController.navigate(Constants.BOTTOM_ME) },
                    icon = Icons.Filled.Person,
                    text = Constants.BOTTOM_ME,
                    isSelected = getCurrentRoute(navController) == Constants.BOTTOM_ME
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

