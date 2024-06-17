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
import ir.atefehtaheri.homescreen.HomeScreenViewModel
import ir.atefehtaheri.homescreen.Uistate.TvAiringPagerState
import ir.atefehtaheri.tvairing.repository.models.TvAiringMovieDataModel


@Composable
internal fun TvAiringList(
    modifier: Modifier = Modifier,
    homeScreenViewModel: HomeScreenViewModel = hiltViewModel()
) {
    val state by homeScreenViewModel.tvShowAiring.collectAsState()
    TvAiringListScreen(state)
}

@Composable
private fun TvAiringListScreen(state: TvAiringPagerState) {


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 5.dp, vertical = 0.dp)
    ) {
        Text(
            modifier = Modifier
                .padding( horizontal = 20.dp , vertical = 10.dp),
            text = "Tv Shows",
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onPrimary,
        )
    when {
        state.loading -> LoadingState()
        else -> ShowListState(state.tvAiringListDataModel.airinglist)
    }

}
}
@Composable
private fun LoadingState(modifier: Modifier = Modifier) {

        Row (horizontalArrangement =Arrangement.spacedBy(10.dp)){
            (1..3).forEach{
                PagerItem(null,null,true)
            }
        }
}


@Composable
private fun ShowListState(
    airinglist: List<TvAiringMovieDataModel>?,
    modifier: Modifier = Modifier
) {

   airinglist?.let { list->

        LazyRow (horizontalArrangement =Arrangement.spacedBy(10.dp)){
            items(list){
                PagerItem(it.name,it.poster_path,false)
            }
        }
    }
}


