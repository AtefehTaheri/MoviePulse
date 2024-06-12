package ir.atefehtaheri.upcominglist.repository

import androidx.paging.PagingData
import ir.atefehtaheri.commen.models.ResultStatus
import ir.atefehtaheri.upcominglist.repository.models.UpcomingListDataModel
import ir.atefehtaheri.upcominglist.repository.models.UpcomingMovieDataModel
import kotlinx.coroutines.flow.Flow

interface UpcomingListRepository {
    companion object {
        const val NETWORK_PAGE_SIZE = 10
    }
    suspend fun getUpcomingPager(): ResultStatus<UpcomingListDataModel>

    fun getUpcomingMovies(): Flow<PagingData<UpcomingMovieDataModel>>

}