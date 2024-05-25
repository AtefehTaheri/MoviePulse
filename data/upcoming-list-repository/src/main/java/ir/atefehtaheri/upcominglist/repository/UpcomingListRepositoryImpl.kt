package ir.atefehtaheri.upcominglist.repository

import ir.atefehtaheri.commen.models.ResultStatus
import ir.atefehtaheri.upcominglist.remote.UpcomingListDatasource
import ir.atefehtaheri.upcominglist.repository.models.UpcomingListDataModel
import ir.atefehtaheri.upcominglist.repository.models.asUpcomingListDataModel
import javax.inject.Inject

class UpcomingListRepositoryImpl @Inject constructor(
    private val upcomingListDatasource: UpcomingListDatasource
) : UpcomingListRepository {
    override suspend fun getUpcomingList(): ResultStatus<UpcomingListDataModel> {
        return when (val result = upcomingListDatasource.getUpcomingList()) {
            is ResultStatus.Failure -> ResultStatus.Failure(result.exception_message)
            is ResultStatus.Success -> ResultStatus.Success(result.data?.asUpcomingListDataModel())
        }
    }
}