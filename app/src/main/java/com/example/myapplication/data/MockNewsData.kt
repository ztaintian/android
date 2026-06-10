package com.example.myapplication.data

import androidx.compose.ui.graphics.Color

data class NewsCategory(
    val id: String,
    val name: String
)

data class NewsArticle(
    val id: String,
    val title: String,
    val summary: String,
    val source: String,
    val time: String,
    val commentCount: String,
    val category: String,
    val imageColor: Color,
    val isBreaking: Boolean = false
)

data class HotTopic(
    val rank: Int,
    val title: String,
    val heat: String,
    val tag: String
)

data class FollowAuthor(
    val name: String,
    val description: String,
    val latestTitle: String,
    val color: Color
)

data class ProfileAction(
    val title: String,
    val subtitle: String,
    val color: Color
)

val mockCategories = listOf(
    NewsCategory("recommend", "推荐"),
    NewsCategory("local", "本地"),
    NewsCategory("tech", "科技"),
    NewsCategory("finance", "财经"),
    NewsCategory("sports", "体育"),
    NewsCategory("culture", "文化")
)

val mockArticles = listOf(
    NewsArticle(
        id = "n1",
        title = "多地推出夜间消费新场景，商圈客流持续回暖",
        summary = "城市更新、文旅活动和特色市集联动，带动餐饮、交通与零售同步增长。",
        source = "城市观察",
        time = "12分钟前",
        commentCount = "128评",
        category = "推荐",
        imageColor = Color(0xFFE94F37),
        isBreaking = true
    ),
    NewsArticle(
        id = "n2",
        title = "新一代手机影像芯片发布，低光拍摄能力提升",
        summary = "厂商将重点放在端侧算法、续航控制和视频稳定性上。",
        source = "科技前线",
        time = "28分钟前",
        commentCount = "89评",
        category = "科技",
        imageColor = Color(0xFF2F80ED)
    ),
    NewsArticle(
        id = "n3",
        title = "早盘指数震荡上行，消费与半导体板块活跃",
        summary = "机构认为短期资金更关注业绩确定性和政策落地节奏。",
        source = "财讯快报",
        time = "45分钟前",
        commentCount = "203评",
        category = "财经",
        imageColor = Color(0xFF00A36C)
    ),
    NewsArticle(
        id = "n4",
        title = "高校毕业季开启，多个城市加码青年就业服务",
        summary = "线上招聘、实习补贴和人才公寓成为今年服务重点。",
        source = "教育周刊",
        time = "1小时前",
        commentCount = "56评",
        category = "本地",
        imageColor = Color(0xFFFFB703)
    ),
    NewsArticle(
        id = "n5",
        title = "国内联赛今晚迎来焦点战，双方主力阵容齐整",
        summary = "教练组表示会在高压逼抢和转换速度上做更多准备。",
        source = "赛场速递",
        time = "2小时前",
        commentCount = "311评",
        category = "体育",
        imageColor = Color(0xFF7C3AED)
    )
)

val mockHotTopics = listOf(
    HotTopic(1, "高考志愿填报服务陆续上线", "486万", "热"),
    HotTopic(2, "新能源汽车充电网络继续扩容", "372万", "新"),
    HotTopic(3, "端午假期周边游订单增长", "326万", "荐"),
    HotTopic(4, "AI 办公工具进入企业采购季", "291万", "热"),
    HotTopic(5, "多地优化公积金使用政策", "248万", "快"),
    HotTopic(6, "电影暑期档片单公布", "205万", "娱")
)

val mockFollowAuthors = listOf(
    FollowAuthor(
        name = "城市实验室",
        description = "关注城市治理与商业空间",
        latestTitle = "社区商业为什么重新变热？",
        color = Color(0xFFE94F37)
    ),
    FollowAuthor(
        name = "硬件笔记",
        description = "手机、电脑和智能设备深度观察",
        latestTitle = "轻薄本散热设计的三个变化",
        color = Color(0xFF2F80ED)
    ),
    FollowAuthor(
        name = "财经十分钟",
        description = "用短内容解释市场变化",
        latestTitle = "今天资金为什么流向消费板块？",
        color = Color(0xFF00A36C)
    )
)

val mockProfileActions = listOf(
    ProfileAction("消息通知", "评论、点赞和系统提醒", Color(0xFFE94F37)),
    ProfileAction("收藏历史", "稍后阅读的新闻都在这里", Color(0xFF2F80ED)),
    ProfileAction("阅读偏好", "调整频道、字号和推送频率", Color(0xFF00A36C))
)
