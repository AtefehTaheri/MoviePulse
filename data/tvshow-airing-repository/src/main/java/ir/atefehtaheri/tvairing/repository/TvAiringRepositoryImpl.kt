package ir.atefehtaheri.tvairing.repository


import ir.atefehtaheri.common.models.ResultStatus
import ir.atefehtaheri.tvairing.remote.TvAiringDatasource
import ir.atefehtaheri.tvairing.repository.models.TvAiringListDataModel
import ir.atefehtaheri.tvairing.repository.models.asTvAiringListDataModel
import javax.inject.Inject

class TvAiringRepositoryImpl @Inject constructor(
    private val tvAiringDatasource: TvAiringDatasource,

    ) : TvAiringRepository {
    override suspend fun getTvAiringPager(): ResultStatus<TvAiringListDataModel> {
        return when (val result = tvAiringDatasource.getAiringPager()) {
            is ResultStatus.Failure -> ResultStatus.Failure(result.exception_message)
            is ResultStatus.Success -> ResultStatus.Success(result.data?.asTvAiringListDataModel())
        }
    }
}