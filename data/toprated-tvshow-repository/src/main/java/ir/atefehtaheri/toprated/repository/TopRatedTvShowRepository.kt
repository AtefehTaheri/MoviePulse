package ir.atefehtaheri.toprated.repository

import androidx.paging.PagingData
import ir.atefehtaheri.common.models.ResultStatus
import ir.atefehtaheri.toprated.repository.models.TopRatedTvShowDataModel
import ir.atefehtaheri.toprated.repository.models.TopRatedTvShowListDataModel
import kotlinx.coroutines.flow.Flow

interface TopRatedTvShowRepository {
    companion object {
        const val NETWORK_PAGE_SIZE = 20
    }
    suspend fun getTopRatedTvShowPager(): ResultStatus<TopRatedTvShowListDataModel>
    fun getTopRatedTvShow(): Flow<PagingData<TopRatedTvShowDataModel>>

}