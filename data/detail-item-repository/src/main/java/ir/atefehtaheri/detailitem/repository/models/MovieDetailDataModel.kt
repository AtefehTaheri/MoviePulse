package ir.atefehtaheri.detailitem.repository.models

import ir.atefehtaheri.detailitem.remote.models.movie.MovieDtailDto

data class MovieDetailDataModel(
    val adult: Boolean,
//    val backdrop_path: String,
//    val budget: Int,
//    val homepage: String,
    val id: Int,
//    val imdb_id: String,
//    val origin_country: List<String>,
//    val original_language: String,
//    val original_title: String,
//    val overview: String,
//    val popularity: Double,
    val poster_path: String?,
    val release_date: String,
//    val revenue: Int,
    val runtime: Int,
    val status: String,
//    val tagline: String,
    val title: String,
//    val video: Boolean,
    val vote_average: Double,
//    val vote_count: Int
)

fun MovieDtailDto.asMovieDetailDataModel():MovieDetailDataModel{
    return MovieDetailDataModel(
        adult=adult,
        poster_path=poster_path,
        title=title,
        id=id,
        runtime=runtime,
        vote_average=vote_average,
        status=status,
        release_date=release_date
        )

}