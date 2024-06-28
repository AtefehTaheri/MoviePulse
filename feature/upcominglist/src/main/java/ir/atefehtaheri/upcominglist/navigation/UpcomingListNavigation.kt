package ir.atefehtaheri.upcominglist.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import ir.atefehtaheri.common.models.Type
import ir.atefehtaheri.upcominglist.UpcomingListRoute

const val UpcomingListRoute = "upcominglist_route"


fun NavController.navigateToUpcomingList(navOptions: NavOptions? = null) {
    this.navigate(UpcomingListRoute, navOptions)
}

fun NavGraphBuilder.upcomingListDestination(
    onItemClick:(Type, String, NavOptions?) -> Unit

) {
    composable(route = UpcomingListRoute) {
        UpcomingListRoute(onItemClick)

    }
}
