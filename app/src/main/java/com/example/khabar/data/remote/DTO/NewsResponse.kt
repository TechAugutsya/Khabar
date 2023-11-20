package com.example.khabar.data.remote.DTO

import com.example.khabar.domain.model.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)