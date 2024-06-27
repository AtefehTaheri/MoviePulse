package ir.atefehtaheri.detailscreen.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import ir.atefehtaheri.common.models.Type
import ir.atefehtaheri.detailscreen.DetailScreen

const val DetailScreenRoute = "detailscreen_route?id={id}&type={type}"


fun NavController.navigateToDetailScreen(
    type: Type,
    id:String,
    navOptions: NavOptions? = null
) {
    this.navigate(DetailScreenRoute
        .replace("{id}", id)
        .replace("{type}", type.name)
        , navOptions)
}

fun NavGraphBuilder.detailscreenDestination() {

    composable(route = DetailScreenRoute,
            arguments = listOf(
            navArgument("id") {
                type = NavType.StringType
                defaultValue = ""
            },
        navArgument("type") {
            type = NavType.StringType
            defaultValue = ""
        }
    )
    ) {
        val id = it.arguments!!.getString("id")!!
        val typename = it.arguments!!.getString("type")!!
        val type= when(typename){
            Type.MOVIE.name -> Type.MOVIE
            else ->Type.TVSHOW
        }
        DetailScreen (type,id)
    }
}
