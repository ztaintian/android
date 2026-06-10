# Android News Demo

一个使用 Kotlin + Jetpack Compose 编写的新闻资讯示例应用，页面风格参考头条类信息流产品。当前数据全部来自本地 mock，适合继续扩展真实接口、图片加载和详情页。

## 功能概览

- 首页信息流：顶部标题栏、搜索/通知入口、横向频道、头条大卡、新闻列表。
- 热榜页面：全网热榜排行和热议新闻列表。
- 关注页面：关注作者列表和最新内容展示。
- 我的页面：用户信息卡片、收藏历史、阅读偏好等入口。
- 公共组件拆分：新闻卡片、热榜行、频道标签、顶部栏等可复用 UI 已单独抽出。
- Mock 数据拆分：页面示例数据统一放在 `data` 包中，后续替换接口更方便。

## 目录结构

```text
app/src/main/java/com/example/myapplication/
├── MainActivity.kt                 # App 入口，负责底部导航和页面切换
├── data/
│   └── MockNewsData.kt             # 新闻、频道、热榜、关注、个人中心 mock 数据
└── ui/
    ├── components/
    │   └── NewsComponents.kt       # 新闻 App 公共 Compose 组件
    ├── screens/
    │   ├── HomeNewsPage.kt         # 首页信息流
    │   ├── HotListPage.kt          # 热榜页
    │   ├── FollowPage.kt           # 关注页
    │   └── ProfilePage.kt          # 我的页
    └── theme/
        ├── Color.kt                # 新闻 App 主题色
        ├── Theme.kt                # Material3 主题配置
        └── Type.kt                 # 字体排版配置
```

## 技术栈

- Kotlin
- Jetpack Compose
- Material3
- Gradle Kotlin DSL

## 常用命令

编译 Kotlin：

```powershell
.\gradlew.bat :app:compileDebugKotlin
```

构建 Debug APK：

```powershell
.\gradlew.bat :app:assembleDebug
```

Debug APK 输出位置：

```text
app/build/outputs/apk/debug/app-debug.apk
```

## 后续扩展建议

1. 在 `MockNewsData.kt` 中替换为接口返回的数据模型。
2. 为 `NewsArticle` 增加图片 URL 字段，并接入 Coil 等图片加载库。
3. 增加新闻详情页和点击跳转。
4. 为频道选择、阅读记录、收藏状态接入 ViewModel 和持久化。
