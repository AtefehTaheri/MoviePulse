package ir.atefehtaheri.topratedmovie.remote.models

import android.util.Log
import ir.atefehtaheri.database.entities.MovieTopRatedEntity

data class Movie(
    val adult: Boolean,
    val backdrop_path: String?,
//    val genre_ids: List<Int>,
    val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String?,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
)
fun Movie.asMovieTopRatedEntity(): MovieTopRatedEntity {

    return MovieTopRatedEntity(
        id= id,
        backdrop_path=backdrop_path ,
        title =title,
        overview=overview ,
        poster_path=poster_path ,
        release_date= release_date,
        vote_average= vote_average
    )
}