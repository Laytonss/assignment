package com.thoughtworks.assignment.data.repository

import com.thoughtworks.assignment.data.remote.RetrofitClient
import com.thoughtworks.assignment.domain.Tweet
import kotlinx.coroutines.flow.Flow

class TweetRepository {
    fun fetchTweets(): Flow<List<Tweet>> {
        return RetrofitClient.apiService.getTweets()
    }
}