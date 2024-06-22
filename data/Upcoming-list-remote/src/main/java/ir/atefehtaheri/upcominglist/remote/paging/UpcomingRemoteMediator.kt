package ir.atefehtaheri.upcominglist.remote.paging

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import ir.atefehtaheri.database.MovieDatabase
import ir.atefehtaheri.database.entities.RemoteKey
import ir.atefehtaheri.database.entities.UpcomingMovieEntity
import ir.atefehtaheri.network.NetworkResponse
import ir.atefehtaheri.upcominglist.remote.api.UpcomingListApi
import ir.atefehtaheri.upcominglist.remote.models.asUpcomingMovieEntity
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class UpcomingRemoteMediator @Inject constructor(
    private val upcomingListApi: UpcomingListApi,
    private val movieDatabase: MovieDatabase
) : RemoteMediator<Int, UpcomingMovieEntity>() {

    private val remoteKeyDao = movieDatabase.remoteKeyDao
    private val movieDao = movieDatabase.upcomingMovieDao

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, UpcomingMovieEntity>
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
                        remoteKeyDao.getKeyByMovie("upcoming_movie")
                    } ?: return MediatorResult.Success(true)


                    remoteKey.next_page ?:  return MediatorResult.Success(true)
                }
            }
            Log.d("Paging",page.toString())
            Log.d("Paging",loadType.name)

            val networkResponse = upcomingListApi.getUpcomingList(page = page)

            when (networkResponse) {
                is NetworkResponse.ApiError -> MediatorResult.Error(Throwable(networkResponse.body.message))
                is NetworkResponse.NetworkError -> MediatorResult.Error(networkResponse.error)
                is NetworkResponse.UnknownError -> MediatorResult.Error(networkResponse.error)
                is NetworkResponse.Success -> {

                    movieDatabase.withTransaction {
                        if(loadType == LoadType.REFRESH) {
                            movieDao.clearAllUpcoming()
                        }
                        val data =
                            networkResponse.body?.results?.map { it.asUpcomingMovieEntity() }
                                ?: emptyList()

                        val nextPage = if(networkResponse.body?.results!!.isEmpty()) {
                            null
                        } else {
                            page + 1
                        }

                        remoteKeyDao.insertKey(
                            RemoteKey(
                            id = "upcoming_movie",
                            next_page = nextPage,
                        )
                        )
                        movieDao.insertUpcomingAll(data)
                    }
                    MediatorResult.Success(
                        endOfPaginationReached = networkResponse.body?.results!!.isEmpty()
                    )

                }}

        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }}




