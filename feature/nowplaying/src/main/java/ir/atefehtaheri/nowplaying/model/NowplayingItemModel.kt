package ir.atefehtaheri.nowplaying.model

import ir.atefehtaheri.common.models.Type
import ir.atefehtaheri.nowplaying.repository.models.NowPlayingDataModel
import ir.atefehtaheri.tvairing.repository.models.TvAiringDataModel

data class NowplayingItemModel(
    val type : Type,
    val id: Int,
    val title: String,
    val overview: String,
    val poster_path: String?,
    val release_date: String,
    val vote_average: Double
)


fun TvAiringDataModel.asNowplayingItem(): NowplayingItemModel {
    return NowplayingItemModel(
        type = Type.TVSHOW,
        id = id,
        title = name,
        overview = overview,
        poster_path = poster_path,
        release_date = first_air_date,
        vote_average = vote_average
    )
}

fun NowPlayingDataModel.asNowplayingItem(): NowplayingItemModel {
    return NowplayingItemModel(
        type = Type.MOVIE,
        id = id,
        title = title,
        overview = overview,
        poster_path = poster_path,
        release_date = release_date,
        vote_average = vote_average
    )
}