package ir.atefehtaheri.upcominglist.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import ir.atefehtaheri.upcominglist.UpcomingListRoute

const val UpcomingListRoute = "upcominglist_route"


fun NavController.navigateToUpcomingList(navOptions: NavOptions? = null) {
    this.navigate(UpcomingListRoute, navOptions)
}

fun NavGraphBuilder.upcomingListDestination() {
    composable(route = UpcomingListRoute) {
        UpcomingListRoute()

    }
}
