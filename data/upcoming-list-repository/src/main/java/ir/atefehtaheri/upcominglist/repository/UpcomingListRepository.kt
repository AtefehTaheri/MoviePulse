package ir.atefehtaheri.upcominglist.repository

import ir.atefehtaheri.commen.models.ResultStatus
import ir.atefehtaheri.upcominglist.repository.models.UpcomingListDataModel

interface UpcomingListRepository {

    suspend fun getUpcomingList(): ResultStatus<UpcomingListDataModel>
}