package ir.atefehtaheri.homescreen.Uistate

import ir.atefehtaheri.toprated.repository.models.TopRatedTvShowListDataModel

data class TopRatedTvShowPagerState(
    val topRatedTvShowListDataModel: TopRatedTvShowListDataModel,
    val loading:Boolean=true
)
