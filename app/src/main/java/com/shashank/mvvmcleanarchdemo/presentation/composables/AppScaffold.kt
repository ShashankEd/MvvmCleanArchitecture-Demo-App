package com.shashank.mvvmcleanarchdemo.presentation.composables

import android.content.Context
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.shashank.mvvmcleanarchdemo.presentation.navigation.Screen
import com.shashank.mvvmcleanarchdemo.presentation.viewmodel.ArticleViewModel

@Composable
fun AppScaffold(
    context: Context,
    articleViewModel: ArticleViewModel
) {
    val navController = rememberNavController()
    Scaffold {
        AppNavHost(
            navController = navController,
            modifier = Modifier
                .fillMaxSize()
                .padding(it) ,
            context = context ,
            articleViewModel = articleViewModel
        )
    }
}

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    context: Context,
    articleViewModel: ArticleViewModel
) {
    NavHost(navController = navController, startDestination = Screen.ARTICLE_SCREEN.routes) {
        composable(Screen.ARTICLE_SCREEN.routes) {
            ArticleComposable( modifier = modifier, articleViewModel = articleViewModel)
        }
    }
}