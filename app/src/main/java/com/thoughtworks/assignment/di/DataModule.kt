package com.thoughtworks.assignment.di

import com.thoughtworks.assignment.data.repository.TweetRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun provideDataSource(): TweetRepository = TweetRepository()
}