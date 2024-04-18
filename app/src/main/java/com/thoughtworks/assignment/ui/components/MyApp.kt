package com.thoughtworks.assignment.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.thoughtworks.assignment.ui.utils.Utils.getCurrentRoute
import com.thoughtworks.assignment.ui.viewmodel.MomentsViewModel


enum class BottomNavigationItem {
    CHATS, CONTACTS, DISCOVER, ME
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApp(
    momentsViewModel: MomentsViewModel = viewModel()
) {
    val homeNavController = rememberNavController()
    NavHost(homeNavController, startDestination = "HomeContent") {
        composable("HomeContent") {
            HomeContent(homeNavController)
        }
        composable("Moments") {
            MomentsPage(momentsViewModel)
        }
    }
}


@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun HomeContent(homeNavController: NavHostController) {
    val navController = rememberNavController()
    val (selectedItem, setSelectedItem) = remember { mutableStateOf(BottomNavigationItem.DISCOVER) }
    val title = getCurrentRoute(navController)

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = title ?: "wrong title") },
                colors = topAppBarColors(containerColor = Color.Black, titleContentColor = Color.White)
            )
        },
        bottomBar = {
            AppBottomBar(navController, selectedItem) { newItem -> setSelectedItem(newItem) }
        },
    ) { innerPadding ->
        NavHost(navController, startDestination = "Chats") {
            composable("Chats") {
                EmptyPage()
            }
            composable("Contacts") {
                EmptyPage()
            }
            composable("Discover") {
                DiscoverPage(homeNavController, modifier = Modifier.padding(innerPadding))
            }
            composable("Me") {
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