package ir.atefehtaheri.toprated

import ir.atefehtaheri.common.models.ResultStatus
import ir.atefehtaheri.toprated.models.TopRatedTvShowDto
import ir.atefehtaheri.toprated.pager.TvTopRatedRemoteMediator

interface TopRatedTvShowDatasource {

    suspend fun getTopRatedTvShowPager(): ResultStatus<TopRatedTvShowDto>

    fun getTvTopRatedRemoteMediator(): TvTopRatedRemoteMediator


}