package ir.atefehtaheri.detailitem.repository.models

import ir.atefehtaheri.detailitem.remote.models.tvshow.TvShowDetailDto

data class TvShowDetailDataModel(
    val adult: Boolean,
//    val backdrop_path: String,
//    val episode_run_time: List<Int>,
//    val first_air_date: String,
//    val homepage: String,
    val id: Int,
//    val in_production: Boolean,
//    val languages: List<String>,
//    val last_air_date: String,
    val name: String,
//    val number_of_episodes: Int,
//    val number_of_seasons: Int,
//    val origin_country: List<String>,
//    val original_language: String,
//    val original_name: String,
//    val overview: String,
//    val popularity: Double,
    val poster_path: String,
    val status: String,
//    val tagline: String,
//    val type: String,
    val vote_average: Double,
//    val vote_count: Int
)


fun TvShowDetailDto.asTvShowDetailDataModel():TvShowDetailDataModel{
    return TvShowDetailDataModel(
        adult=adult,
        poster_path=poster_path,
        name=name,
        id=id,
        vote_average= vote_average,
        status=status
    )

}