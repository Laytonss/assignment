package com.thoughtworks.assignment.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.thoughtworks.assignment.ui.viewmodel.MomentsViewModel
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
import com.thoughtworks.assignment.domain.Comment
import com.thoughtworks.assignment.domain.User

@Composable
fun MomentsPage(
    momentsViewModel: MomentsViewModel
) {
    var tweetCount by remember { mutableIntStateOf(5) }
    val tweets by momentsViewModel.getTweets(tweetCount).collectAsState(initial = emptyList())
    val tweetTotalCount by momentsViewModel.getTweetsCount().collectAsState(initial = 0)
    val user by momentsViewModel.user.collectAsState(User("", "", "", ""))
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF19191E))
    ) {
        LazyColumn {
            item {
                UserBackground(user)
            }
            items(items = tweets) { tweet ->
                TweetItem(tweet)
            }
            if(tweetCount < tweetTotalCount) {
                item {
                    Text(
                        text = "show more tweets",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp)
                            .clickable { tweetCount += 5 },
                        textAlign = TextAlign.Center,
                        color = Color.White,
                    )
                }
            }
        }
    }
}

@Composable
fun UserBackground(user: User) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(350.dp)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(user.profileImage)
                .crossfade(true)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .align(Alignment.TopCenter)
                .graphicsLayer {
                    scaleX = 1.0f
                    scaleY = 1.0f
                },
        )

        Text(
            text = user.nick,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier
                .padding(top = 5.dp)
                .align(Alignment.BottomEnd)
                .padding(bottom = 60.dp, end = 100.dp),
        )
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(user.avatar)
                .crossfade(true)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(100.dp)
                .align(Alignment.BottomEnd)
                .padding(bottom = 25.dp),
        )
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
            tweet.content?.let {
                TweetContent(tweet.content)
            }
            tweet.images?.let {
                ImageGrid(tweet.images)
            }
            TweetBottom()
            tweet.comments?.let {
                Comments(it)
            }
        }
    }
    Divider(color = Color.Gray, thickness = 0.5.dp)
}

@Composable
fun Comments(comments: List<Comment>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xff202020))
    ) {
        comments.forEach { comment ->
            Row {
                Text(
                    text = "${comment.sender.nick ?: "anonymous"}: ",
                    fontWeight = FontWeight.Bold,
                    color = Color(0xff808ca3)
                )
                Text(
                    text = comment.content,
                    color = Color.White
                )
            }
        }
    }
}

@Composable
private fun TweetBottom() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 5.dp),
    ) {
        Text(
            text = "8 minutes ago",
            color = Color.Gray,
            modifier = Modifier.weight(1f)
        )
        Icon(
            imageVector = Icons.Filled.MoreHoriz,
            contentDescription = null,
            tint = Color(0xff808ca3),
        )
    }
}

@Composable
fun ImageGrid(images: List<Image>) {
    val height = when (images.size) {
        in 1..3 -> 100
        in 4..6 -> 200
        in 7..9 -> 300
        else -> throw RuntimeException()
    }
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier
            .padding(top = 5.dp)
            .height(height.dp)
    ) {
        items(images) { image ->
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(image.url)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(100.dp)
                    .padding(2.dp)
            )
        }
    }
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