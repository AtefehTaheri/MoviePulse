package ir.atefehtaheri.toprated

import ir.atefehtaheri.commen.models.ResultStatus
import ir.atefehtaheri.toprated.models.TopRatedTvShowDto

interface TopRatedTvShowDatasource {

    suspend fun getTopRatedTvShowPager(): ResultStatus<TopRatedTvShowDto>



}