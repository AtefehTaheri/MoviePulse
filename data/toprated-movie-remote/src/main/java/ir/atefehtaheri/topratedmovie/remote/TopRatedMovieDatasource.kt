package ir.atefehtaheri.topratedmovie.remote

import ir.atefehtaheri.common.models.ResultStatus
import ir.atefehtaheri.topratedmovie.remote.models.TopRatedMovieDto
import ir.atefehtaheri.topratedmovie.remote.pager.MovieTopRatedRemoteMediator

interface TopRatedMovieDatasource {

    suspend fun getTopRatedMoviePager(): ResultStatus<TopRatedMovieDto>

    fun getMovieTopRatedRemoteMediator(): MovieTopRatedRemoteMediator


}