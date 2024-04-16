package com.thoughtworks.assignment

import com.thoughtworks.assignment.data.repository.TweetRepository
import com.thoughtworks.assignment.ui.viewmodel.MainViewModel
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        runBlocking {
            TweetRepository().fetchTweets().collect {
                assertEquals(22, it.size)
            }
        }
    }
}