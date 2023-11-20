package com.example.khabar.domain.usecases.news

import com.example.khabar.data.local.NewsDao
import com.example.khabar.domain.model.Article
import com.example.khabar.domain.repository.NewsRepository

class SelectArticle (
    private val newsRepository: NewsRepository
) {

    suspend operator fun invoke(url: String): Article?{
      return  newsRepository.selectArticle(url)
    }
}