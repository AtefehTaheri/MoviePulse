package ir.atefehtaheri.toprated.repository

import ir.atefehtaheri.common.models.ResultStatus
import ir.atefehtaheri.toprated.repository.models.TopRatedTvShowListDataModel

interface TopRatedTvShowRepository {

    suspend fun getTopRatedTvShowPager(): ResultStatus<TopRatedTvShowListDataModel>

}