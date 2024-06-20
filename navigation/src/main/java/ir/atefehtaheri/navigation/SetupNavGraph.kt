package ir.atefehtaheri.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.NavHost
import ir.atefehtaheri.detailscreen.navigation.detailscreenDestination
import ir.atefehtaheri.detailscreen.navigation.navigateToDetailScreen
import ir.atefehtaheri.homescreen.navigation.HomeScreenRoute
import ir.atefehtaheri.homescreen.navigation.homeScreenDestination
import ir.atefehtaheri.upcominglist.navigation.UpcomingListRoute
import ir.atefehtaheri.upcominglist.navigation.navigateToUpcomingList
import ir.atefehtaheri.upcominglist.navigation.upcomingListDestination


@Composable
fun SetupNavGraph(navController: NavHostController, startDestination: String = HomeScreenRoute) {


    NavHost(navController = navController, startDestination = startDestination) {


        homeScreenDestination(
//            navToUpcoming :  (NavOptions?) -> Unit,
//        navToNowPlaying :  (NavOptions?) -> Unit,
//        navToTopRated :  (NavOptions?) -> Unit,
            navToUpcoming= navController::navigateToUpcomingList,
            onItemClick= navController::navigateToDetailScreen
        )

        upcomingListDestination()
        detailscreenDestination()
    }


}

