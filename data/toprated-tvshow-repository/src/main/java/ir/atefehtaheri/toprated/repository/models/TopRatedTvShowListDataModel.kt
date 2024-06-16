package ir.atefehtaheri.toprated.repository.models

import android.util.Log
import ir.atefehtaheri.toprated.models.Movie
import ir.atefehtaheri.toprated.models.TopRatedTvShowDto


data class TopRatedTvShowListDataModel(
    val topratedtvshowlist: List<TopRatedTvShowDataModel>? = null,
)

data class TopRatedTvShowDataModel(
    val backdrop_path: String?,
    val id: Int,
    val title: String,
    val overview: String,
    val poster_path: String?,
    val vote_average: Double
)


fun TopRatedTvShowDto.asTopRatedTvShowListDataModel(): TopRatedTvShowListDataModel {
    return TopRatedTvShowListDataModel(
        topratedtvshowlist = this.results.map {
            it.asTopRatedTvShowDataModel()
        }
    )

}

fun Movie.asTopRatedTvShowDataModel(): TopRatedTvShowDataModel {
    return TopRatedTvShowDataModel(
        backdrop_path = backdrop_path,
        id = id,
        title = name,
        overview = overview,
        poster_path = poster_path,
        vote_average = vote_average
    )
}

