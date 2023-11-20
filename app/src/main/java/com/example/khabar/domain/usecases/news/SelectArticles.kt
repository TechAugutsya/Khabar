package com.example.khabar.domain.usecases.news

import com.example.khabar.data.local.NewsDao
import com.example.khabar.domain.model.Article
import com.example.khabar.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class SelectArticles(
    private val newsRepository: NewsRepository
) {

    operator fun invoke(): Flow<List<Article>>{
      return  newsRepository.selectArticles()
    }
}