package ir.atefehtaheri.homescreen.Uistate

import ir.atefehtaheri.toprated.repository.models.TopRatedMovieListDataModel

data class TopRatedMoviePagerState(
    val topRatedMovieListDataModel: TopRatedMovieListDataModel,
    val loading:Boolean=true
)
