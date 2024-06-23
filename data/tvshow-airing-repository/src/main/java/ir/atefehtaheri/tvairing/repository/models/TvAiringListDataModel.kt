package ir.atefehtaheri.tvairing.repository.models

import ir.atefehtaheri.database.entities.TvAiringEntity
import ir.atefehtaheri.tvairing.remote.models.Movie
import ir.atefehtaheri.tvairing.remote.models.TvAiringDto

data class TvAiringListDataModel(
    val airinglist: List<TvAiringDataModel>? = null,
)

data class TvAiringDataModel(
    val backdrop_path: String?,
    val id: Int,
    val name: String,
    val overview: String,
    val poster_path: String?,
    val first_air_date: String,
    val vote_average: Double
)


fun TvAiringDto.asTvAiringListDataModel(): TvAiringListDataModel {
    return TvAiringListDataModel(
        airinglist = this.results.map {
            it.asTvAiringMovieDataModel()
        }
    )

}

fun Movie.asTvAiringMovieDataModel(): TvAiringDataModel {
    return TvAiringDataModel(
        backdrop_path = backdrop_path,
        id = id,
        name = name,
        overview = overview,
        poster_path = poster_path,
        vote_average = vote_average,
        first_air_date=first_air_date
    )
}


fun TvAiringEntity.asTvAiringMovieDataModel(): TvAiringDataModel{
    return TvAiringDataModel(
        backdrop_path =backdrop_path,
        id =id,
        name =name,
        overview =overview,
        poster_path =poster_path,
        first_air_date =first_air_date,
        vote_average =vote_average
    )
}
