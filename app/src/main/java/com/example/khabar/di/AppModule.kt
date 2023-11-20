package com.example.khabar.di

import android.app.Application
import androidx.room.Room
import com.example.khabar.data.local.NewsDao
import com.example.khabar.data.local.NewsDataBase
import com.example.khabar.data.local.NewsTypeConvertor
import com.example.khabar.data.manager.LocalUserManagerImple
import com.example.khabar.data.remote.NewsAPI
import com.example.khabar.data.repository.NewsRepositoryImpl
import com.example.khabar.domain.manager.LocalUserManager
import com.example.khabar.domain.repository.NewsRepository
import com.example.khabar.domain.usecases.app_entry.AppEntryusecases
import com.example.khabar.domain.usecases.app_entry.ReadAppEntry
import com.example.khabar.domain.usecases.app_entry.SaveAppEntry
import com.example.khabar.domain.usecases.news.DeleteArticle
import com.example.khabar.domain.usecases.news.GetNews
import com.example.khabar.domain.usecases.news.NewsUseCases
import com.example.khabar.domain.usecases.news.SearchNews
import com.example.khabar.domain.usecases.news.SelectArticle
import com.example.khabar.domain.usecases.news.SelectArticles
import com.example.khabar.domain.usecases.news.UpsertArticle
import com.example.khabar.util.Constants.Base_Url
import com.example.khabar.util.Constants.NEWS_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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
    ):LocalUserManager = LocalUserManagerImple(application)


    @Provides
    @Singleton
    fun provideAppEntryusecases(localUserManager: LocalUserManager) = AppEntryusecases(
        readAppEntry = ReadAppEntry(localUserManager),
        saveAppEntry = SaveAppEntry((localUserManager))
    )

    @Provides
    @Singleton
    fun provideNewsApi():NewsAPI{
        return Retrofit.Builder()
            .baseUrl(Base_Url)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(NewsAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(newsAPI:NewsAPI,
                              newsDao: NewsDao): NewsRepository =NewsRepositoryImpl(newsAPI,newsDao)

    @Provides
    @Singleton
    fun provideNewsUsecases(newsRepository: NewsRepository,
                            newsDao: NewsDao) : NewsUseCases{
        return  NewsUseCases(
            getNews = GetNews(newsRepository),
            searchNews = SearchNews(newsRepository),
            upsertArticle = UpsertArticle(newsRepository),
            deleteArticle = DeleteArticle(newsRepository),
            selectArticles = SelectArticles(newsRepository),
            selectArticle = SelectArticle(newsRepository)
        )

    }

    @Provides
    @Singleton
    fun provideNewsDatabase(
        application: Application
    ):NewsDataBase{
        return Room.databaseBuilder(
            context = application,
            klass = NewsDataBase::class.java,
            name = NEWS_DATABASE_NAME
        ).addTypeConverter(NewsTypeConvertor()).fallbackToDestructiveMigration().build()

    }

    @Provides
    @Singleton
    fun provideNewsDao(
        newsDataBase: NewsDataBase
    ):NewsDao=newsDataBase.newsDao





}