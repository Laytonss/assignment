package com.thoughtworks.assignment.domain

import com.google.gson.annotations.SerializedName

data class Tweet(
    val content: String? = null,
    val images: List<Image>? = null,
    val sender: Sender? = null,
    val comments: List<Comment>? = null,
    val error: String? = null,
    @SerializedName("unknown error")
    val unknownError: String? = null
) {
    fun isError(): Boolean {
        return error != null || unknownError != null
    }

    fun haveContent(): Boolean {
        return content != null
    }
}