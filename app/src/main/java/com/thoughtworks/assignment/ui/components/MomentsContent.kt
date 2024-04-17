package com.thoughtworks.assignment.ui.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.thoughtworks.assignment.data.repository.TweetRepository
import com.thoughtworks.assignment.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.runBlocking
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.thoughtworks.assignment.domain.Image
import com.thoughtworks.assignment.domain.Sender
import com.thoughtworks.assignment.domain.Tweet
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun MomentsPage(
    mainViewModel: MainViewModel
) {
    val tweets by mainViewModel.tweet.collectAsState(initial = emptyList())
    LazyColumn {
        items(items = tweets) { tweet ->
            TweetItem(tweet)
        }
    }
}

@Composable
@Preview(showBackground = true)
fun TweetItem(@PreviewParameter(BackgroundColorProvider::class) tweet: Tweet) {
    Row {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(tweet.sender?.avatar)
                .crossfade(true)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(50.dp)
        )
        Text(text = tweet.sender?.nick ?: "no")
    }
}

class BackgroundColorProvider : PreviewParameterProvider<Tweet> {
    override val values: Sequence<Tweet> = sequenceOf(
        Tweet(
            content = "content1",
            images = arrayListOf(
                Image("https://xianmobilelab.gitlab.io/moments-data/images/tweets/001.jpeg")
            ),
            sender = Sender("name1", "nick1", "https://xianmobilelab.gitlab.io/moments-data/images/user/avatar/001.jpeg"),
            comments = emptyList()
        ),
        Tweet("content2", emptyList(), Sender("name2", "nick1", "avatar1"), emptyList()),
        Tweet("content3", emptyList(), Sender("name3", "nick1", "avatar1"), emptyList()),
    )
}
