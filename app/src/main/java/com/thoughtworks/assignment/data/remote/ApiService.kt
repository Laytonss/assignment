package com.thoughtworks.assignment.data.remote

import com.thoughtworks.assignment.domain.Tweet
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

interface ApiService {
    @GET("moments-data/tweets.json")
    fun getTweets(): Flow<List<Tweet>>
}