package ir.atefehtaheri.toprated.repository

import ir.atefehtaheri.common.models.ResultStatus
import ir.atefehtaheri.toprated.repository.models.TopRatedMovieListDataModel

interface TopRatedMovieRepository {

    suspend fun getTopRatedMoviePager(): ResultStatus<TopRatedMovieListDataModel>
}