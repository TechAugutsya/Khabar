package com.example.khabar.presentation.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import com.example.khabar.R
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import com.example.khabar.domain.model.Article
import com.example.khabar.presentation.Dimension.mediumPadding1
import com.example.khabar.presentation.common.ArticlsList
import com.example.khabar.presentation.common.SearchBar
import com.example.khabar.presentation.navgraph.Route

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(article: LazyPagingItems<Article>,
               navigateToSearch:()->Unit,
               navigateToDetails: (Article) -> Unit){
    val titles by remember {
        derivedStateOf {
            if (article.itemCount > 10) {
                article.itemSnapshotList.items.slice(IntRange(start = 0, endInclusive = 9))
                    .joinToString(separator = " \uD83d\uDFE5 ") { it.title }
            } else{
                    ""
                }


            }
        }
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(top = mediumPadding1)
        .statusBarsPadding()
    ) {
        Image(painter = painterResource(id = R.drawable.ic_logo), contentDescription =null, modifier = Modifier
            .width(150.dp)
            .height(30.dp)
            .padding(horizontal = mediumPadding1) )

        Spacer(modifier = Modifier.height(mediumPadding1))

        SearchBar(
            modifier = Modifier.padding(horizontal = mediumPadding1),
            text = "",
            readOnly = true,
            onValueChange = {},
            onClick = {
                      navigateToSearch

        }, onSearch = {})

        Spacer(modifier = Modifier.height(mediumPadding1))
        
        Text(text = titles,modifier = Modifier
            .fillMaxWidth()
            .padding(start = mediumPadding1)
            .basicMarquee(),
            fontSize = 12.sp,
            color = colorResource(id = R.color.placeholder))

        Spacer(modifier = Modifier.height(mediumPadding1))

        ArticlsList(
            modifier = Modifier.padding(horizontal = mediumPadding1),
            articles = article,
            onClick = { navigateToDetails(it) })



    }
    }
    

