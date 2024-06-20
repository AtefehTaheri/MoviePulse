package ir.atefehtaheri.nowplaying.remote

import ir.atefehtaheri.common.models.ResultStatus
import ir.atefehtaheri.database.MovieDatabase
import ir.atefehtaheri.network.NetworkResponse
import ir.atefehtaheri.nowplaying.remote.api.NowPlayingApi
import ir.atefehtaheri.nowplaying.remote.models.NowPlayingDto
import ir.atefehtaheri.nowplaying.remote.paging.NowPlayingRemoteMediator
import javax.inject.Inject

class NowPlayingDatasourceImpl @Inject constructor(
    private val nowPlayingApi : NowPlayingApi,
    private val movieDatabase: MovieDatabase,

    ): NowPlayingDatasource {
    override suspend fun getNowPlayingPager(): ResultStatus<NowPlayingDto> {
       return when(val result =nowPlayingApi.getNowPlaying()){
           is NetworkResponse.ApiError -> ResultStatus.Failure(result.body.message)
           is NetworkResponse.NetworkError -> ResultStatus.Failure(result.error.message ?: "NetworkError")
           is NetworkResponse.Success -> ResultStatus.Success(result.body)
           is NetworkResponse.UnknownError -> ResultStatus.Failure(result.error.message ?: "UnknownError")
       }
    }
    override fun getNowPlayingRemoteMediator(): NowPlayingRemoteMediator {
        return NowPlayingRemoteMediator(nowPlayingApi,movieDatabase)
    }

}