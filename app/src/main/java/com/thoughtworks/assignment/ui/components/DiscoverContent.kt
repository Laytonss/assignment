package com.thoughtworks.assignment.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.LiveTv
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.Radar
import androidx.compose.material.icons.filled.WifiChannel
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.thoughtworks.assignment.ui.theme.GrayBackground

@Composable
fun DiscoverPage(navController: NavHostController, modifier: Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        DiscoverItem(Icons.Filled.Language, Color.Cyan, "Moments") { navController.navigate("Moments") }
        Spacer(modifier = Modifier.height(5.dp))
        DiscoverItem(Icons.Filled.WifiChannel, Color.Yellow, "Channels")
        DiscoverItem(Icons.Filled.Radar, Color.Red, "Live")
        Spacer(modifier = Modifier.height(5.dp))
        DiscoverItem(Icons.Filled.LiveTv, Color.Blue, "Scan")
        DiscoverItem(Icons.Filled.MusicNote, Color.Red, "Listen")
    }
}

@Composable
fun DiscoverItem(
    icon: ImageVector,
    iconColor: Color,
    text: String,
    onClick: () -> Unit = {},
) {
    Row(
        modifier = Modifier
            .clickable { onClick() }
            .fillMaxWidth()
            .height(60.dp)
            .background(GrayBackground),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(icon, contentDescription = text, tint = iconColor, modifier = Modifier.padding(start = 10.dp))
        Text(
            text = text, color = Color.White, modifier = Modifier
                .weight(1f)
                .padding(start = 20.dp)
        )
        Icon(Icons.Filled.ArrowForwardIos, contentDescription = text, tint = Color.Gray, modifier = Modifier.padding(end = 10.dp))
    }
}