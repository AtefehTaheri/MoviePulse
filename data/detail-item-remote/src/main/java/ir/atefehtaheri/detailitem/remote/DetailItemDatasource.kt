package ir.atefehtaheri.detailitem.remote

import ir.atefehtaheri.common.models.ResultStatus
import ir.atefehtaheri.common.models.Type
import ir.atefehtaheri.detailitem.remote.models.BackdropsitemDto
import ir.atefehtaheri.detailitem.remote.models.movie.MovieDtailDto
import ir.atefehtaheri.detailitem.remote.models.tvshow.TvShowDetailDto

interface DetailItemDatasource {

    suspend fun getDetailMovie(movieid:String): ResultStatus<MovieDtailDto>
    suspend fun getDetailTvShow(tvshowid:String): ResultStatus<TvShowDetailDto>
    suspend fun getBackdropsItem(id:String,type: Type): ResultStatus<BackdropsitemDto>


}