package ir.atefehtaheri.homescreen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ir.atefehtaheri.homescreen.HomeScreenViewModel

@Composable
fun TopRatedMovieList(
    modifier: Modifier = Modifier,
    homeScreenViewModel: HomeScreenViewModel = hiltViewModel()
) {


    val state by homeScreenViewModel.topRatedMovie.collectAsState()
    state.topRatedMovieListDataModel.topratedmovielist?.let { list->

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 5.dp, vertical = 0.dp)
        ) {
            Text(
                modifier = Modifier
                    .padding( horizontal = 20.dp , vertical = 10.dp),
                text = "Movies",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onPrimary,
            )

            LazyRow (horizontalArrangement =Arrangement.spacedBy(10.dp)){
                items(list){
                    PagerItem(it.title,it.poster_path)
                }
            }
        }
    }
}