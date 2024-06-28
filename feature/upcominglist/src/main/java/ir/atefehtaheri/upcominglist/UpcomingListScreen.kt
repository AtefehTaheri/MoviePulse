package ir.atefehtaheri.upcominglist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavOptions
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import ir.atefehtaheri.common.models.Type
import ir.atefehtaheri.designsystem.ShowError
import ir.atefehtaheri.upcominglist.repository.models.UpcomingMovieDataModel


@Composable
internal fun UpcomingListRoute(
    onItemClick: (Type, String, NavOptions?) -> Unit,
    modifier: Modifier = Modifier,
    upcomingMovieViewModel: UpcomingMovieViewModel = hiltViewModel()
) {
    val movies = upcomingMovieViewModel.upcomingMovies.collectAsLazyPagingItems()
    UpcomingListScreen(movies, onItemClick)
}

@Composable
private fun UpcomingListScreen(
    movies: LazyPagingItems<UpcomingMovieDataModel>,
    onItemClick: (Type, String, NavOptions?) -> Unit,
) {

    when {
        movies.loadState.refresh is LoadState.Error -> ShowError(
            (movies.loadState.refresh as LoadState.Error).error.message ?: ""
        )

        movies.loadState.refresh is LoadState.Loading -> LoadingState()
        else -> ShowListScreen(movies, onItemClick)
    }
}

@Composable
private fun LoadingState() {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primaryContainer)
    ) {

        CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center),
            color = MaterialTheme.colorScheme.secondaryContainer,
        )
    }
}

@Composable
private fun ShowListScreen(
    movies: LazyPagingItems<UpcomingMovieDataModel>,
    onItemClick: (Type, String, NavOptions?) -> Unit,

    ) {
    val listState = rememberLazyListState()

            Box(
                modifier = Modifier
                    .fillMaxSize()
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
                                item, onItemClick
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