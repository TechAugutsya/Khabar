package com.example.khabar.domain.repository

import androidx.paging.PagingData
import com.example.khabar.domain.model.Article
import java.net.URL
import java.util.concurrent.Flow

interface NewsRepository {

    fun getNews(sources: List<String>): kotlinx.coroutines.flow.Flow<PagingData<Article>>
    fun searchNews(searchQuery:String,sources: List<String>): kotlinx.coroutines.flow.Flow<PagingData<Article>>

    suspend fun upsertArticle(article: Article)

    suspend fun deleteArticle(article: Article)

    fun selectArticles():kotlinx.coroutines.flow.Flow<List<Article>>

    suspend fun selectArticle(url: String): Article?

}