package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.automirrored.rounded.FormatListBulleted
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme
import android.util.Log

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 开启沉浸式边缘布局，让内容可以延伸到系统状态栏和导航栏区域。
        enableEdgeToEdge()
        setContent {
            // 统一套用项目默认主题，颜色、字体、暗色模式都从这里进入。
            MyApplicationTheme {
                MainApp()
            }
        }
    }
}

// 底部菜单的三个入口。
// 每一项都包含页面标题和图标，后面如果要新增页面，可以在这里继续添加枚举项。
private enum class AppTab(
    val title: String,
    val icon: ImageVector
) {
    Home("首页", Icons.Rounded.Home),
    List("列表", Icons.AutoMirrored.Rounded.FormatListBulleted),
    Mine("我的", Icons.Rounded.Person)
}

@Composable
fun MainApp() {
    // 保存当前选中的底部菜单项。
    // rememberSaveable 可以在屏幕旋转、配置变化时尽量保留当前页面状态。
    var selectedTab by rememberSaveable { mutableStateOf(AppTab.Home) }

    // Scaffold 是 Material3 推荐的页面骨架，适合放顶部栏、内容区、底部栏等结构。
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            // 底部 App 菜单栏，包含：首页、列表、我的。
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.surface,
                tonalElevation = 8.dp
            ) {
                // 根据 AppTab 枚举自动生成底部菜单按钮，减少重复代码。
                AppTab.entries.forEach { tab ->
                    NavigationBarItem(
                        selected = selectedTab == tab,
                        onClick = {
                            selectedTab = tab
                        },
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
        // innerPadding 是 Scaffold 留给底部菜单的安全间距。
        // 内容区使用它可以避免页面内容被底部菜单遮住。
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            color = Color(0xFFF6F7FB)
        ) {
            // 根据当前选中的菜单显示不同页面。
            Log.d("TabDebug", "current $selectedTab")
            when (selectedTab) {
                AppTab.Home -> HomePage()
                AppTab.List -> ListPage()
                AppTab.Mine -> MinePage()
            }
        }
    }
}

@Composable
private fun HomePage() {
    // 首页使用 LazyColumn，内容多时可以自动滚动。
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            // 顶部欢迎卡片，作为首页的视觉重点。
            HeroCard()
        }
        item {
            // 区块标题：用于提示下面卡片内容的主题。
            SectionTitle(title = "今日概览", subtitle = "保持简单，也保持漂亮")
        }
        // 首页统计卡片数据来自 homeStats。
        items(homeStats) { stat ->
            InfoCard(
                title = stat.title,
                subtitle = stat.subtitle,
                accentColor = stat.color
            )
        }
    }
}

@Composable
private fun HeroCard() {
    // 渐变背景卡片，让首页第一屏更有层次感。
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(176.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(
                Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF006C67),
                        Color(0xFF1C8A74),
                        Color(0xFFE8B44F)
                    )
                )
            )
            .padding(20.dp)
    ) {
        Column(
            modifier = Modifier.align(Alignment.CenterStart),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                // 首页主标题。
                text = "欢迎回来",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
            Text(
                // 首页说明文字。
                text = "这里是你的轻量工作台，快速查看重点内容。",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White.copy(alpha = 0.88f)
            )
        }
    }
}

@Composable
private fun ListPage() {
    // 列表页展示一组任务卡片，适合后面替换成真实接口数据。
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(20.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item {
            SectionTitle(title = "列表", subtitle = "清晰展示最近事项")
        }
        // 列表页数据来自 taskItems，每一项渲染成一个 TaskCard。
        items(taskItems) { item ->
            TaskCard(item)
        }
    }
}

@Composable
private fun MinePage() {
    // 我的页面包含账户头部和个人相关入口。
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            // 用户信息区域。
            ProfileHeader()
        }
        // 个人中心功能入口数据来自 profileActions。
        items(profileActions) { action ->
            InfoCard(
                title = action.title,
                subtitle = action.subtitle,
                accentColor = action.color
            )
        }
    }
}

@Composable
private fun SectionTitle(title: String, subtitle: String) {
    // 通用区块标题组件：主标题 + 辅助说明。
    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineSmall,
            color = Color(0xFF18201F),
            fontWeight = FontWeight.Bold
        )
        Text(
            text = subtitle,
            style = MaterialTheme.typography.bodyMedium,
            color = Color(0xFF68706F)
        )
    }
}

@Composable
private fun InfoCard(
    title: String,
    subtitle: String,
    accentColor: Color
) {
    // 通用信息卡片：首页和我的页面都会复用这个组件。
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                // 左侧圆形图标背景，使用 accentColor 的浅色透明版本。
                modifier = Modifier
                    .size(44.dp)
                    .clip(CircleShape)
                    .background(accentColor.copy(alpha = 0.16f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    // 星标图标只是装饰性图标，所以 contentDescription 设置为 null。
                    imageVector = Icons.Rounded.Star,
                    contentDescription = null,
                    tint = accentColor
                )
            }
            Column(
                modifier = Modifier.padding(start = 14.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    color = Color(0xFF18201F),
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color(0xFF68706F)
                )
            }
        }
    }
}

@Composable
private fun TaskCard(item: TaskItem) {
    // 列表页专用任务卡片：左侧色点、中间标题说明、右侧状态文字。
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                // 左侧状态色点，颜色与右侧状态文字一致。
                modifier = Modifier
                    .size(10.dp)
                    .clip(CircleShape)
                    .background(item.color)
            )
            Column(
                // weight(1f) 让中间文字占用剩余空间，避免挤压右侧状态。
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 14.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = item.title,
                    style = MaterialTheme.typography.titleMedium,
                    color = Color(0xFF18201F),
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = item.subtitle,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color(0xFF68706F)
                )
            }
            Text(
                text = item.status,
                style = MaterialTheme.typography.labelLarge,
                color = item.color,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
private fun ProfileHeader() {
    // 我的页面头部卡片：展示头像图标和账户说明。
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                // 简单的圆形头像占位，后续可替换成真实头像图片。
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF006C67)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Rounded.Person,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(34.dp)
                )
            }
            Column(
                modifier = Modifier.padding(start = 16.dp),
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Text(
                    text = "我的账户",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color(0xFF18201F),
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "简洁、清爽、随时可用",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color(0xFF68706F)
                )
            }
        }
    }
}

// 首页和个人中心卡片使用的数据结构。
private data class HomeStat(
    val title: String,
    val subtitle: String,
    val color: Color
)

// 列表页任务卡片使用的数据结构。
private data class TaskItem(
    val title: String,
    val subtitle: String,
    val status: String,
    val color: Color
)

// 首页卡片的示例数据。
private val homeStats = listOf(
    HomeStat("待办事项", "还有 6 项内容等待处理", Color(0xFF006C67)),
    HomeStat("本周进度", "核心任务已完成 72%", Color(0xFF2F6FDB)),
    HomeStat("灵感收藏", "新增 12 条值得回看的记录", Color(0xFFC46A1B))
)

// 列表页任务的示例数据。
private val taskItems = listOf(
    TaskItem("完善首页样式", "优化间距、颜色和内容层级", "进行中", Color(0xFF006C67)),
    TaskItem("整理列表数据", "让信息呈现更清晰", "待处理", Color(0xFF2F6FDB)),
    TaskItem("检查个人中心", "补充账户入口与偏好设置", "待处理", Color(0xFFC46A1B)),
    TaskItem("发布新版本", "确认页面体验后打包", "计划中", Color(0xFF7C4DFF))
)

// 我的页面功能入口的示例数据。
private val profileActions = listOf(
    HomeStat("个人资料", "查看和维护基础信息", Color(0xFF006C67)),
    HomeStat("消息通知", "管理提醒与更新", Color(0xFF2F6FDB)),
    HomeStat("应用设置", "调整显示和使用偏好", Color(0xFFC46A1B))
)

@Preview(showBackground = true)
@Composable
fun MainAppPreview() {
    // Android Studio 预览入口，不需要运行 App 也能看到页面效果。
    MyApplicationTheme {
        MainApp()
    }
}
