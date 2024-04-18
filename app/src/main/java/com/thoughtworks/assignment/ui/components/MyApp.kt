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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.thoughtworks.assignment.Constants
import com.thoughtworks.assignment.ui.utils.Utils.getCurrentRoute
import com.thoughtworks.assignment.ui.viewmodel.MomentsViewModel

@Composable
fun MyApp(
    momentsViewModel: MomentsViewModel = viewModel()
) {
    val homeNavController = rememberNavController()
    NavHost(homeNavController, startDestination = Constants.HOME_CONTENT) {
        composable(Constants.HOME_CONTENT) {
            HomeContent(homeNavController)
        }
        composable(Constants.MOMENTS) {
            MomentsPage(momentsViewModel)
        }
    }
}


@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun HomeContent(homeNavController: NavHostController) {
    val navController = rememberNavController()
    val title = getCurrentRoute(navController)

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = title ?: "") },
                colors = topAppBarColors(containerColor = Color.Black, titleContentColor = Color.White)
            )
        },
        bottomBar = {
            AppBottomBar(navController)
        },
    ) { innerPadding ->
        NavHost(navController, startDestination = Constants.BOTTOM_CHATS) {
            composable(Constants.BOTTOM_CHATS) {
                EmptyPage()
            }
            composable(Constants.BOTTOM_CONTACTS) {
                EmptyPage()
            }
            composable(Constants.BOTTOM_DISCOVER) {
                DiscoverPage(homeNavController, modifier = Modifier.padding(innerPadding))
            }
            composable(Constants.BOTTOM_ME) {
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