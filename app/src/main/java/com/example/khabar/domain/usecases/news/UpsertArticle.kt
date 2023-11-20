package com.example.khabar.domain.usecases.news

import com.example.khabar.data.local.NewsDao
import com.example.khabar.domain.model.Article
import com.example.khabar.domain.repository.NewsRepository

class UpsertArticle(
    private val newsRepository: NewsRepository
) {

    suspend operator fun invoke(article: Article){
        newsRepository.upsertArticle(article)
    }
}