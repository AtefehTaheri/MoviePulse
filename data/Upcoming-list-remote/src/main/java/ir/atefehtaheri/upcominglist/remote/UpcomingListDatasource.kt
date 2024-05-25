package ir.atefehtaheri.upcominglist.remote

import ir.atefehtaheri.commen.models.ResultStatus
import ir.atefehtaheri.upcominglist.remote.models.UpcomingListDto

interface UpcomingListDatasource {

    suspend fun getUpcomingList(): ResultStatus<UpcomingListDto>
}