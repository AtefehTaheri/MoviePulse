package ir.atefehtaheri.toprated

import ir.atefehtaheri.common.models.ResultStatus
import ir.atefehtaheri.toprated.models.TopRatedTvShowDto

interface TopRatedTvShowDatasource {

    suspend fun getTopRatedTvShowPager(): ResultStatus<TopRatedTvShowDto>



}