package ir.atefehtaheri.homescreen.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import ir.atefehtaheri.common.models.Type
import ir.atefehtaheri.homescreen.HomeScreen

const val HomeScreenRoute = "homescreen_route"


fun NavController.navigateToHomeScreen(navOptions: NavOptions? = null) {
    this.navigate(HomeScreenRoute, navOptions)
}

fun NavGraphBuilder.homeScreenDestination(
    navToUpcoming :  (NavOptions?) -> Unit,
    navToNowPlaying :  (NavOptions?) -> Unit,
    navToTopRated :  (NavOptions?) -> Unit,
    onItemClick:(Type, String, NavOptions?) -> Unit
   ) {

    composable(route = HomeScreenRoute) {
        HomeScreen (
            navToUpcoming,
            navToNowPlaying ,
            navToTopRated,
            onItemClick
        )
    }
}
