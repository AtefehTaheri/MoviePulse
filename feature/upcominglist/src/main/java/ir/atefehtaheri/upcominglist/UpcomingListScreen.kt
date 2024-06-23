package ir.atefehtaheri.upcominglist

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import ir.atefehtaheri.designsystem.ShowError
import ir.atefehtaheri.upcominglist.repository.models.UpcomingMovieDataModel


@Composable
internal fun UpcomingListRoute(
    modifier: Modifier = Modifier,
    upcomingMovieViewModel: UpcomingMovieViewModel = hiltViewModel()
) {
    val movies = upcomingMovieViewModel.getUpcomingMovies().collectAsLazyPagingItems()
    UpcomingListScreen(movies)
}

@Composable
private fun UpcomingListScreen(
    movies: LazyPagingItems<UpcomingMovieDataModel>
) {

    when {
        movies.loadState.refresh is LoadState.Error -> ShowError(
            (movies.loadState.refresh as LoadState.Error).error.message ?: ""
        )

        movies.loadState.refresh is LoadState.Loading -> LoadingState()
        else -> ShowListScreen(movies)
    }
}

@Composable
private fun LoadingState(){

    Box(modifier = Modifier.fillMaxSize()
        .background(MaterialTheme.colorScheme.primaryContainer)) {

        CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center),
            color = MaterialTheme.colorScheme.secondaryContainer,
        )
    }
}

@Composable
private fun ShowListScreen(
    movies: LazyPagingItems<UpcomingMovieDataModel>
) {
    val listState = rememberLazyListState()

    Box(modifier = Modifier.fillMaxSize()
        .background(MaterialTheme.colorScheme.primaryContainer)
    ) {
        LazyColumn(
            modifier = Modifier.padding(16.dp), state = listState,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            items(
                count = movies.itemCount,
            ) { index ->
                val item = movies[index]

                if (item != null) {
                   UpcomingItem(
                        item
                    )
                }
            }

            item {
                if (movies.loadState.append is LoadState.Loading) {
                    CircularProgressIndicator()
                }
            }
        }

    }
}

@Composable
private fun ErrorState(
    error:String,
    modifier: Modifier = Modifier
    ) {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.primaryContainer),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center)
    {
        Image(
            painter = painterResource(id = R.drawable.error),
            contentDescription = "",
            Modifier
                .size(100.dp),
            contentScale = ContentScale.Fit
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = error,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onPrimary,
            textAlign = TextAlign.Center
        )

    }
}