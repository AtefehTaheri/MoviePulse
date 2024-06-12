package ir.atefehtaheri.upcominglist

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
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
fun UpcomingListScreen(
    movies: LazyPagingItems<UpcomingMovieDataModel>
) {

    val listState = rememberLazyListState()

    val context = LocalContext.current
    LaunchedEffect(key1 = movies.loadState) {
        if (movies.loadState.refresh is LoadState.Error) {
            Toast.makeText(
                context,
                "Error: " + (movies.loadState.refresh as LoadState.Error).error.message,
                Toast.LENGTH_LONG
            ).show()
        }
    }
    Box(modifier = Modifier.fillMaxSize()) {
        if (movies.loadState.refresh is LoadState.Loading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
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

}