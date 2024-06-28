package ir.atefehtaheri.detailitem.repository.models

import ir.atefehtaheri.detailitem.remote.models.tvshow.TvShowDetailDto

data class TvShowDetailDataModel(
    val adult: Boolean,
//    val backdrop_path: String,
//    val created_by: List<CreatedBy>,
    val credits: Credits,
    val episode_run_time: List<Int>,
    val first_air_date: String,
    val genres: List<String>,
//    val homepage: String,
    val id: Int,
    val images: List<String>,
//    val in_production: Boolean,
//    val languages: List<String>,
    val last_air_date: String,
    val last_episode_to_air: Episode,
    val name: String,
//    val networks: List<Network>,
    val next_episode_to_air: Episode?,
    val number_of_episodes: Int,
    val number_of_seasons: Int,
//    val origin_country: List<String>,
//    val original_language: String,
//    val original_name: String,
    val overview: String,
//    val popularity: Double,
    val poster_path: String?,
//    val production_companies: List<ProductionCompany>,
//    val production_countries: List<ProductionCountry>,
    val seasons: List<Season>,
//    val spoken_languages: List<SpokenLanguage>,
    val status: String,
//    val tagline: String,
//    val type: String,
    val vote_average: Double,
//    val vote_count: Int
)

fun TvShowDetailDto.asTvShowDetailDataModel():TvShowDetailDataModel{
    return TvShowDetailDataModel(
        genres=genres.map{it.name},//
        adult=adult,//
        poster_path=poster_path,//
        name = name,//
        id=id,//
        first_air_date=first_air_date,//
        vote_average=vote_average,//
        status=status,//
        last_air_date=last_air_date,//
        overview=overview,//
        credits=Credits(
            credits.cast.map { Cast(it.name,it.profile_path) }
            ,credits.crew.map {Crew(it.job,it.name,it.profile_path)
            }),
        images=images.backdrops.map { it.file_path },//
        number_of_episodes=number_of_episodes,//
        number_of_seasons =number_of_seasons,//
        episode_run_time =episode_run_time,//
        last_episode_to_air =last_episode_to_air.run {
            Episode(episode_number,name,runtime,season_number,still_path,vote_average)
        }
                ,
        next_episode_to_air=next_episode_to_air?.let {
            Episode(it.episode_number,it.name,it.runtime,it.season_number,it.still_path,it.vote_average)
        },
        seasons=seasons.map { it.run{
            Season(
                episode_count,
                name,
                poster_path
                ,season_number
                ,vote_average)} }

    )


}