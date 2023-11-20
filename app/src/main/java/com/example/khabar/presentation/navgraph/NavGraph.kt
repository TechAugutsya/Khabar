package com.example.khabar.presentation.navgraph

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.khabar.presentation.bookmark.BookmarkScreen
import com.example.khabar.presentation.bookmark.bookmarkViewModel
import com.example.khabar.presentation.home.HomeScreen
import com.example.khabar.presentation.home.HomeViewModel
import com.example.khabar.presentation.news_navigator.components.NewsNavigator
import com.example.khabar.presentation.search.SearchScreen
import com.example.khabar.presentation.onboarding.OnBoardingScreen
import com.example.khabar.presentation.onboarding.OnBoardingViewModel
import com.example.khabar.presentation.search.SearchScreen
import com.example.khabar.presentation.search.SearchViewModel

@Composable
fun NavGraph(
    startDestination:String
){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination){
        navigation(
            route = Route.AppStartNavigation.route,
            startDestination =Route.onBoardingScreen.route
        ){
            composable(
                route =Route.onBoardingScreen.route
            ){
            val viewModel:OnBoardingViewModel = hiltViewModel()
            OnBoardingScreen(
                event = viewModel::onEvent
            )        }}
        navigation(
            route = Route.NewsNavigation.route,
            startDestination =Route.NewsNavigatorScreen.route
        ){
            composable(
                route =Route.NewsNavigatorScreen.route
            ){
               NewsNavigator()


            }}
    }
}