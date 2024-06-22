package ir.atefehtaheri.tvairing.remote.models

import ir.atefehtaheri.database.entities.TvAiringEntity
import ir.atefehtaheri.database.entities.UpcomingMovieEntity

data class Movie(
    val adult: Boolean,
    val backdrop_path: String?,
    val first_air_date: String,
    val id: Int,
    val name: String,
    val origin_country: List<String>,
    val original_language: String,
    val original_name: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String?,
    val vote_average: Double,
    val vote_count: Int
)


fun Movie.asTvAiringEntity(): TvAiringEntity {
    return TvAiringEntity(

        id= id,
        backdrop_path=backdrop_path ,
        name= name,
        overview=overview ,
        poster_path=poster_path ,
        first_air_date= first_air_date,
        vote_average= vote_average
    )
}