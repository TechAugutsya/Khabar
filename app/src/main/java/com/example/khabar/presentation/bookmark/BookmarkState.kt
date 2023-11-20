package com.example.khabar.presentation.bookmark

import com.example.khabar.domain.model.Article

data class BookmarkState(
    val articles: List<Article> = emptyList()
)
