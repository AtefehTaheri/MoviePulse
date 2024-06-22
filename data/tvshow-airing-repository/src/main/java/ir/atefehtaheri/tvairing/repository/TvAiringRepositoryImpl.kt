package ir.atefehtaheri.tvairing.repository


import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import ir.atefehtaheri.common.models.ResultStatus
import ir.atefehtaheri.database.MovieDatabase
import ir.atefehtaheri.tvairing.remote.TvAiringDatasource
import ir.atefehtaheri.tvairing.repository.models.TvAiringDataModel
import ir.atefehtaheri.tvairing.repository.models.TvAiringListDataModel
import ir.atefehtaheri.tvairing.repository.models.asTvAiringListDataModel
import ir.atefehtaheri.tvairing.repository.models.asTvAiringMovieDataModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TvAiringRepositoryImpl @Inject constructor(
    private val tvAiringDatasource: TvAiringDatasource,
    private val movieDatabase: MovieDatabase,

    ) : TvAiringRepository {
    override suspend fun getTvAiringPager(): ResultStatus<TvAiringListDataModel> {
        return when (val result = tvAiringDatasource.getAiringPager()) {
            is ResultStatus.Failure -> ResultStatus.Failure(result.exception_message)
            is ResultStatus.Success -> ResultStatus.Success(result.data?.asTvAiringListDataModel())
        }
    }


    @OptIn(ExperimentalPagingApi::class)
    override fun getTvAiring(): Flow<PagingData<TvAiringDataModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = TvAiringRepository.NETWORK_PAGE_SIZE,
                enablePlaceholders = true,
            ),
            remoteMediator = tvAiringDatasource.getTvAiringRemoteMediator() ,
            pagingSourceFactory = { movieDatabase.tvAiringDao.pagingSourceTvAiring() }
        ).flow.map{
            it.map{
                it.asTvAiringMovieDataModel()
            } }
    }
}