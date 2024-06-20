package ir.atefehtaheri.upcominglist.remote

import ir.atefehtaheri.common.models.ResultStatus
import ir.atefehtaheri.upcominglist.remote.models.UpcomingListDto
import ir.atefehtaheri.upcominglist.remote.paging.UpcomingRemoteMediator

interface UpcomingListDatasource {

    suspend fun getUpcomingPager(): ResultStatus<UpcomingListDto>

    fun getUpcomingRemoteMediator(): UpcomingRemoteMediator

}