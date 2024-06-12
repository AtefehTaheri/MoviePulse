package ir.atefehtaheri.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import ir.atefehtaheri.homescreen.navigation.HomeScreenRoute
import ir.atefehtaheri.homescreen.navigation.homeScreenDestination
import ir.atefehtaheri.upcominglist.navigation.UpcomingListRoute
import ir.atefehtaheri.upcominglist.navigation.navigateToUpcomingList
import ir.atefehtaheri.upcominglist.navigation.upcomingListDestination


@Composable
fun SetupNavGraph(navController: NavHostController, startDestination: String = HomeScreenRoute) {


    NavHost(navController = navController, startDestination = startDestination) {

        upcomingListDestination()

        homeScreenDestination(navController::navigateToUpcomingList)
    }


}