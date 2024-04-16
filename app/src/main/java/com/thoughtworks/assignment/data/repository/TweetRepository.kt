package com.thoughtworks.assignment.data.repository

import com.thoughtworks.assignment.data.remote.RetrofitClient
import com.thoughtworks.assignment.domain.Tweet
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TweetRepository {
    fun fetchTweets(): Flow<List<Tweet>> {
        return flow {
            val response = RetrofitClient.apiService.getTweets()
            emit(response)
        }
    }
}