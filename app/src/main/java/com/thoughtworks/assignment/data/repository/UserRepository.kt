package com.thoughtworks.assignment.data.repository

import com.thoughtworks.assignment.data.remote.RetrofitClient
import com.thoughtworks.assignment.domain.User

class UserRepository {
    suspend fun fetchUser(): User {
        return RetrofitClient.apiService.getUser()
    }
}