package ir.atefehtaheri.toprated

import ir.atefehtaheri.common.models.ResultStatus
import ir.atefehtaheri.network.NetworkResponse
import ir.atefehtaheri.toprated.api.TopRatedTvShowApi
import ir.atefehtaheri.toprated.models.TopRatedTvShowDto
import javax.inject.Inject

class TopRatedTvShowDatasourceImpl @Inject constructor(
    private val topRatedTvShowApi : TopRatedTvShowApi,
//    private val movieDatabase: MovieDatabase,

    ): TopRatedTvShowDatasource {
    override suspend fun getTopRatedTvShowPager(): ResultStatus<TopRatedTvShowDto> {
       return when(val result =topRatedTvShowApi.getTopRatedTvShowList()){
           is NetworkResponse.ApiError -> ResultStatus.Failure(result.body.message)
           is NetworkResponse.NetworkError -> ResultStatus.Failure(result.error.message ?: "NetworkError")
           is NetworkResponse.Success -> ResultStatus.Success(result.body)
           is NetworkResponse.UnknownError -> ResultStatus.Failure(result.error.message ?: "UnknownError")
       }
    }


}