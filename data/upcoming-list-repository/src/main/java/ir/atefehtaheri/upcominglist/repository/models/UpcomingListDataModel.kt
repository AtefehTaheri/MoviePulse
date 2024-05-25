package ir.atefehtaheri.upcominglist.repository.models

import ir.atefehtaheri.upcominglist.remote.models.Movie
import ir.atefehtaheri.upcominglist.remote.models.UpcomingListDto

data class UpcomingListDataModel(
    val upcominglist: List<UpcomingMovieDataModel>? = null,
)

data class UpcomingMovieDataModel(
    val backdrop_path: String,
    val genre_ids: List<Int>,
    val id: Int,
    val original_title: String,
    val overview: String,
    val poster_path: String,
    val release_date: String,
    val vote_average: Double
)


fun UpcomingListDto.asUpcomingListDataModel(): UpcomingListDataModel {
    return UpcomingListDataModel(
        upcominglist = this.movies.map {
            it.asUpcomingMovieDataModel()
        }
    )

}

fun Movie.asUpcomingMovieDataModel(): UpcomingMovieDataModel {
    return UpcomingMovieDataModel(
        backdrop_path = backdrop_path,
        genre_ids = genre_ids,
        id = id,
        original_title = original_title,
        overview = overview,
        poster_path = poster_path,
        release_date = release_date,
        vote_average = vote_average
    )
}