package ir.atefehtaheri.homescreen.Uistate

import ir.atefehtaheri.nowplaying.repository.models.NowPlayingListDataModel
import ir.atefehtaheri.tvairing.repository.models.TvAiringListDataModel
import ir.atefehtaheri.upcominglist.repository.models.UpcomingListDataModel

data class TvAiringPagerState(
    val tvAiringListDataModel: TvAiringListDataModel,
    val error:String?=null,
    val loading:Boolean=true
)
