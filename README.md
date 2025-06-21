
# 📰 Khabbar - Modern News App (Jetpack Compose)

Khabbar is a modern News application built using **Jetpack Compose** following **Clean Architecture** principles. It fetches the latest headlines using REST APIs, supports article saving, offline reading, and seamless navigation.

---

## ✨ Features

- 🚀 **Jetpack Compose UI** — Fully declarative UI using Material 3.
- 🔁 **Clean Architecture** — Separation of concerns with clear domain, data, and presentation layers.
- 🧠 **MVVM** Pattern — Powered by ViewModels and state handling.
- 📡 **Retrofit** — For consuming news APIs.
- 📂 **Room Database** — Offline saving of articles and local persistence.
- 🔦 **Shimmer Effect** — Loading animations while articles load.
- 🎉 **Splash Screen** — Elegant startup experience.
- 📊 **Jetpack DataStore** — Persistent storage for user preferences.
- 🧭 **Navigation Component (Navigation-Compose)** — Smooth screen transitions.
- 🗑️ **Swipe to Delete** — Remove saved articles with intuitive gestures.
- 🔗 **Share & Open in Browser** — Share or view full articles directly in Google Chrome or preferred browser.

---

## 📷 Screenshots


<p align="center">
  <br>
  <img src="./3.jpg" width="200" height="400">
  <img src="./search.jpg" width="200" height="400">
  <img src="./share.jpg" width="200" height="400">
</p>


---

## 🛠️ Tech Stack

| Layer | Tools |
|------|-------|
| UI | Jetpack Compose, Material 3 |
| Architecture | Clean Architecture, MVVM |
| API | Retrofit, Gson |
| Local DB | Room |
| State | StateFlow, ViewModel |
| Navigation | Jetpack Navigation-Compose |
| Storage | DataStore Preferences |
| Effects | Compose Shimmer |
| Others | Coil (for image loading), Chrome Custom Tabs, SwipeToDismiss |

---

## 📁 Project Structure

```

Khabbar/
│
├── data/
│   ├── local/           # Room DB setup
│   ├── remote/          # Retrofit API
│   ├── repository/      # Repository pattern
│
├── domain/
│   ├── model/           # Data models
│   ├── use\_case/        # Business logic
│
├── presentation/
│   ├── screens/         # Compose screens
│   ├── components/      # Reusable UI
│   ├── navigation/      # NavGraph
│   └── viewmodel/       # State holders
│
└── utils/               # Mappers, extensions, constants

````

---

## 🔧 Setup Instructions

1. Clone the repository:
```bash
git clone https://github.com/YourUsername/Khabbar.git
````

2. Open in **Android Studio** Arctic Fox or higher.

3. Replace `YOUR_API_KEY` in the `NewsApiService` file with your News API key:

```kotlin
@GET("v2/top-headlines")
suspend fun getTopHeadlines(@Query("apiKey") apiKey: String = "YOUR_API_KEY"): NewsResponse
```

4. Build & run the project.

---

## 📌 Boilerplate Code Snippet (Room Setup)

**Entity**

```kotlin
@Entity(tableName = "articles")
data class ArticleEntity(
    @PrimaryKey val url: String,
    val title: String,
    val description: String?,
    val urlToImage: String?
)
```

**DAO**

```kotlin
@Dao
interface ArticleDao {
    @Query("SELECT * FROM articles")
    fun getAllArticles(): Flow<List<ArticleEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticle(article: ArticleEntity)

    @Delete
    suspend fun deleteArticle(article: ArticleEntity)
}
```

**Database**

```kotlin
@Database(entities = [ArticleEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao
}
```

---

## 🚀 Planned Improvements

* 🔍 Search functionality
* 🌐 Multi-country or language support
* 🌓 Dark Mode toggle with DataStore
* 🔔 Push Notifications for breaking news

---

## 🤝 Contributing

Feel free to fork the repo, raise PRs, or open issues.

---

## 📜 License

This project is licensed under the MIT License. See the `LICENSE` file for details.

---

## 🙋‍♂️ Author

* **Aadarsh Chaurasia**
* [GitHub](https://github.com/Aadarsh45)
* [LinkedIn](https://linkedin.com/in/aadarsh-chaurasia-876588231)

---


