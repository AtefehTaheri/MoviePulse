package ir.atefehtaheri.upcominglist.remote

import ir.atefehtaheri.commen.models.ResultStatus
import ir.atefehtaheri.upcominglist.remote.models.UpcomingListDto
import ir.atefehtaheri.upcominglist.remote.paging.UpcomingRemoteMediator

interface UpcomingListDatasource {

    suspend fun getUpcomingList(): ResultStatus<UpcomingListDto>

    fun getUpcomingRemoteMediator(): UpcomingRemoteMediator

}