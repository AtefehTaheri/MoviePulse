package ir.atefehtaheri.topratedmovie.remote

import ir.atefehtaheri.common.models.ResultStatus
import ir.atefehtaheri.database.MovieDatabase
import ir.atefehtaheri.network.NetworkResponse
import ir.atefehtaheri.topratedmovie.remote.api.TopRatedMovieApi
import ir.atefehtaheri.topratedmovie.remote.models.TopRatedMovieDto
import ir.atefehtaheri.topratedmovie.remote.pager.MovieTopRatedRemoteMediator
import javax.inject.Inject

class TopRatedMovieDatasourceImpl @Inject constructor(
    private val topRatedMovieApi : TopRatedMovieApi,
    private val movieDatabase: MovieDatabase,

    ): TopRatedMovieDatasource {
    override suspend fun getTopRatedMoviePager(): ResultStatus<TopRatedMovieDto> {
       return when(val result =topRatedMovieApi.getTopRatedMovieList()){
           is NetworkResponse.ApiError -> ResultStatus.Failure(result.body.message)
           is NetworkResponse.NetworkError -> ResultStatus.Failure(result.error.message ?: "NetworkError")
           is NetworkResponse.Success -> ResultStatus.Success(result.body)
           is NetworkResponse.UnknownError -> ResultStatus.Failure(result.error.message ?: "UnknownError")
       }
    }

    override fun getMovieTopRatedRemoteMediator(): MovieTopRatedRemoteMediator {
        return MovieTopRatedRemoteMediator(topRatedMovieApi,movieDatabase)

    }


}