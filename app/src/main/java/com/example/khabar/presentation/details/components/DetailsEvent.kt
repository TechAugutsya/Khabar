package com.example.khabar.presentation.details

import com.example.khabar.domain.model.Article

sealed class DetailsEvent {
    data class  UpsertDeleteArticle(val article: Article) : DetailsEvent()

    object RemoveSideEffect : DetailsEvent()
}