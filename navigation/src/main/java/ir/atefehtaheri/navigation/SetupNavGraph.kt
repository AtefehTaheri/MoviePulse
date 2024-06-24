package ir.atefehtaheri.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import ir.atefehtaheri.detailscreen.navigation.detailscreenDestination
import ir.atefehtaheri.detailscreen.navigation.navigateToDetailScreen
import ir.atefehtaheri.homescreen.navigation.HomeScreenRoute
import ir.atefehtaheri.homescreen.navigation.homeScreenDestination
import ir.atefehtaheri.nowplaying.navigation.navigateToNowplaying
import ir.atefehtaheri.nowplaying.navigation.nowPlayingMovieDestination
import ir.atefehtaheri.topratedmovie.navigation.TopRatedListDestination
import ir.atefehtaheri.topratedmovie.navigation.navigateToTopRated
import ir.atefehtaheri.upcominglist.navigation.navigateToUpcomingList
import ir.atefehtaheri.upcominglist.navigation.upcomingListDestination


@Composable
fun SetupNavGraph(navController: NavHostController, startDestination: String = HomeScreenRoute) {


    NavHost(navController = navController, startDestination = startDestination) {


        homeScreenDestination(
        navToTopRated =navController::navigateToTopRated,
            navToUpcoming= navController::navigateToUpcomingList,
            onItemClick= navController::navigateToDetailScreen,
            navToNowPlaying = navController::navigateToNowplaying,
        )

        upcomingListDestination()
        nowPlayingMovieDestination()
        detailscreenDestination()
        TopRatedListDestination()
    }


}

