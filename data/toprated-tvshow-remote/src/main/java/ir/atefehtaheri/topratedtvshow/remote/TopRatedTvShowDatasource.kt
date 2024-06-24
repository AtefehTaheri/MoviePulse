package ir.atefehtaheri.toprated.remote

import ir.atefehtaheri.common.models.ResultStatus
import ir.atefehtaheri.toprated.remote.models.TopRatedTvShowDto
import ir.atefehtaheri.toprated.remote.pager.TvTopRatedRemoteMediator

interface TopRatedTvShowDatasource {

    suspend fun getTopRatedTvShowPager(): ResultStatus<TopRatedTvShowDto>

    fun getTvTopRatedRemoteMediator(): TvTopRatedRemoteMediator


}