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
import kotlinx.coroutines.delay
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class UpcomingRemoteMediator @Inject constructor(
    private val upcomingListApi: UpcomingListApi,
    private val movieDatabase: MovieDatabase
) : RemoteMediator<Int, UpcomingMovieEntity>() {

    private val remoteKeyDao = movieDatabase.remoteKeyDao
    private val movieDao = movieDatabase.movieDao

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
                        remoteKeyDao.getKeyByMovie("discover_movie")
                    } ?: return MediatorResult.Success(true)


                    remoteKey.next_page ?:  return MediatorResult.Success(true)
                }
            }
            Log.d("Paging",page.toString())
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
                            id = "discover_movie",
                            next_page = nextPage,
                        )
                        )
                        movieDao.upsertUpcomingAll(data)
                    }

                    MediatorResult.Success(
                        endOfPaginationReached = networkResponse.body?.results!!.isEmpty()
                    )

                }}


        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }}






//        val page = when (loadType) {
//            LoadType.REFRESH -> {
//                // Refresh should start from page 1
//                1
//            }
//
//            LoadType.PREPEND -> {
//                // No need to prepend in this example
//                return MediatorResult.Success(endOfPaginationReached = true)
//            }
//
//            LoadType.APPEND -> {
//                delay(5000)
////                val lastItem = state.lastItemOrNull()
////                val lastItem= movieDatabase.remoteKeyDao.getLastItem()
////                lastItem?.let {
////                   val remotekey = movieDatabase.remoteKeyDao.remoteKeyById(it.id)
////                    if (lastItem!!.page == lastItem!!.total_pages)
////                            return MediatorResult.Success(endOfPaginationReached = true)
////                    else
////                        (lastItem!!.page!! + 1 )
//                }
////                    ?: return MediatorResult.Success(endOfPaginationReached = true)
//
//
////                Log.d("pagingrr", lastItem?.id.toString())
////            }
//
//        }
//
//
//        return try {
//
//
////            if (loadType == LoadType.REFRESH) {
//                val networkResponse = upcomingListApi.getUpcomingList(page = page)
//
//                when (networkResponse) {
//                    is NetworkResponse.ApiError -> MediatorResult.Error(Throwable(networkResponse.body.message))
//                    is NetworkResponse.NetworkError -> MediatorResult.Error(networkResponse.error)
//                    is NetworkResponse.UnknownError -> MediatorResult.Error(networkResponse.error)
//                    is NetworkResponse.Success -> {
//                        val data =
//                            networkResponse.body?.results?.map { it.asUpcomingMovieEntity() }
//                                ?: emptyList()
//
//                        var keys :List<RemoteKey> = emptyList()
//
//                            networkResponse.body?.let { upcominglistDto ->
//
//                                 keys = upcominglistDto.results?.map {
//                                    RemoteKey(it.id,upcominglistDto.page,upcominglistDto.total_pages)
//                                } ?: emptyList()
//
//
//
//                            }
//
//
//                        movieDatabase.withTransaction {
//                            if (loadType == LoadType.REFRESH) {
//                                movieDatabase.movieDao.clearAllUpcoming()
//                            movieDatabase.remoteKeyDao.clearRemoteKeys()
//                            }
//
////                        val nextPageKey = if (data.isEmpty()) {
////                            null
////                        } else {
////                            page + 1
////                        }
//
//
////                    var keys = result.body!!.results!!.map {
////                        RemoteKey(id = it.id, prevKey = prevKey, nextKey = nextKey)
////                    }
//
////                        val keys = data.map {
////                            RemoteKey(id = it.id, nextKey = nextPageKey)
////                        }
//                        movieDatabase.remoteKeyDao.insertAll(keys)
//                            movieDatabase.movieDao.upsertUpcomingAll(data)
//                        }
//                        MediatorResult.Success(endOfPaginationReached = data.isEmpty())
//
//
//                    }
//
//                }
//
//
//        } catch (e: Exception) {
//            Log.e("paging", "Error loading data", e)
//            MediatorResult.Error(e)
//
//        }
//
//
////       return MediatorResult.Success(endOfPaginationReached =true)
//
//
//    }
//}