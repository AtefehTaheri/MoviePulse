package ir.atefehtaheri.homescreen.components


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavOptions
import ir.atefehtaheri.common.models.Type
import ir.atefehtaheri.homescreen.HomeScreenViewModel
import ir.atefehtaheri.homescreen.Uistate.TopRatedMoviePagerState
import ir.atefehtaheri.homescreen.Uistate.movieItem
import ir.atefehtaheri.toprated.repository.models.TopRatedMovieDataModel


@Composable
internal fun TopRatedMovieList(
    onItemClick:(Type, String, NavOptions?) -> Unit,
    modifier: Modifier = Modifier,
    homeScreenViewModel: HomeScreenViewModel = hiltViewModel()
) {
    val state by homeScreenViewModel.topRatedMovie.collectAsState()
    TopRatedMovieListScreen(state,onItemClick)
}

@Composable
private fun TopRatedMovieListScreen(state: TopRatedMoviePagerState,onItemClick:(Type, String,NavOptions?) -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 5.dp, vertical = 0.dp)
    ) {
        Text(
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 10.dp),
            text = "Movies",
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onPrimary,
        )
        when {
            state.loading -> LoadingState()
            else -> ShowListState(state.topRatedMovieListDataModel.topratedmovielist,onItemClick)
        }
    }
}

@Composable
private fun LoadingState(
    modifier: Modifier = Modifier) {
    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        (1..3).forEach {
            PagerItem(null,true)
        }
    }
}


@Composable
private fun ShowListState(
    topratedmovielist: List<TopRatedMovieDataModel>?,
    onItemClick:(Type, String,NavOptions?) -> Unit,
    modifier: Modifier = Modifier
) {
    topratedmovielist?.let { list ->
        LazyRow(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            items(list) {
                val item = movieItem(it.title,it.poster_path,it.id,it.vote_average, Type.MOVIE)
                PagerItem(item,false,onItemClick)
            }
        }
    }
}
