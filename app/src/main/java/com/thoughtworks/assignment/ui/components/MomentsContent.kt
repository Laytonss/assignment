package com.thoughtworks.assignment.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.thoughtworks.assignment.ui.viewmodel.MainViewModel
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thoughtworks.assignment.domain.Image
import com.thoughtworks.assignment.domain.Sender
import com.thoughtworks.assignment.domain.Tweet
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun MomentsPage(
    mainViewModel: MainViewModel
) {
    val tweets by mainViewModel.filterTweets.collectAsState(initial = emptyList())
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF19191E))
    ) {
        LazyColumn {
            items(items = tweets) { tweet ->
                TweetItem(tweet)
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun TweetItem(@PreviewParameter(BackgroundColorProvider::class) tweet: Tweet) {
    Row(
        modifier = Modifier.padding(10.dp)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(tweet.sender?.avatar)
                .crossfade(true)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(50.dp)
                .clip(RoundedCornerShape(8.dp))
        )
        Column(
            modifier = Modifier.padding(start = 10.dp),
        ) {
            Text(
                text = tweet.sender?.nick ?: throw RuntimeException(),
                fontWeight = FontWeight.Bold,
                color = Color(0xff808ca3)
            )
            TweetContent(tweet.content ?: throw RuntimeException())
        }
    }
    Divider(color = Color.Gray, thickness = 0.5.dp)
}

@Composable
fun TweetContent(text: String) {
    if (text.length <= 150) {
        Text(
            text = text,
            modifier = Modifier.padding(top = 5.dp),
            color = Color.White
        )
    } else {
        TextWithExpand(text)
    }
}

@Composable
fun TextWithExpand(text: String) {
    var isExpand by remember { mutableStateOf(false) }
    Text(
        text = text,
        modifier = Modifier.padding(top = 5.dp),
        color = Color.White,
        overflow = TextOverflow.Ellipsis,
        maxLines = if (isExpand) Int.MAX_VALUE else 3,
    )
    if (isExpand) {
        Text(
            text = "Show Less",
            modifier = Modifier
                .clickable { isExpand = false }
                .padding(top = 5.dp),
            fontSize = 15.sp,
            color = Color(0xff808ca3),
        )
    } else {
        Text(
            text = "Show More",
            modifier = Modifier
                .clickable { isExpand = true }
                .padding(top = 5.dp),
            fontSize = 15.sp,
            color = Color(0xff808ca3)
        )
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