package com.thoughtworks.assignment.domain

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("profile-image")
    val profileImage: String,
    val avatar: String,
    val nick: String,
    val username: String,
)
