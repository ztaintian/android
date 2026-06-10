package com.example.myapplication.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapplication.data.mockArticles
import com.example.myapplication.data.mockCategories
import com.example.myapplication.ui.components.CategoryTabs
import com.example.myapplication.ui.components.LeadNewsCard
import com.example.myapplication.ui.components.NewsItemCard
import com.example.myapplication.ui.components.NewsTopBar
import com.example.myapplication.ui.components.SectionHeader

@Composable
fun HomeNewsPage() {
    var selectedCategory by rememberSaveable { mutableStateOf(mockCategories.first().id) }
    val selectedName = mockCategories.first { it.id == selectedCategory }.name
    val articles = if (selectedName == "推荐") {
        mockArticles
    } else {
        mockArticles.filter { it.category == selectedName }.ifEmpty { mockArticles }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        NewsTopBar()
        CategoryTabs(
            categories = mockCategories,
            selectedId = selectedCategory,
            onSelected = { selectedCategory = it }
        )
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(20.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            item {
                LeadNewsCard(article = mockArticles.first())
            }
            item {
                SectionHeader(title = "正在发生", action = "换一换")
            }
            items(articles, key = { it.id }) { article ->
                NewsItemCard(article = article)
            }
        }
    }
}
