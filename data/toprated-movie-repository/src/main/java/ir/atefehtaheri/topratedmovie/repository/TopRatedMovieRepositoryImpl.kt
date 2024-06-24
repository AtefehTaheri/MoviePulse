package ir.atefehtaheri.toprated.repository


import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import ir.atefehtaheri.common.models.ResultStatus
import ir.atefehtaheri.database.MovieDatabase
import ir.atefehtaheri.toprated.remote.TopRatedMovieDatasource
import ir.atefehtaheri.toprated.repository.models.TopRatedMovieDataModel
import ir.atefehtaheri.toprated.repository.models.TopRatedMovieListDataModel
import ir.atefehtaheri.toprated.repository.models.asTopRatedMovieDataModel
import ir.atefehtaheri.toprated.repository.models.asTopRatedMovieListDataModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TopRatedMovieRepositoryImpl @Inject constructor(
    private val topRatedMovieDatasource: TopRatedMovieDatasource,
    private val movieDatabase: MovieDatabase,

    ) : TopRatedMovieRepository {
    override suspend fun getTopRatedMoviePager(): ResultStatus<TopRatedMovieListDataModel> {
        return when (val result = topRatedMovieDatasource.getTopRatedMoviePager()) {
            is ResultStatus.Failure -> ResultStatus.Failure(result.exception_message)
            is ResultStatus.Success -> ResultStatus.Success(result.data?.asTopRatedMovieListDataModel())
        }
    }

    @OptIn(ExperimentalPagingApi::class)
    override fun getTopRatedMovie(): Flow<PagingData<TopRatedMovieDataModel>> {

        return Pager(
            config = PagingConfig(
                pageSize = TopRatedMovieRepository.NETWORK_PAGE_SIZE,
                enablePlaceholders = true,
            ),
            remoteMediator = topRatedMovieDatasource.getMovieTopRatedRemoteMediator(),
            pagingSourceFactory = { movieDatabase.movieTopRatedDao.pagingSourceMovieTopRated() }
        ).flow.map {
            it.map {
                it.asTopRatedMovieDataModel()
            }
        }

    }
}