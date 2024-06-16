package ir.atefehtaheri.toprated.repository


import ir.atefehtaheri.commen.models.ResultStatus
import ir.atefehtaheri.toprated.TopRatedTvShowDatasource
import ir.atefehtaheri.toprated.repository.models.TopRatedTvShowListDataModel
import ir.atefehtaheri.toprated.repository.models.asTopRatedTvShowListDataModel
import javax.inject.Inject

class TopRatedTvShowRepositoryImpl @Inject constructor(
    private val topRatedTvShowDatasource: TopRatedTvShowDatasource,
//    private val movieDatabase: MovieDatabase,

    ) : TopRatedTvShowRepository {
    override suspend fun getTopRatedTvShowPager(): ResultStatus<TopRatedTvShowListDataModel> {
        return when (val result = topRatedTvShowDatasource.getTopRatedTvShowPager()) {
            is ResultStatus.Failure -> ResultStatus.Failure(result.exception_message)
            is ResultStatus.Success -> ResultStatus.Success(result.data?.asTopRatedTvShowListDataModel())
        }
    }
}