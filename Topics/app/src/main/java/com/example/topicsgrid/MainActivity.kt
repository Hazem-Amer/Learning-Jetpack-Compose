package com.example.topicsgrid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.topicsgrid.data.DataSource
import com.example.topicsgrid.model.Topic
import com.example.topicsgrid.ui.theme.TopicsGridTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TopicsGridTheme {
                TopicGridApp()
            }
        }
    }
}

@Composable
fun TopicGridApp() {
    val layOutDirection = LocalLayoutDirection.current
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(
                start = WindowInsets.safeDrawing
                    .asPaddingValues()
                    .calculateStartPadding(layOutDirection),
                end = WindowInsets.safeDrawing
                    .asPaddingValues()
                    .calculateEndPadding(layOutDirection)
            ),
        color = MaterialTheme.colorScheme.background
    ) {
        TopicGridList(modifier = Modifier.fillMaxSize(), topics = DataSource.topics)
    }
}

@Composable
fun TopicGridList(modifier: Modifier = Modifier, topics: List<Topic>) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(topics) { topic ->
            TopicGridListItem(topic = topic)
        }
    }
}

@Composable
fun TopicGridListItem(modifier: Modifier = Modifier, topic: Topic) {
    Card(modifier = modifier) {
        Row(modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(topic.imageRes),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxHeight()
                    .width(68.dp)
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(top = 16.dp, end = 16.dp)
            ) {
                Text(
                    text = stringResource(topic.titleRes),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(
                        start = 16.dp,
                        bottom = 8.dp
                    )
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(start = 16.dp)
                ) {
                    Image(
                        painter = painterResource(topic.iconRes),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .height(32.dp)
                            .width(32.dp)
                            .padding(end = 8.dp)
                    )
                    Text(text = topic.code.toString(), style = MaterialTheme.typography.labelMedium)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TopicGridListItemPreview() {
    TopicGridListItem(
        modifier = Modifier.fillMaxSize(),
        topic = Topic(
            R.drawable.ic_launcher_background,
            R.string.architecture,
            58,
            R.drawable.ic_launcher_foreground
        )
    )
}

@Preview(showBackground = true)
@Composable
fun TopicGridListApp() {
    TopicsGridTheme {
        TopicGridApp()
    }

}