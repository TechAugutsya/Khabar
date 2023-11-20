package com.example.khabar.domain.usecases.news

data class NewsUseCases(
    val getNews: GetNews,
    val searchNews: SearchNews,
    val upsertArticle: UpsertArticle,
    val selectArticles: SelectArticles,
    val deleteArticle: DeleteArticle,
    val selectArticle: SelectArticle


)
