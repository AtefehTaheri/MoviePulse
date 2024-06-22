package ir.atefehtaheri.tvairing.remote

import ir.atefehtaheri.common.models.ResultStatus
import ir.atefehtaheri.database.MovieDatabase
import ir.atefehtaheri.network.NetworkResponse
import ir.atefehtaheri.tvairing.remote.api.TvAiringApi
import ir.atefehtaheri.tvairing.remote.models.TvAiringDto
import ir.atefehtaheri.tvairing.remote.pager.TvAiringRemoteMediator
import javax.inject.Inject

class TvAiringDatasourceImpl @Inject constructor(
    private val tvAiringApi : TvAiringApi,
    private val movieDatabase: MovieDatabase,

    ): TvAiringDatasource {
    override suspend fun getAiringPager(): ResultStatus<TvAiringDto> {
       return when(val result =tvAiringApi.getTvAiringList()){
           is NetworkResponse.ApiError -> ResultStatus.Failure(result.body.message)
           is NetworkResponse.NetworkError -> ResultStatus.Failure(result.error.message ?: "NetworkError")
           is NetworkResponse.Success -> ResultStatus.Success(result.body)
           is NetworkResponse.UnknownError -> ResultStatus.Failure(result.error.message ?: "UnknownError")
       }
    }
    override fun getTvAiringRemoteMediator(): TvAiringRemoteMediator {
        return TvAiringRemoteMediator(tvAiringApi,movieDatabase)
    }

}