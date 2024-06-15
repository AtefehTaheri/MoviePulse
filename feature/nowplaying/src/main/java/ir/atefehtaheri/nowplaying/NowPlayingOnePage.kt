package ir.atefehtaheri.nowplaying

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NowPlayingOnePageScreen(
    modifier: Modifier = Modifier,
    nowPlayingMovieViewModel: NowPlayingMovieViewModel = hiltViewModel()
) {


    val state by nowPlayingMovieViewModel.nowplayingPager.collectAsState()
    state.nowPlayingListDataModel.nowplaying?.let { list->

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {

            Text("Movies",modifier=Modifier.align(Alignment.Start))

            LazyRow {
                items(list){

                }

            }



        }


    }
}

