package com.example.khabar.domain.usecases.news

import androidx.paging.PagingData
import com.example.khabar.domain.model.Article
import com.example.khabar.domain.repository.NewsRepository

class GetNews(
    private val newsRepository: NewsRepository
) {
    operator fun invoke(sources: List<String>): kotlinx.coroutines.flow.Flow<PagingData<Article>>{
        return newsRepository.getNews(sources= sources)
    }
}