package ir.atefehtaheri.nowplaying.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import ir.atefehtaheri.common.models.Type
import ir.atefehtaheri.nowplaying.NowPlayingListRoute

const val NowPlayingRoute = "nowplayinglist_route"


fun NavController.navigateToNowplaying(navOptions: NavOptions? = null) {
    this.navigate(NowPlayingRoute, navOptions)
}

fun NavGraphBuilder.nowPlayingMovieDestination(
    onItemClick:(Type, String, NavOptions?) -> Unit
) {
    composable(route = NowPlayingRoute) {
        NowPlayingListRoute(onItemClick)

    }
}
