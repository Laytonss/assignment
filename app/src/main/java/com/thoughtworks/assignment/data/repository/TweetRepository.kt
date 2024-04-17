package com.thoughtworks.assignment.data.repository

import com.thoughtworks.assignment.data.remote.RetrofitClient
import com.thoughtworks.assignment.domain.Tweet
import com.thoughtworks.assignment.domain.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TweetRepository {

    fun fetchTweets(): Flow<List<Tweet>> {
        return RetrofitClient.apiService.getTweets()
    }

    fun fetchUser(): Flow<User> {
        return RetrofitClient.apiService.getUser()
    }
}