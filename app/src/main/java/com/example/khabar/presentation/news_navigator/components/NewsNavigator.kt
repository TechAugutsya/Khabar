package com.example.khabar.presentation.news_navigator.components

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.khabar.R
import com.example.khabar.domain.model.Article
import com.example.khabar.presentation.bookmark.BookmarkScreen
import com.example.khabar.presentation.details.DetailsScreen
import com.example.khabar.presentation.details.DetailsViewModel
import com.example.khabar.presentation.home.HomeScreen
import com.example.khabar.presentation.home.HomeViewModel
import com.example.khabar.presentation.navgraph.Route
import com.example.khabar.presentation.search.SearchScreen
import com.example.khabar.presentation.search.SearchViewModel
import com.example.khabar.presentation.bookmark.bookmarkViewModel
import com.example.khabar.presentation.details.DetailsEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsNavigator(){
    val bottomNavigationItem= remember {
        listOf(
            BottomNavigationItem(icon = R.drawable.ic_home, text = "Home"),
            BottomNavigationItem(icon = R.drawable.ic_search, text = "Search"),
            BottomNavigationItem(icon = R.drawable.ic_bookmark, text = "Bookmark")
            )
    }

    val navController = rememberNavController()
    val backStackState = navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable {
        mutableStateOf(0)
    }
     selectedItem = remember(key1 = backStackState) {
         when(backStackState?.destination?.route){
         Route.HomeScreen.route ->0
         Route.SearchScreen.route ->1
         Route.BookmarkScreen.route ->2
         else ->0
     }}
    val isBottomBarVisible = remember(key1 = backStackState) {
        backStackState?.destination?.route==Route.HomeScreen.route ||
                backStackState?.destination?.route==Route.  SearchScreen.route||
                backStackState?.destination?.route==Route.BookmarkScreen.route

    }

    Scaffold(modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (isBottomBarVisible) {
                NewsBottomNavigation(
                    items = bottomNavigationItem,
                    selected = selectedItem,
                    onItemClick = { index ->
                        when (index) {
                            0 -> navigateToTap(
                                navController = navController,
                                route = Route.HomeScreen.route
                            )
                            1 -> navigateToTap(
                                navController = navController,
                                route = Route.SearchScreen.route
                            )
                            0 -> navigateToTap(
                                navController = navController,
                                route = Route.BookmarkScreen.route
                            )
                        }
                    })
            }}) {
        val bottomPadding = it.calculateBottomPadding()
        NavHost(
            navController = navController,
            startDestination = Route.HomeScreen.route,
            modifier = Modifier.padding(bottom = bottomPadding)
        ) {
            composable(route = Route.HomeScreen.route){
                val viewModel:HomeViewModel = hiltViewModel()
                val articles = viewModel.news.collectAsLazyPagingItems()
                HomeScreen(article = articles, navigateToSearch = {
                    navigateToTap(navController=navController, route = Route.SearchScreen.route )
                },
                    navigateToDetails = {article ->
                        navigateToDetails(navController=navController,article=article)

                    })
            }
            composable(route = Route.SearchScreen.route){
                val viewModel:SearchViewModel = hiltViewModel()
                val state = viewModel.state.value
                SearchScreen(state = state, event = viewModel::onEvent, navigateToDetails ={
                    navigateToDetails(navController= navController, article = it )

                } )

            }

            composable(route = Route.DetailScreen.route){
                val viewModel:DetailsViewModel = hiltViewModel()
                if (viewModel.sideEffect != null){
                    Toast.makeText(LocalContext.current,viewModel.sideEffect,Toast.LENGTH_SHORT).show()
                    viewModel.onEvent(DetailsEvent.RemoveSideEffect)
                }
                navController.previousBackStackEntry?.savedStateHandle?.get<Article>("article")?.let {article ->
                    DetailsScreen(article = article, event = viewModel::onEvent, navigateUp = {navController.navigateUp()}
                        )

                }
            }
            composable(route = Route.BookmarkScreen.route){
                val viewModel: bookmarkViewModel = hiltViewModel()
                val state = viewModel.state.value
                BookmarkScreen(state = state, navigateToDetails ={article ->
                navigateToDetails(navController=navController,article=article)

                } )
                
            }

        }

    }

}

private fun navigateToTap(navController: NavController,route:String){
    navController.navigate(route ){
        navController.graph.startDestinationRoute?.let { homeScreen->
            popUpTo(homeScreen){
                saveState = true
            }
            restoreState = true
            launchSingleTop= true
        }
    }
}

private fun navigateToDetails(navController: NavController,article: Article){
    navController.currentBackStackEntry?.savedStateHandle?.set("article",article)
    navController.navigate(
        route = Route.DetailScreen.route
    )

}