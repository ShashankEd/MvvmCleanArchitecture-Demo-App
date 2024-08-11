package com.shashank.mvvmcleanhiltdemo.feature1.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.shashank.mvvmcleanarchdemo.presentation.composables.ArticleComposable
import com.shashank.mvvmcleanarchdemo.presentation.navigation.Screen
import com.shashank.mvvmcleanarchdemo.presentation.viewmodel.ArticleViewModel

@Composable
fun Navigation(articleViewModel: ArticleViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.ARTICLE_SCREEN.routes) {
        composable(Screen.ARTICLE_SCREEN.routes) {
            val peopleState = articleViewModel.articlesState.collectAsState().value
            ArticleComposable(modifier = Modifier, articleViewModel = articleViewModel )
        }
    }
}