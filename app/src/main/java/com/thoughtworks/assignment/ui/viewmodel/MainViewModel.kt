package com.thoughtworks.assignment.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thoughtworks.assignment.data.repository.TweetRepository
import com.thoughtworks.assignment.domain.Tweet
import dagger.hilt.android.ViewModelLifecycle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.Dispatcher
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: TweetRepository
) : ViewModel() {
    val tweet = repository.fetchTweets()
}