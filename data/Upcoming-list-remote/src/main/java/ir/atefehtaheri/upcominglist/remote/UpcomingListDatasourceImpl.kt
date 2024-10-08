package ir.atefehtaheri.upcominglist.remote

import android.util.Log
import ir.atefehtaheri.common.models.ResultStatus
import ir.atefehtaheri.database.MovieDatabase
import ir.atefehtaheri.network.BuildConfig
import ir.atefehtaheri.network.NetworkResponse
import ir.atefehtaheri.upcominglist.remote.api.UpcomingListApi
import ir.atefehtaheri.upcominglist.remote.models.UpcomingListDto
import ir.atefehtaheri.upcominglist.remote.paging.UpcomingRemoteMediator
import javax.inject.Inject

class UpcomingListDatasourceImpl @Inject constructor(
    private val upcomingListApi : UpcomingListApi,
    private val movieDatabase: MovieDatabase,

    ): UpcomingListDatasource {
    override suspend fun getUpcomingPager(): ResultStatus<UpcomingListDto> {



       return when(val result =upcomingListApi.getUpcomingList()){
           is NetworkResponse.ApiError -> ResultStatus.Failure(result.body.message)
           is NetworkResponse.NetworkError -> ResultStatus.Failure(result.error.message ?: "NetworkError")
           is NetworkResponse.Success -> ResultStatus.Success(result.body)
           is NetworkResponse.UnknownError -> ResultStatus.Failure(result.error.message ?: "UnknownError")
       }
    }
    override fun getUpcomingRemoteMediator(): UpcomingRemoteMediator {
        return UpcomingRemoteMediator(upcomingListApi,movieDatabase)
    }

}