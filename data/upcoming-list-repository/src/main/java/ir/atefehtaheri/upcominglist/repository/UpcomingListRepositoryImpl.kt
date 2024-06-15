package ir.atefehtaheri.upcominglist.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import ir.atefehtaheri.commen.models.ResultStatus
import ir.atefehtaheri.database.MovieDatabase
import ir.atefehtaheri.upcominglist.remote.UpcomingListDatasource
import ir.atefehtaheri.upcominglist.repository.models.UpcomingListDataModel
import ir.atefehtaheri.upcominglist.repository.models.UpcomingMovieDataModel
import ir.atefehtaheri.upcominglist.repository.models.asUpcomingListDataModel
import ir.atefehtaheri.upcominglist.repository.models.asUpcomingMovieDataModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UpcomingListRepositoryImpl @Inject constructor(
    private val upcomingListDatasource: UpcomingListDatasource,
    private val movieDatabase: MovieDatabase,

    ) : UpcomingListRepository {
    override suspend fun getUpcomingPager(): ResultStatus<UpcomingListDataModel> {
        return when (val result = upcomingListDatasource.getUpcomingPager()) {
            is ResultStatus.Failure -> ResultStatus.Failure(result.exception_message)
            is ResultStatus.Success -> ResultStatus.Success(result.data?.asUpcomingListDataModel())
        }
    }

    @OptIn(ExperimentalPagingApi::class)
    override fun getUpcomingMovies(): Flow<PagingData<UpcomingMovieDataModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = UpcomingListRepository.NETWORK_PAGE_SIZE,
                enablePlaceholders = true,
            ),
            remoteMediator = upcomingListDatasource.getUpcomingRemoteMediator() ,
            pagingSourceFactory = { movieDatabase.upcomingMovieDao.pagingSourceUpcoming() }
        ).flow.map{
            it.map{
                it.asUpcomingMovieDataModel()
            } }
    }


}