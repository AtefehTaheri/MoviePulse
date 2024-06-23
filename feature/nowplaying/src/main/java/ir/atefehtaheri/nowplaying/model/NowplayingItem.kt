package ir.atefehtaheri.nowplaying.model

import ir.atefehtaheri.nowplaying.repository.models.NowPlayingDataModel
import ir.atefehtaheri.tvairing.repository.models.TvAiringDataModel

data class NowplayingItem(
    val id: Int,
    val title: String,
    val overview: String,
    val poster_path: String?,
    val release_date: String,
    val vote_average: Double
)


fun TvAiringDataModel.asNowplayingItem(): NowplayingItem {
    return NowplayingItem(
        id = id,
        title = name,
        overview = overview,
        poster_path = overview,
        release_date = first_air_date,
        vote_average = vote_average
    )
}

fun NowPlayingDataModel.asNowplayingItem(): NowplayingItem {
    return NowplayingItem(
        id = id,
        title = title,
        overview = overview,
        poster_path = overview,
        release_date = release_date,
        vote_average = vote_average
    )
}