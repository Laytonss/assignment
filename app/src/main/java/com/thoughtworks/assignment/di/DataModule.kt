package com.thoughtworks.assignment.di

import com.thoughtworks.assignment.data.repository.TweetRepository
import com.thoughtworks.assignment.data.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun provideTweetDataSource(): TweetRepository = TweetRepository()

    @Provides
    fun provideUserDataSource(): UserRepository = UserRepository()
}