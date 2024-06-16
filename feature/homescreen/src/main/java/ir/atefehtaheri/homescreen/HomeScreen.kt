package ir.atefehtaheri.homescreen

import android.util.Log
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavOptions
import ir.atefehtaheri.homescreen.components.NowPlayingList
import ir.atefehtaheri.homescreen.components.TopRatedMovieList
import ir.atefehtaheri.homescreen.components.TopRatedTvShowList
import ir.atefehtaheri.homescreen.components.TvAiringList
import ir.atefehtaheri.homescreen.components.UpcomingPager
import kotlin.math.roundToInt

@Composable
fun HomeScreen (
    navToUpcoming :  (NavOptions?) -> Unit,
    navToNowPlaying :  (NavOptions?) -> Unit,
    navToTopRated :  (NavOptions?) -> Unit,
) {
val scrollstate = rememberScrollState()

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
        UpcomingPager()
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
        NowPlayingList()
        TvAiringList()

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
        TopRatedMovieList()
        TopRatedTvShowList()
        Spacer(modifier = Modifier.height(10.dp))
}}