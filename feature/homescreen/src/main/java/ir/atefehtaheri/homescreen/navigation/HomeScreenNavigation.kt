package ir.atefehtaheri.homescreen.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import ir.atefehtaheri.homescreen.HomeScreen

const val HomeScreenRoute = "homescreen_route"


fun NavController.navigateTohomeScreen(navOptions: NavOptions? = null) {
    this.navigate(HomeScreenRoute, navOptions)
}

fun NavGraphBuilder.homeScreenDestination(onClick: (NavOptions?) -> Unit) {
    composable(route = HomeScreenRoute) {
        HomeScreen (onClick)
    }
}
