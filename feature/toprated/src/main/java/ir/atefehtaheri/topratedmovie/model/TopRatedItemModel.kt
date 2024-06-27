package ir.atefehtaheri.topratedmovie.model

import ir.atefehtaheri.common.models.Type
import ir.atefehtaheri.topratedmovie.repository.models.TopRatedMovieDataModel
import ir.atefehtaheri.topratedtvshow.repository.models.TopRatedTvShowDataModel

data class TopRatedItemModel(
    val type: Type,
    val id: Int,
    val title: String,
    val overview: String,
    val poster_path: String?,
    val release_date: String,
    val vote_average: Double
)


fun TopRatedTvShowDataModel.asTopRatedItem(): TopRatedItemModel {
    return TopRatedItemModel(
        type=Type.TVSHOW,
        id = id,
        title = name,
        overview = overview,
        poster_path = poster_path,
        release_date = first_air_date,
        vote_average = vote_average
    )
}

fun TopRatedMovieDataModel.asTopRatedItem(): TopRatedItemModel {
    return TopRatedItemModel(
        type = Type.MOVIE,
        id = id,
        title = title,
        overview = overview,
        poster_path = poster_path,
        release_date = release_date,
        vote_average = vote_average
    )
}