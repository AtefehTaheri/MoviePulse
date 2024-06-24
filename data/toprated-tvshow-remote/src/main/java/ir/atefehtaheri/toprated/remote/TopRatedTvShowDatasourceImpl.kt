package ir.atefehtaheri.toprated.remote

import ir.atefehtaheri.common.models.ResultStatus
import ir.atefehtaheri.database.MovieDatabase
import ir.atefehtaheri.network.NetworkResponse
import ir.atefehtaheri.toprated.remote.TopRatedTvShowDatasource
import ir.atefehtaheri.toprated.remote.api.TopRatedTvShowApi
import ir.atefehtaheri.toprated.remote.models.TopRatedTvShowDto
import ir.atefehtaheri.toprated.remote.pager.TvTopRatedRemoteMediator
import javax.inject.Inject

class TopRatedTvShowDatasourceImpl @Inject constructor(
    private val topRatedTvShowApi : TopRatedTvShowApi,
    private val movieDatabase: MovieDatabase,

    ): TopRatedTvShowDatasource {
    override suspend fun getTopRatedTvShowPager(): ResultStatus<TopRatedTvShowDto> {
       return when(val result =topRatedTvShowApi.getTopRatedTvShowList()){
           is NetworkResponse.ApiError -> ResultStatus.Failure(result.body.message)
           is NetworkResponse.NetworkError -> ResultStatus.Failure(result.error.message ?: "NetworkError")
           is NetworkResponse.Success -> ResultStatus.Success(result.body)
           is NetworkResponse.UnknownError -> ResultStatus.Failure(result.error.message ?: "UnknownError")
       }
    }

    override fun getTvTopRatedRemoteMediator(): TvTopRatedRemoteMediator {
        return TvTopRatedRemoteMediator(topRatedTvShowApi,movieDatabase)
    }


}