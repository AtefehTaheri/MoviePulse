package ir.atefehtaheri.tvairing.remote.pager

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import ir.atefehtaheri.database.MovieDatabase
import ir.atefehtaheri.database.entities.RemoteKey
import ir.atefehtaheri.database.entities.TvAiringEntity
import ir.atefehtaheri.network.NetworkResponse
import ir.atefehtaheri.tvairing.remote.api.TvAiringApi
import ir.atefehtaheri.tvairing.remote.models.asTvAiringEntity
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class TvAiringRemoteMediator @Inject constructor(
    private val tvAiringApi: TvAiringApi,
    private val movieDatabase: MovieDatabase
) : RemoteMediator<Int, TvAiringEntity>() {

    private val remoteKeyDao = movieDatabase.remoteKeyDao
    private val tvAiringDao = movieDatabase.tvAiringDao

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, TvAiringEntity>
    ): MediatorResult {


        return try {
            val page = when (loadType) {
                LoadType.REFRESH -> {
                    1
                }

                LoadType.PREPEND -> {
                    return MediatorResult.Success(true)
                }

                LoadType.APPEND -> {
                    val remoteKey = movieDatabase.withTransaction {
                        remoteKeyDao.getKeyByMovie("airing_tvshow")
                    } ?: return MediatorResult.Success(true)


                    remoteKey.next_page ?:  return MediatorResult.Success(true)
                }
            }
            val networkResponse = tvAiringApi.getTvAiringList(page = page)

            when (networkResponse) {
                is NetworkResponse.ApiError -> MediatorResult.Error(Throwable(networkResponse.body.message))
                is NetworkResponse.NetworkError -> MediatorResult.Error(networkResponse.error)
                is NetworkResponse.UnknownError -> MediatorResult.Error(networkResponse.error)
                is NetworkResponse.Success -> {

                    movieDatabase.withTransaction {
                        if(loadType == LoadType.REFRESH) {
                            tvAiringDao.clearAllTvAiring()
                        }
                        val data =
                            networkResponse.body?.results?.map { it.asTvAiringEntity() }
                                ?: emptyList()

                        val nextPage = if(networkResponse.body?.results!!.isEmpty()) {
                            null
                        } else {
                            page + 1
                        }

                        remoteKeyDao.insertKey(
                            RemoteKey(
                            id = "airing_tvshow",
                            next_page = nextPage,
                        )
                        )
                        tvAiringDao.insertTvAiringAll(data)
                    }
                    MediatorResult.Success(
                        endOfPaginationReached = networkResponse.body?.results!!.isEmpty()
                    )

                }}

        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }}




