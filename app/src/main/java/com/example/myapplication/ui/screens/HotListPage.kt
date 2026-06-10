package com.example.myapplication.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapplication.data.mockArticles
import com.example.myapplication.data.mockHotTopics
import com.example.myapplication.ui.components.HotTopicRow
import com.example.myapplication.ui.components.NewsItemCard
import com.example.myapplication.ui.components.SectionHeader

@Composable
fun HotListPage() {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(20.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item {
            SectionHeader(title = "全网热榜", action = "实时")
        }
        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
            ) {
                androidx.compose.foundation.layout.Column(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    mockHotTopics.forEach { topic ->
                        HotTopicRow(topic = topic)
                    }
                }
            }
        }
        item {
            SectionHeader(title = "热议新闻")
        }
        items(mockArticles.take(3), key = { it.id }) { article ->
            NewsItemCard(article = article)
        }
    }
}
