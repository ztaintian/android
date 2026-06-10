package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.Article
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Whatshot
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.ui.screens.FollowPage
import com.example.myapplication.ui.screens.HomeNewsPage
import com.example.myapplication.ui.screens.HotListPage
import com.example.myapplication.ui.screens.ProfilePage
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                NewsApp()
            }
        }
    }
}

private enum class NewsTab(
    val title: String,
    val icon: ImageVector
) {
    Home("首页", Icons.Rounded.Home),
    Hot("热榜", Icons.Rounded.Whatshot),
    Follow("关注", Icons.AutoMirrored.Rounded.Article),
    Profile("我的", Icons.Rounded.Person)
}

@Composable
fun NewsApp() {
    var selectedTab by rememberSaveable { mutableStateOf(NewsTab.Home) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.surface,
                tonalElevation = androidx.compose.ui.unit.Dp.Hairline
            ) {
                NewsTab.entries.forEach { tab ->
                    NavigationBarItem(
                        selected = selectedTab == tab,
                        onClick = { selectedTab = tab },
                        icon = {
                            Icon(
                                imageVector = tab.icon,
                                contentDescription = tab.title
                            )
                        },
                        label = { Text(tab.title) }
                    )
                }
            }
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            color = MaterialTheme.colorScheme.background
        ) {
            when (selectedTab) {
                NewsTab.Home -> HomeNewsPage()
                NewsTab.Hot -> HotListPage()
                NewsTab.Follow -> FollowPage()
                NewsTab.Profile -> ProfilePage()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun NewsAppPreview() {
    MyApplicationTheme {
        NewsApp()
    }
}
