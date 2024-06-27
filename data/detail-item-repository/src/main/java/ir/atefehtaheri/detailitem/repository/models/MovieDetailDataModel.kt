package ir.atefehtaheri.detailitem.repository.models

import ir.atefehtaheri.detailitem.remote.models.movie.MovieDetailDto


data class MovieDetailDataModel(
    val adult: Boolean,
//    val backdrop_path: String,
//    val belongs_to_collection: BelongsToCollection,
//    val budget: Int,
    val credits: Credits,
    val genres: List<String>,
//    val homepage: String,
    val id: Int,
    val images: List<String>,
//    val imdb_id: String,
//    val origin_country: List<String>,
//    val original_language: String,
//    val original_title: String,
    val overview: String,
//    val popularity: Double,
    val poster_path: String?,
//    val production_companies: List<ProductionCompany>,
//    val production_countries: List<ProductionCountry>,
    val release_date: String,
//    val revenue: Int,
    val runtime: Int,
//    val spoken_languages: List<SpokenLanguage>,
    val status: String,
//    val tagline: String,
    val title: String,
//    val video: Boolean,
    val vote_average: Double,
//    val vote_count: Int
)

fun MovieDetailDto.asMovieDetailDataModel():MovieDetailDataModel{
    return MovieDetailDataModel(
        genres=genres.map{it.name},
        adult=adult,
        poster_path=poster_path,
        title=title,
        id=id,
        runtime=runtime,
        vote_average=vote_average,
        status=status,
        release_date=release_date,
        overview=overview,
        credits=Credits(
            credits.cast.map { Cast(it.name,it.profile_path) }
            ,credits.crew.map {Crew(it.job,it.name,it.profile_path)
            }),
        images=images.backdrops.map { it.file_path }
        )

}