package ir.atefehtaheri.toprated.repository.models

import ir.atefehtaheri.database.entities.TvTopRatedEntity
import ir.atefehtaheri.toprated.models.Movie
import ir.atefehtaheri.toprated.models.TopRatedTvShowDto


data class TopRatedTvShowListDataModel(
    val topratedtvshowlist: List<TopRatedTvShowDataModel>? = null,
)

data class TopRatedTvShowDataModel(
    val backdrop_path: String?,
    val id: Int,
    val name: String,
    val overview: String,
    val poster_path: String?,
    val first_air_date: String,
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
        name = name,
        overview = overview,
        poster_path = poster_path,
        first_air_date = first_air_date,
        vote_average = vote_average
    )
}

fun TvTopRatedEntity.asTopRatedTvShowDataModel(): TopRatedTvShowDataModel{
    return TopRatedTvShowDataModel(
        backdrop_path =backdrop_path,
        id =id,
        name =name,
        overview =overview,
        poster_path =poster_path,
        first_air_date = first_air_date,
        vote_average =vote_average
    )
}


