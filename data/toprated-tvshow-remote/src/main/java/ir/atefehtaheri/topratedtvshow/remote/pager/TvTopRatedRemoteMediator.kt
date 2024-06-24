package ir.atefehtaheri.topratedtvshow.remote.pager

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import ir.atefehtaheri.database.MovieDatabase
import ir.atefehtaheri.database.entities.RemoteKey
import ir.atefehtaheri.database.entities.TvTopRatedEntity
import ir.atefehtaheri.network.NetworkResponse
import ir.atefehtaheri.topratedtvshow.remote.api.TopRatedTvShowApi
import ir.atefehtaheri.topratedtvshow.remote.models.asTvTopRatedEntity
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class TvTopRatedRemoteMediator @Inject constructor(
    private val topRatedTvShowApi: TopRatedTvShowApi,
    private val movieDatabase: MovieDatabase
) : RemoteMediator<Int, TvTopRatedEntity>() {

    private val remoteKeyDao = movieDatabase.remoteKeyDao
    private val tvTopRatedDao = movieDatabase.tvTopRatedDao

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, TvTopRatedEntity>
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
                        remoteKeyDao.getKeyByMovie("toprated_tvshow")
                    } ?: return MediatorResult.Success(true)


                    remoteKey.next_page ?:  return MediatorResult.Success(true)
                }
            }
            val networkResponse = topRatedTvShowApi.getTopRatedTvShowList(page = page)

            when (networkResponse) {
                is NetworkResponse.ApiError -> MediatorResult.Error(Throwable(networkResponse.body.message))
                is NetworkResponse.NetworkError -> MediatorResult.Error(networkResponse.error)
                is NetworkResponse.UnknownError -> MediatorResult.Error(networkResponse.error)
                is NetworkResponse.Success -> {

                    movieDatabase.withTransaction {
                        if(loadType == LoadType.REFRESH) {
                            tvTopRatedDao.clearAllTvTopRated()
                        }
                        val data =
                            networkResponse.body?.results?.map { it.asTvTopRatedEntity() }
                                ?: emptyList()

                        val nextPage = if(networkResponse.body?.results!!.isEmpty()) {
                            null
                        } else {
                            page + 1
                        }

                        remoteKeyDao.insertKey(
                            RemoteKey(
                            id = "toprated_tvshow",
                            next_page = nextPage,
                        )
                        )
                        tvTopRatedDao.insertTvTopRatedAll(data)
                    }
                    MediatorResult.Success(
                        endOfPaginationReached = networkResponse.body?.results!!.isEmpty()
                    )

                }}

        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }}




