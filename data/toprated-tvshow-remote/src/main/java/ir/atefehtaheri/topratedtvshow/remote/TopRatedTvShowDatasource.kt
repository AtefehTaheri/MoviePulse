package ir.atefehtaheri.topratedtvshow.remote

import ir.atefehtaheri.common.models.ResultStatus
import ir.atefehtaheri.topratedtvshow.remote.models.TopRatedTvShowDto
import ir.atefehtaheri.topratedtvshow.remote.pager.TvTopRatedRemoteMediator

interface TopRatedTvShowDatasource {

    suspend fun getTopRatedTvShowPager(): ResultStatus<TopRatedTvShowDto>

    fun getTvTopRatedRemoteMediator(): TvTopRatedRemoteMediator


}