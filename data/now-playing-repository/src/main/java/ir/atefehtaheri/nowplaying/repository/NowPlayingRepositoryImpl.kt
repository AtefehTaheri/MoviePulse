package ir.atefehtaheri.nowplaying.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import ir.atefehtaheri.commen.models.ResultStatus
import ir.atefehtaheri.database.MovieDatabase
import ir.atefehtaheri.nowplaying.remote.NowPlayingDatasource
import ir.atefehtaheri.nowplaying.repository.models.NowPlayingDataModel
import ir.atefehtaheri.nowplaying.repository.models.NowPlayingListDataModel
import ir.atefehtaheri.nowplaying.repository.models.asNowPlayingDataModel
import ir.atefehtaheri.nowplaying.repository.models.asNowPlayingListDataModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NowPlayingRepositoryImpl @Inject constructor(
    private val nowPlayingDatasource: NowPlayingDatasource,
    private val movieDatabase: MovieDatabase
    ) : NowPlayingRepository {

    override suspend fun getNowPlayingPager(): ResultStatus<NowPlayingListDataModel> {
        return when (val result = nowPlayingDatasource.getNowPlayingPager()) {
            is ResultStatus.Failure -> ResultStatus.Failure(result.exception_message)
            is ResultStatus.Success -> ResultStatus.Success(result.data?.asNowPlayingListDataModel())
        }
    }

    @OptIn(ExperimentalPagingApi::class)
    override fun getNowPlayingMovies(): Flow<PagingData<NowPlayingDataModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = NowPlayingRepository.NETWORK_PAGE_SIZE,
                enablePlaceholders = true,
            ),
            remoteMediator = nowPlayingDatasource.getNowPlayingRemoteMediator(),
            pagingSourceFactory = { movieDatabase.nowPlayingMovieDao.pagingSourceNowPlayingMovie() }
        ).flow.map {
            it.map {
                it.asNowPlayingDataModel()
            }
        }
    }


}