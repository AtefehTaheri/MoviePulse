package ir.atefehtaheri.detailitem.remote

import ir.atefehtaheri.common.models.ResultStatus
import ir.atefehtaheri.detailitem.remote.models.movie.MovieDetailDto
import ir.atefehtaheri.detailitem.remote.models.tvshow.TvShowDetailDto

interface DetailItemDatasource {

    suspend fun getDetailMovie(movieid:String): ResultStatus<MovieDetailDto>
    suspend fun getDetailTvShow(tvshowid:String): ResultStatus<TvShowDetailDto>


}