package ir.atefehtaheri.detailitem.repository


import ir.atefehtaheri.common.models.ResultStatus
import ir.atefehtaheri.detailitem.remote.DetailItemDatasource
import ir.atefehtaheri.detailitem.repository.models.MovieDetailDataModel
import ir.atefehtaheri.detailitem.repository.models.TvShowDetailDataModel
import ir.atefehtaheri.detailitem.repository.models.asMovieDetailDataModel
import ir.atefehtaheri.detailitem.repository.models.asTvShowDetailDataModel
import javax.inject.Inject

class DetailItemRepositoryImpl @Inject constructor(
    private val DetailItemDatasource: DetailItemDatasource,
    ) : DetailItemRepository {
    override suspend fun getDetailMovie(movieid:String): ResultStatus<MovieDetailDataModel> {
        return when (val result = DetailItemDatasource.getDetailMovie(movieid)) {
            is ResultStatus.Failure -> ResultStatus.Failure(result.exception_message)
            is ResultStatus.Success -> ResultStatus.Success(result.data?.asMovieDetailDataModel())
        }
    }
    override suspend fun getDetailTvShow(tvshowid:String): ResultStatus<TvShowDetailDataModel> {
        return when (val result = DetailItemDatasource.getDetailTvShow(tvshowid)) {
            is ResultStatus.Failure -> ResultStatus.Failure(result.exception_message)
            is ResultStatus.Success -> ResultStatus.Success(result.data?.asTvShowDetailDataModel())
        }
    }
}