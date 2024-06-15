package ir.atefehtaheri.nowplaying.remote

import ir.atefehtaheri.commen.models.ResultStatus
import ir.atefehtaheri.nowplaying.remote.models.NowPlayingDto
import ir.atefehtaheri.nowplaying.remote.paging.NowPlayingRemoteMediator

interface NowPlayingDatasource {

    suspend fun getNowPlayingPager(): ResultStatus<NowPlayingDto>

    fun getNowPlayingRemoteMediator(): NowPlayingRemoteMediator

}