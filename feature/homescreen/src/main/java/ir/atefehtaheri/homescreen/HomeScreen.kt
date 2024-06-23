package ir.atefehtaheri.homescreen

import android.util.Log
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavOptions
import ir.atefehtaheri.common.models.Type
import ir.atefehtaheri.designsystem.ShowError
import ir.atefehtaheri.homescreen.components.NowPlayingList
import ir.atefehtaheri.homescreen.components.TopRatedMovieList
import ir.atefehtaheri.homescreen.components.TopRatedTvShowList
import ir.atefehtaheri.homescreen.components.TvAiringList
import ir.atefehtaheri.homescreen.components.UpcomingPager
import kotlin.math.roundToInt

@Composable
fun HomeScreen (
    navToUpcoming :  (NavOptions?) -> Unit,
    navToNowPlaying :  (NavOptions?) -> Unit={},
    navToTopRated :  (NavOptions?) -> Unit={},
    onItemClick:(Type, String, NavOptions?) -> Unit,
    homeScreenViewModel: HomeScreenViewModel = hiltViewModel()

) {
val scrollstate = rememberScrollState()

    val errorState by homeScreenViewModel.errorState.collectAsState()

    if (errorState != ""){
//        Column(modifier = Modifier
//            .fillMaxSize()
//            .background(MaterialTheme.colorScheme.primaryContainer),
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Center)
//        {
//            Image(
//                painter = painterResource(id = R.drawable.error),
//                contentDescription = "",
//                Modifier
//                    .size(100.dp),
//                     contentScale = ContentScale.Fit
//            )
//        Spacer(modifier = Modifier.height(10.dp))
//            Text(
//                text = errorState,
//                style = MaterialTheme.typography.titleMedium,
//                color = MaterialTheme.colorScheme.onPrimary,
//                textAlign = TextAlign.Center
//            )
//
//        }
        ShowError(errorState)
    }else{

    Column (
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollstate)
            .background(MaterialTheme.colorScheme.primaryContainer)

    ){

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, start = 20.dp, end = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        )
        {
            Text(
                text = "Upcoming Movies",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onPrimary,
            )

            Text(
                modifier = Modifier.clickable{ navToUpcoming(null)},
                text = "See All",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.secondaryContainer)
        }
        UpcomingPager(onItemClick)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        )
        {
            Text(
                text = "Now Playing",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onPrimary,
            )

            Text(
                modifier = Modifier.clickable { navToNowPlaying(null) },
                text = "See All",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.secondaryContainer,
            )
        }
        NowPlayingList(onItemClick)
        TvAiringList(onItemClick)

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        )
        {
            Text(
                text = "Top Rated",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onPrimary,
            )

            Text(
                modifier = Modifier.clickable { navToTopRated(null) },
                text = "See All",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.secondaryContainer,
            )
        }
        TopRatedMovieList(onItemClick)
        TopRatedTvShowList(onItemClick)
        Spacer(modifier = Modifier.height(10.dp))
}
    }
}
