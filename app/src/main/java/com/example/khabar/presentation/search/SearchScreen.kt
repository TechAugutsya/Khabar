package com.example.khabar.presentation.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.khabar.domain.model.Article
import com.example.khabar.presentation.Dimension.mediumPadding1
import com.example.khabar.presentation.common.ArticlsList
import com.example.khabar.presentation.common.SearchBar
import com.example.khabar.presentation.navgraph.Route



@Composable
fun SearchScreen(
    state: SearchState,
    event: (SearchEvent) -> Unit,
    navigateToDetails: (Article) -> Unit
){

    Column(
        modifier = Modifier
            .padding(
                top = mediumPadding1, start = mediumPadding1, end = mediumPadding1
            )
            .statusBarsPadding().fillMaxSize()
    ) {
        SearchBar(
            text = state.searchQuery,
            readOnly = false,
            onValueChange = {event(SearchEvent.UpdateSearchQuery(it))},
            onSearch = {event(SearchEvent.SearchNews)}
        )

            Spacer(modifier = Modifier.height(mediumPadding1))
        state.articles?.let {
            val articles =it.collectAsLazyPagingItems()
            ArticlsList(articles = articles, onClick = {navigateToDetails(it)})
        }


    }

}