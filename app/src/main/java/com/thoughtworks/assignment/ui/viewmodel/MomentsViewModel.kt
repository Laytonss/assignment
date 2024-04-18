package com.thoughtworks.assignment.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thoughtworks.assignment.data.repository.TweetRepository
import com.thoughtworks.assignment.domain.Tweet
import com.thoughtworks.assignment.domain.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MomentsViewModel @Inject constructor(
    private val repository: TweetRepository
) : ViewModel() {

    private val _allTweets: MutableStateFlow<List<Tweet>> = MutableStateFlow(emptyList())
    private val allTweets: StateFlow<List<Tweet>> = _allTweets.asStateFlow()

    private val _user: MutableStateFlow<User> = MutableStateFlow(User("", "", "", ""))
    val user: StateFlow<User> = _user.asStateFlow()

    init {
        fetchTweets()
        fetchUser()
    }

    private fun fetchTweets() {
        viewModelScope.launch {
            val tweets = repository.fetchTweets()
            _allTweets.value = tweets
        }
    }

    private fun fetchUser() {
        viewModelScope.launch {
            val user = repository.fetchUser()
            _user.value = user
        }
    }

    val filterTweets = allTweets.map { tweets ->
        tweets.filter {
            !it.isError() && it.haveContentOrImage()
        }
    }
}