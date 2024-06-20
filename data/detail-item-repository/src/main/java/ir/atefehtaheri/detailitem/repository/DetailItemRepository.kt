package ir.atefehtaheri.detailitem.repository

import ir.atefehtaheri.common.models.ResultStatus
import ir.atefehtaheri.common.models.Type
import ir.atefehtaheri.detailitem.repository.models.MovieDetailDataModel
import ir.atefehtaheri.detailitem.repository.models.TvShowDetailDataModel
import ir.atefehtaheri.detailitem.repository.models.backdropsDataModel

interface DetailItemRepository {

    suspend fun getDetailMovie(movieid:String): ResultStatus<MovieDetailDataModel>
    suspend fun getDetailTvShow(tvshowid:String): ResultStatus<TvShowDetailDataModel>
    suspend fun getBackdropsItem(id:String,type: Type): ResultStatus<backdropsDataModel>


}