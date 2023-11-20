package com.example.khabar.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.khabar.data.local.NewsDao
import com.example.khabar.data.remote.NewsAPI
import com.example.khabar.data.remote.NewsPagingSource
import com.example.khabar.data.remote.SearchNewsPagingSources
import com.example.khabar.domain.model.Article
import com.example.khabar.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

class NewsRepositoryImpl(
    private val newsAPI: NewsAPI,
    private val newsDao: NewsDao
):NewsRepository {
    override fun getNews(sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                NewsPagingSource(
                    newsAPI =newsAPI,
                    sources =sources.joinToString(separator = ",")
                )
            }
        ).flow
    }

    override fun searchNews(searchQuery: String, sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                SearchNewsPagingSources(
                    searchQuery= searchQuery,
                    newsAPI =newsAPI,
                    sources =sources.joinToString(separator = ",")
                )
            }
        ).flow
    }

    override suspend fun upsertArticle(article: Article) {
        newsDao.upsert(article)    }

    override suspend fun deleteArticle(article: Article) {
       newsDao.delete(article)
    }

    override fun selectArticles(): Flow<List<Article>> {
        return newsDao.getArticles()
    }

    override suspend fun selectArticle(url: String): Article? {
     return   newsDao.getArticle(url)
    }

}