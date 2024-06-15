package ir.atefehtaheri.nowplaying.Uistate

import ir.atefehtaheri.nowplaying.repository.models.NowPlayingListDataModel

data class NowPlayingPagerState(
    val nowPlayingListDataModel: NowPlayingListDataModel,
    val error:String?=null
)
