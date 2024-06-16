package ir.atefehtaheri.tvairing.remote

import ir.atefehtaheri.commen.models.ResultStatus
import ir.atefehtaheri.tvairing.remote.models.TvAiringDto

interface TvAiringDatasource {

    suspend fun getAiringPager(): ResultStatus<TvAiringDto>



}