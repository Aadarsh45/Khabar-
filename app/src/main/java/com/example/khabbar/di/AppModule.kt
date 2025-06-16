package com.example.khabbar.di

import android.app.Application
import android.content.Context
import com.example.khabbar.data.manager.LocalUserManagerImpl
import com.example.khabbar.data.remote.NewsApi
import com.example.khabbar.data.remote.local.ArticleDatabase
import com.example.khabbar.data.remote.local.dao.ArticleDao
import com.example.khabbar.data.repository.NewsRepositoryImpl
import com.example.khabbar.domain.manager.LocalUserManager
import com.example.khabbar.domain.repository.NewsRepository
import com.example.khabbar.domain.usecases.Article.DeleteArticleUseCase
import com.example.khabbar.domain.usecases.appEntry.AppEntryUseCases
import com.example.khabbar.domain.usecases.appEntry.readAppEntry
import com.example.khabbar.domain.usecases.appEntry.saveAppEntry
import com.example.khabbar.domain.usecases.news.GetNews
import com.example.khabbar.domain.usecases.news.NewsUseCases
import com.example.khabbar.domain.usecases.Article.GetSavedArticleUseCases
import com.example.khabbar.domain.usecases.Article.IsArticleSavedUseCase
import com.example.khabbar.domain.usecases.Article.SaveArticleUseCase
import com.example.khabbar.domain.usecases.Article.SearchNewsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManager(
        application: Application
    ): LocalUserManager = LocalUserManagerImpl(application)

    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        LocalUserManager: LocalUserManager
    ): AppEntryUseCases {
        return AppEntryUseCases(
            readAppEntry = readAppEntry(LocalUserManager),
            saveAppEntry = saveAppEntry(LocalUserManager)
        )
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    })
                    .build()
            )
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsApiService(retrofit: Retrofit): NewsApi {
        return retrofit.create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(apiService: NewsApi,articleDao: ArticleDao): NewsRepository {
        return NewsRepositoryImpl(apiService,articleDao)
    }

    @Provides
    @Singleton
    fun provideNewsUseCases(
          newsRepository: NewsRepository
    ): NewsUseCases{
        return NewsUseCases(
            getNews = GetNews(newsRepository)
        )

    }

    @Provides
    @Singleton
    fun provideArticleDatabase(@ApplicationContext context: Context): ArticleDatabase {
        return ArticleDatabase.getDatabase(context)

    }

    @Provides
    @Singleton
    fun provideArticleDao(articleDatabase: ArticleDatabase): ArticleDao {
        return articleDatabase.articleDao()

    }
    @Provides
    @Singleton
    fun provideSaveArticleUseCase(newsRepository: NewsRepository) : SaveArticleUseCase {
        return SaveArticleUseCase(newsRepository)

    }

    @Provides
    @Singleton
    fun provideGetSavedUseCases(newsRepository: NewsRepository) : GetSavedArticleUseCases {
        return GetSavedArticleUseCases(newsRepository)

    }

    @Provides
    @Singleton
    fun provideIsArticleSavedUseCase(repository: NewsRepository): IsArticleSavedUseCase {
        return IsArticleSavedUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun provideSearchNewsUseCase(repository: NewsRepository): SearchNewsUseCase =
        SearchNewsUseCase(repository)

    @Provides
    fun provideDeleteArticleUseCase(newsRepository: NewsRepository): DeleteArticleUseCase {
        return DeleteArticleUseCase(newsRepository)
    }
}