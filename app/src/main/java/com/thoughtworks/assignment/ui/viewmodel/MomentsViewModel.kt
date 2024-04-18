package com.thoughtworks.assignment.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.thoughtworks.assignment.data.repository.TweetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class MomentsViewModel @Inject constructor(
    private val repository: TweetRepository
) : ViewModel() {

    val allTweets = repository.fetchTweets()

    val filterTweets = allTweets.map { tweets ->
        tweets.filter {
            !it.isError() && it.haveContentOrImage()
        }
    }

    val user = repository.fetchUser()
}