package ir.atefehtaheri.toprated.remote

import ir.atefehtaheri.commen.models.ResultStatus
import ir.atefehtaheri.toprated.remote.models.TopRatedMovieDto

interface TopRatedMovieDatasource {

    suspend fun getTopRatedMoviePager(): ResultStatus<TopRatedMovieDto>



}