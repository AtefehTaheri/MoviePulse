package ir.atefehtaheri.tvairing.repository


import ir.atefehtaheri.commen.models.ResultStatus
import ir.atefehtaheri.database.MovieDatabase
import ir.atefehtaheri.tvairing.remote.TvAiringDatasource
import ir.atefehtaheri.tvairing.repository.models.TvAiringListDataModel
import ir.atefehtaheri.tvairing.repository.models.asTvAiringListDataModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TvAiringRepositoryImpl @Inject constructor(
    private val tvAiringDatasource: TvAiringDatasource,
//    private val movieDatabase: MovieDatabase,

    ) : TvAiringRepository {
    override suspend fun getTvAiringPager(): ResultStatus<TvAiringListDataModel> {
        return when (val result = tvAiringDatasource.getAiringPager()) {
            is ResultStatus.Failure -> ResultStatus.Failure(result.exception_message)
            is ResultStatus.Success -> ResultStatus.Success(result.data?.asTvAiringListDataModel())
        }
    }
}