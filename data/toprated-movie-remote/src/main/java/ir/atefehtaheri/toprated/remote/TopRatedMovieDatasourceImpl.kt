package ir.atefehtaheri.toprated.remote

import ir.atefehtaheri.common.models.ResultStatus
import ir.atefehtaheri.network.NetworkResponse
import ir.atefehtaheri.toprated.remote.api.TopRatedMovieApi
import ir.atefehtaheri.toprated.remote.models.TopRatedMovieDto
import javax.inject.Inject

class TopRatedMovieDatasourceImpl @Inject constructor(
    private val topRatedMovieApi : TopRatedMovieApi,
//    private val movieDatabase: MovieDatabase,

    ): TopRatedMovieDatasource {
    override suspend fun getTopRatedMoviePager(): ResultStatus<TopRatedMovieDto> {
       return when(val result =topRatedMovieApi.getTopRatedMovieList()){
           is NetworkResponse.ApiError -> ResultStatus.Failure(result.body.message)
           is NetworkResponse.NetworkError -> ResultStatus.Failure(result.error.message ?: "NetworkError")
           is NetworkResponse.Success -> ResultStatus.Success(result.body)
           is NetworkResponse.UnknownError -> ResultStatus.Failure(result.error.message ?: "UnknownError")
       }
    }


}