package com.thoughtworks.assignment.data.remote

import com.thoughtworks.assignment.domain.Tweet
import com.thoughtworks.assignment.domain.User
import retrofit2.http.GET

interface ApiService {
    @GET("moments-data/tweets.json")
    suspend fun getTweets(): List<Tweet>

    @GET("/moments-data/user.json")
    suspend fun getUser(): User
}