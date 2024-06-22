package ir.atefehtaheri.tvairing.repository

import androidx.paging.PagingData
import ir.atefehtaheri.common.models.ResultStatus
import ir.atefehtaheri.tvairing.repository.models.TvAiringDataModel
import ir.atefehtaheri.tvairing.repository.models.TvAiringListDataModel
import kotlinx.coroutines.flow.Flow

interface TvAiringRepository {
     companion object {
          const val NETWORK_PAGE_SIZE = 20
     }
     suspend fun getTvAiringPager(): ResultStatus<TvAiringListDataModel>
     fun getTvAiring(): Flow<PagingData<TvAiringDataModel>>

}