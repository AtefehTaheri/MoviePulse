package ir.atefehtaheri.nowplaying.repository

import androidx.paging.PagingData
import ir.atefehtaheri.commen.models.ResultStatus
import ir.atefehtaheri.nowplaying.repository.models.NowPlayingDataModel
import ir.atefehtaheri.nowplaying.repository.models.NowPlayingListDataModel
import kotlinx.coroutines.flow.Flow

interface NowPlayingRepository {
    companion object {
        const val NETWORK_PAGE_SIZE = 10
    }
    suspend fun getNowPlayingPager(): ResultStatus<NowPlayingListDataModel>

    fun getNowPlayingMovies(): Flow<PagingData<NowPlayingDataModel>>

}