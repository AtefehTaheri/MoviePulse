package ir.atefehtaheri.topratedmovie.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import ir.atefehtaheri.topratedmovie.TopRatedListRoute

const val TopRatedRoute = "TopRated_route"


fun NavController.navigateToTopRated(navOptions: NavOptions? = null) {
    this.navigate(TopRatedRoute, navOptions)
}

fun NavGraphBuilder.TopRatedListDestination() {
    composable(route = TopRatedRoute) {
        TopRatedListRoute()

    }
}
