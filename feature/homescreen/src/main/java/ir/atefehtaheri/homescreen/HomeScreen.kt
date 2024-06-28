package ir.atefehtaheri.homescreen


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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavOptions
import androidx.navigation.navOptions
import ir.atefehtaheri.common.models.Type
import ir.atefehtaheri.designsystem.ShowError
import ir.atefehtaheri.homescreen.components.NowPlayingList
import ir.atefehtaheri.homescreen.components.TopRatedMovieList
import ir.atefehtaheri.homescreen.components.TopRatedTvShowList
import ir.atefehtaheri.homescreen.components.TvAiringList
import ir.atefehtaheri.homescreen.components.UpcomingPager

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


//            val x=navOptions {
//                // Pop up to the start destination of the graph to
//                // avoid building up a large stack of destinations
//                // on the back stack as users select items
//                popUpTo(navController.graph.findStartDestination().id) {
//                    saveState = true
//                }
//                // Avoid multiple copies of the same destination when
//                // reselecting the same item
//                launchSingleTop = true
//                // Restore state when reselecting a previously selected item
//                restoreState = true
//            }


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
