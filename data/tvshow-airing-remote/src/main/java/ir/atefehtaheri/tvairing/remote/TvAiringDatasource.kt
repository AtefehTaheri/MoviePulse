package ir.atefehtaheri.tvairing.remote

import ir.atefehtaheri.common.models.ResultStatus
import ir.atefehtaheri.tvairing.remote.models.TvAiringDto
import ir.atefehtaheri.tvairing.remote.pager.TvAiringRemoteMediator

interface TvAiringDatasource {

    suspend fun getAiringPager(): ResultStatus<TvAiringDto>

    fun getTvAiringRemoteMediator(): TvAiringRemoteMediator


}