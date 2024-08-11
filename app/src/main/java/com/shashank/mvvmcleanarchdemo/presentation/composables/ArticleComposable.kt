package com.shashank.mvvmcleanarchdemo.presentation.composables

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState
import com.shashank.mvvmcleanarchdemo.domain.model.Article
import com.shashank.mvvmcleanarchdemo.presentation.state.AllArticlesViewModelState
import com.shashank.mvvmcleanarchdemo.presentation.viewmodel.ArticleViewModel

@Composable
fun ArticleComposable(modifier: Modifier, articleViewModel: ArticleViewModel) {
    val articlesViewModelState = articleViewModel.articlesState.collectAsState().value

    var swipeState by remember {
        mutableStateOf(false)
    }

    LaunchedEffect("CallAPI") {
        articleViewModel.getAllArticles("bitcoin")
    }

    if(articlesViewModelState.isLoading) {
        Loader()
    } else if (articlesViewModelState.errorMessage.isNullOrEmpty()) {
        ErrorMessage(articlesViewModelState.errorMessage.toString())
    }
    ArticleListView(
        swipeState,
        articleViewModel,
        modifier = Modifier,
        articlesViewModelState
    )
    
}
@Composable
fun Loader(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.width(64.dp),
            color = MaterialTheme.colorScheme.surfaceVariant,
            trackColor = MaterialTheme.colorScheme.secondary,
        )
    }
}

@Composable
fun ErrorMessage(errorMessage: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = errorMessage,
            style = TextStyle(fontSize = 28.sp, textAlign = TextAlign.Center)
        )
    }
}

@Composable
fun ArticleListView(
    swipeState: Boolean,
    articleViewModel: ArticleViewModel,
    modifier: Modifier,
    articlesViewModelState: AllArticlesViewModelState
) {
    SwipeRefresh(
        state = SwipeRefreshState(swipeState), onRefresh = {
            articleViewModel.getAllArticles("bitcoin")
        }) {
        LazyColumn(modifier = modifier.fillMaxSize().padding(top = 40.dp)) {
            articlesViewModelState.articles?.let {
                items(it.articles) {
                    Log.d("shashank", "ArticleComposable: ${it.publishedAt}")
                    ArticleItem(modifier = modifier, article = it)
                }
            }
        }
    }
}

@Composable
fun ArticleItem(
    modifier: Modifier,
    article: Article
) {
    Column (
        modifier
            .fillMaxWidth()
            .padding(16.dp)
    ){
        AsyncImage(model = article.urlToImage, contentDescription = "book image")
        Spacer(modifier = modifier.height(4.dp))
        Text(text = article.title?: "")
        Spacer(modifier = modifier.height(4.dp))
        Text(
            text = article.publishedAt?: "",
            style = TextStyle(color = Color.Gray),
            modifier = modifier.align(Alignment.End)
        )
        Spacer(modifier = modifier.height(4.dp))
    }
}
