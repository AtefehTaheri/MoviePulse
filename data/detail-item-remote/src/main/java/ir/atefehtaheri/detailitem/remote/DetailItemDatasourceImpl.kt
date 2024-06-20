package ir.atefehtaheri.detailitem.remote

import ir.atefehtaheri.common.models.ResultStatus
import ir.atefehtaheri.common.models.Type
import ir.atefehtaheri.detailitem.remote.api.DetailItemApi
import ir.atefehtaheri.detailitem.remote.models.BackdropsitemDto
import ir.atefehtaheri.detailitem.remote.models.movie.MovieDtailDto
import ir.atefehtaheri.detailitem.remote.models.tvshow.TvShowDetailDto
import ir.atefehtaheri.network.ErrorResponse
import ir.atefehtaheri.network.NetworkResponse
import javax.inject.Inject

class DetailItemDatasourceImpl @Inject constructor(
    private val detailItemApi: DetailItemApi,

    ) : DetailItemDatasource {


    override suspend fun getDetailMovie(movieid: String): ResultStatus<MovieDtailDto> {
        return when (val result = detailItemApi.getDetailMovie(movieid)) {
            is NetworkResponse.ApiError -> ResultStatus.Failure(result.body.message)
            is NetworkResponse.NetworkError -> ResultStatus.Failure(
                result.error.message ?: "NetworkError"
            )

            is NetworkResponse.Success -> ResultStatus.Success(result.body)
            is NetworkResponse.UnknownError -> ResultStatus.Failure(
                result.error.message ?: "UnknownError"
            )
        }
    }

    override suspend fun getDetailTvShow(tvshowid: String): ResultStatus<TvShowDetailDto> {
        return when (val result = detailItemApi.getDetailTvShow(tvshowid)) {
            is NetworkResponse.ApiError -> ResultStatus.Failure(result.body.message)
            is NetworkResponse.NetworkError -> ResultStatus.Failure(
                result.error.message ?: "NetworkError"
            )

            is NetworkResponse.Success -> ResultStatus.Success(result.body)
            is NetworkResponse.UnknownError -> ResultStatus.Failure(
                result.error.message ?: "UnknownError"
            )
        }
    }

    override suspend fun getBackdropsItem(id: String, type: Type): ResultStatus<BackdropsitemDto> {
        lateinit var result:NetworkResponse<BackdropsitemDto,ErrorResponse>
        when (type) {
            Type.TVSHOW ->{
                result= detailItemApi.getBackdropsTvShow(id)
            }
            Type.MOVIE ->{
                result=detailItemApi.getBackdropsMovie(id)
            }
        }

        return when ( result  ) {
            is NetworkResponse.ApiError -> ResultStatus.Failure(result.body.message)
            is NetworkResponse.NetworkError -> ResultStatus.Failure(
                result.error.message ?: "NetworkError"
            )
            is NetworkResponse.Success -> ResultStatus.Success(result.body)
            is NetworkResponse.UnknownError -> ResultStatus.Failure(
                result.error.message ?: "UnknownError"
            )
        }
    }
}