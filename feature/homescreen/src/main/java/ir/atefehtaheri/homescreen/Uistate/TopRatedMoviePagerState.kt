package ir.atefehtaheri.homescreen.Uistate

import ir.atefehtaheri.topratedmovie.repository.models.TopRatedMovieListDataModel

data class TopRatedMoviePagerState(
    val topRatedMovieListDataModel: TopRatedMovieListDataModel,
    val loading:Boolean=true
)
