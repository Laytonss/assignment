package com.thoughtworks.assignment.data.remote

import com.thoughtworks.assignment.domain.Tweet
import com.thoughtworks.assignment.domain.User
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

interface ApiService {
    @GET("moments-data/tweets.json")
    fun getTweets(): Flow<List<Tweet>>

    @GET("/moments-data/user.json")
    fun getUser(): Flow<User>
}