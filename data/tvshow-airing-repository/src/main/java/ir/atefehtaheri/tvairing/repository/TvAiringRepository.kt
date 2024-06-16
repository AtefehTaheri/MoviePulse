package ir.atefehtaheri.tvairing.repository

import ir.atefehtaheri.commen.models.ResultStatus
import ir.atefehtaheri.tvairing.repository.models.TvAiringListDataModel
import kotlinx.coroutines.flow.Flow

interface TvAiringRepository {

    suspend fun getTvAiringPager(): ResultStatus<TvAiringListDataModel>

}