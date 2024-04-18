package com.thoughtworks.assignment

import com.thoughtworks.MainDispatcherRule
import com.thoughtworks.assignment.data.repository.TweetRepository
import com.thoughtworks.assignment.data.repository.UserRepository
import com.thoughtworks.assignment.domain.Image
import com.thoughtworks.assignment.domain.Sender
import com.thoughtworks.assignment.domain.Tweet
import com.thoughtworks.assignment.domain.User
import com.thoughtworks.assignment.ui.viewmodel.MomentsViewModel
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MomentsViewModelTest {

    @Mock
    lateinit var tweetRepository: TweetRepository

    @Mock
    lateinit var userRepository: UserRepository

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun `should return tweet data when fetch`() {
        MainScope().launch {
            val mockTweetLiveDataList = arrayListOf(
                Tweet("content1", arrayListOf(Image("url1")), Sender("username1")),
                Tweet("content2", arrayListOf(Image("url2")), Sender("username2")),
            )
            Mockito.`when`(tweetRepository.fetchTweets()).thenReturn(mockTweetLiveDataList)
            val momentsViewModel = MomentsViewModel(tweetRepository, userRepository)
            momentsViewModel.filterTweets.collect{
                assertEquals(mockTweetLiveDataList, it)
            }
        }
    }

    @Test
    fun `should filter tweet data when tweet have error`() {
        MainScope().launch {
            val mockTweetLiveDataList = arrayListOf(
                Tweet("content1", arrayListOf(Image("url1")), Sender("username1"),null,"error"),
                Tweet("content2", arrayListOf(Image("url2")), Sender("username2"),null,null,"unknownError"),
            )
            Mockito.`when`(tweetRepository.fetchTweets()).thenReturn(mockTweetLiveDataList)
            val momentsViewModel = MomentsViewModel(tweetRepository, userRepository)
            momentsViewModel.filterTweets.collect{
                assertEquals(0, it.size)
            }
        }
    }

    @Test
    fun `should filter tweet data when tweet have no content and image`() {
        MainScope().launch {
            val mockTweetLiveDataList = arrayListOf(
                Tweet("content1", null, Sender("username1"),null),
                Tweet(null, arrayListOf(Image("url2")), Sender("username2"),null,null),
                Tweet(null, null, Sender("username2"),null,null),
            )
            Mockito.`when`(tweetRepository.fetchTweets()).thenReturn(mockTweetLiveDataList)
            val momentsViewModel = MomentsViewModel(tweetRepository, userRepository)
            momentsViewModel.filterTweets.collect{
                assertEquals(2, it.size)
                assertEquals(Tweet("content1", null, Sender("username1"),null),it[0])
                assertEquals(Tweet(null, arrayListOf(Image("url2")), Sender("username2"),null,null),it[1])
            }
        }
    }

    @Test
    fun `should return user data`() {
        MainScope().launch {
            val mockUser = User("profileImage","avatar","nick","username")
            Mockito.`when`(userRepository.fetchUser()).thenReturn(mockUser)
            val momentsViewModel = MomentsViewModel(tweetRepository, userRepository)
            momentsViewModel.user.collect{
                assertEquals(mockUser, it)
            }
        }
    }

    @Test
    fun `should return tweet count when fetch tweet count`() {
        MainScope().launch {
            val mockTweetLiveDataList = arrayListOf(
                Tweet("content1", null, Sender("username1"),null),
                Tweet(null, arrayListOf(Image("url2")), Sender("username2"),null,null),
                Tweet(null, null, Sender("username2"),null,null),
            )
            Mockito.`when`(tweetRepository.fetchTweets()).thenReturn(mockTweetLiveDataList)
            val momentsViewModel = MomentsViewModel(tweetRepository, userRepository)
            assertEquals(2, momentsViewModel.getTweetsCount())
        }
    }
}

