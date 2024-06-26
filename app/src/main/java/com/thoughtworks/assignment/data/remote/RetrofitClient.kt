package com.thoughtworks.assignment.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tech.thdev.network.flowcalladapterfactory.FlowCallAdapterFactory

const val TWEET_URL = "https://xianmobilelab.gitlab.io/"

object RetrofitClient {

    val apiService: ApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(TWEET_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(FlowCallAdapterFactory())
            .build()

        retrofit.create(ApiService::class.java)
    }
}