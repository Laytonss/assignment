package com.thoughtworks.assignment.ui.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.thoughtworks.assignment.data.repository.TweetRepository
import com.thoughtworks.assignment.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.runBlocking

@Composable
fun MomentsPage(
    mainViewModel: MainViewModel
) {
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(color = Color.Black)
//    ) {
//
//    }
//    runBlocking {
//        TweetRepository().fetchTweets().collect {
//            Log.d("tweet", "##${it.size}")
//        }
//    }
}