package ir.atefehtaheri.tvairing.repository.models

import ir.atefehtaheri.database.entities.UpcomingMovieEntity
import ir.atefehtaheri.tvairing.remote.models.Movie
import ir.atefehtaheri.tvairing.remote.models.TvAiringDto

data class TvAiringListDataModel(
    val airinglist: List<TvAiringMovieDataModel>? = null,
)

data class TvAiringMovieDataModel(
    val backdrop_path: String?,
    val id: Int,
    val name: String,
    val overview: String,
    val poster_path: String?,
    val vote_average: Double
)


fun TvAiringDto.asTvAiringListDataModel(): TvAiringListDataModel {
    return TvAiringListDataModel(
        airinglist = this.results.map {
            it.asTvAiringMovieDataModel()
        }
    )

}

fun Movie.asTvAiringMovieDataModel(): TvAiringMovieDataModel {
    return TvAiringMovieDataModel(
        backdrop_path = backdrop_path,
        id = id,
        name = name,
        overview = overview,
        poster_path = poster_path,
        vote_average = vote_average
    )
}
