package com.example.khabar.presentation.onboarding

import androidx.annotation.DrawableRes
import com.example.khabar.R

data class Page(
    val title:String,
    val descroption:String,
    @DrawableRes val image:Int

)
val page = listOf(
    Page(
        title = "Travel",
        descroption = "Find latest news and articles based on Indian and World travel including travel Tips, top 10 travel destination, tourism information.",
        image = R.drawable.onboarding2
    ),
    Page(
        title = "Finance",
        descroption = "Find the latest stock market news from every corner of the globe at Khabar, your online source for breaking international market and finance news.",
        image = R.drawable.onboarding1
    ),

    Page(
        title = "Food",
        descroption = "Get all the latest in food news, food recipes, cooking tips, healthy meal ideas, and nutrition stories from India and around the world at Khabar",
        image = R.drawable.onboarding3
    )
)
