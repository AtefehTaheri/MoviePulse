package ir.atefehtaheri.toprated.repository


import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import ir.atefehtaheri.common.models.ResultStatus
import ir.atefehtaheri.database.MovieDatabase
import ir.atefehtaheri.toprated.TopRatedTvShowDatasource
import ir.atefehtaheri.toprated.repository.models.TopRatedTvShowDataModel
import ir.atefehtaheri.toprated.repository.models.TopRatedTvShowListDataModel
import ir.atefehtaheri.toprated.repository.models.asTopRatedTvShowDataModel
import ir.atefehtaheri.toprated.repository.models.asTopRatedTvShowListDataModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TopRatedTvShowRepositoryImpl @Inject constructor(
    private val topRatedTvShowDatasource: TopRatedTvShowDatasource,
    private val movieDatabase: MovieDatabase,

    ) : TopRatedTvShowRepository {


    override suspend fun getTopRatedTvShowPager(): ResultStatus<TopRatedTvShowListDataModel> {
        return when (val result = topRatedTvShowDatasource.getTopRatedTvShowPager()) {
            is ResultStatus.Failure -> ResultStatus.Failure(result.exception_message)
            is ResultStatus.Success -> ResultStatus.Success(result.data?.asTopRatedTvShowListDataModel())
        }
    }

    @OptIn(ExperimentalPagingApi::class)
    override fun getTopRatedTvShow(): Flow<PagingData<TopRatedTvShowDataModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = TopRatedTvShowRepository.NETWORK_PAGE_SIZE,
                enablePlaceholders = true,
            ),
            remoteMediator = topRatedTvShowDatasource.getTvTopRatedRemoteMediator(),
            pagingSourceFactory = { movieDatabase.tvTopRatedDao.pagingSourceTvTopRated() }
        ).flow.map {
            it.map {
                it.asTopRatedTvShowDataModel()
            }
        }
    }
}