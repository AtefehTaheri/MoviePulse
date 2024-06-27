package ir.atefehtaheri.upcominglist.repository.models

import ir.atefehtaheri.common.models.Type
import ir.atefehtaheri.database.entities.UpcomingMovieEntity
import ir.atefehtaheri.upcominglist.remote.models.Movie
import ir.atefehtaheri.upcominglist.remote.models.UpcomingListDto

data class UpcomingListDataModel(
    val upcominglist: List<UpcomingMovieDataModel>? = null,
)

data class UpcomingMovieDataModel(
    val type: Type,
    val backdrop_path: String?,
    val id: Int,
    val title: String,
    val overview: String,
    val poster_path: String?,
    val release_date: String,
    val vote_average: Double
)


fun UpcomingListDto.asUpcomingListDataModel(): UpcomingListDataModel {
    return UpcomingListDataModel(
        upcominglist = this.results.map {
            it.asUpcomingMovieDataModel()
        }
    )

}

fun Movie.asUpcomingMovieDataModel(): UpcomingMovieDataModel {
    return UpcomingMovieDataModel(
        type = Type.MOVIE,
        backdrop_path = backdrop_path,
        id = id,
        title = title,
        overview = overview,
        poster_path = poster_path,
        release_date = release_date,
        vote_average = vote_average
    )
}

fun UpcomingMovieEntity.asUpcomingMovieDataModel(): UpcomingMovieDataModel{
    return UpcomingMovieDataModel(
        type = Type.MOVIE,
        backdrop_path =backdrop_path,
        id =id,
        title =title,
        overview =overview,
        poster_path =poster_path,
        release_date =release_date,
        vote_average =vote_average
    )
}