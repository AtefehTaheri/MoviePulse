package ir.atefehtaheri.homescreen.Uistate

import ir.atefehtaheri.nowplaying.repository.models.NowPlayingListDataModel

data class NowPlayingPagerState(
    val nowPlayingListDataModel: NowPlayingListDataModel,
    val loading:Boolean=true

)
