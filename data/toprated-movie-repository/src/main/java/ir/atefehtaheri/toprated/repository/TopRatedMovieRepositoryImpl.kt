package ir.atefehtaheri.toprated.repository


import ir.atefehtaheri.commen.models.ResultStatus
import ir.atefehtaheri.toprated.remote.TopRatedMovieDatasource
import ir.atefehtaheri.toprated.repository.models.TopRatedMovieListDataModel
import ir.atefehtaheri.toprated.repository.models.asTopRatedMovieListDataModel
import javax.inject.Inject

class TopRatedMovieRepositoryImpl @Inject constructor(
    private val topRatedMovieDatasource: TopRatedMovieDatasource,
//    private val movieDatabase: MovieDatabase,

    ) : TopRatedMovieRepository {
    override suspend fun getTopRatedMoviePager(): ResultStatus<TopRatedMovieListDataModel> {
        return when (val result = topRatedMovieDatasource.getTopRatedMoviePager()) {
            is ResultStatus.Failure -> ResultStatus.Failure(result.exception_message)
            is ResultStatus.Success -> ResultStatus.Success(result.data?.asTopRatedMovieListDataModel())
        }
    }
}