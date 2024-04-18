package com.thoughtworks.assignment.data.repository

import com.thoughtworks.assignment.data.remote.RetrofitClient
import com.thoughtworks.assignment.domain.Tweet

class TweetRepository {
    suspend fun fetchTweets(): List<Tweet> {
        return RetrofitClient.apiService.getTweets()
    }
}