package ir.atefehtaheri.upcominglist.Uistate

import ir.atefehtaheri.upcominglist.repository.models.UpcomingListDataModel

data class UpcomingPagerState(
    val upcomingListDataModel: UpcomingListDataModel,
    val error:String?=null
)
