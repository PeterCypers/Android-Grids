package com.example.gridwithimages

import DataSource
import androidx.compose.material3.Icon
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ConfirmationNumber
import androidx.compose.material.icons.filled.DeviceHub
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gridwithimages.model.Topic
import com.example.gridwithimages.ui.theme.GridWithImagesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GridWithImagesTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.fillMaxSize()){
                        Box(modifier = Modifier
                            .fillMaxWidth()
                            .height(25.dp)
                            .background(color = Color(red = 133, green = 53, blue = 124))
                        )
                        TopicGrid(DataSource.topics, modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 0.dp)
                            .padding(innerPadding)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun GridItem(topic: Topic, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .border(
                border = BorderStroke(1.dp, Color.Gray.copy(0.3F)),
                shape = RoundedCornerShape(12.dp)
            ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row {
            Image(
                painter = painterResource(topic.imageResourceId),
                contentDescription = stringResource(topic.stringResourceId),
                modifier = Modifier
                    .size(68.dp),
                    //.clip(RoundedCornerShape(topStart = 12.dp, bottomStart = 12.dp)), // for contentscale crop
                contentScale = ContentScale.Fit //maybe crop
            )
            Column(
                modifier.height(68.dp),
                verticalArrangement = Arrangement.Bottom
            ) {
                Text(
                    text = stringResource(topic.stringResourceId),
                    modifier = Modifier.padding(start = 16.dp,
                        top = 16.dp,
                        end = 16.dp,
                        bottom = 8.dp),
                    style = MaterialTheme.typography.bodyMedium
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Filled.ConfirmationNumber,  //DeviceHub | Tag,
                        contentDescription = "amount",
                        modifier = Modifier.padding(start = 16.dp, end = 8.dp)
                            .size(19.dp)
                    )
                    Text(
                        text = topic.amount.toString(),
                        style = MaterialTheme.typography.labelMedium
                    )
                }
            }
        }
    }
}

@Composable
fun TopicGrid(topics: List<Topic>, modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(2), // Two columns
        contentPadding = PaddingValues(8.dp), // Padding around the grid
        verticalArrangement = Arrangement.spacedBy(8.dp), // Vertical spacing between items
        horizontalArrangement = Arrangement.spacedBy(8.dp) // Horizontal spacing between items
    ) {
        items(topics.size) { index ->
            GridItem(topic = topics[index])
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GridItemPreview() {
    GridWithImagesTheme {
        GridItem(Topic(R.string.culinary, 50, R.drawable.culinary))
    }
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    GridWithImagesTheme {
        TopicGrid(DataSource.topics)
    }
}