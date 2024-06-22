package ir.atefehtaheri.nowplaying.remote.paging

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import ir.atefehtaheri.database.MovieDatabase
import ir.atefehtaheri.database.entities.NowPlayingMovieEntity
import ir.atefehtaheri.database.entities.RemoteKey
import ir.atefehtaheri.network.NetworkResponse
import ir.atefehtaheri.nowplaying.remote.api.NowPlayingApi
import ir.atefehtaheri.nowplaying.remote.models.asNowPlayingMovieEntity
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class NowPlayingRemoteMediator @Inject constructor(
    private val nowPlayingApi: NowPlayingApi,
    private val movieDatabase: MovieDatabase
) : RemoteMediator<Int, NowPlayingMovieEntity>() {

    private val remoteKeyDao = movieDatabase.remoteKeyDao
    private val nowPlayingDao = movieDatabase.nowPlayingMovieDao

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, NowPlayingMovieEntity>
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
                        remoteKeyDao.getKeyByMovie("nowplaying_movie")
                    } ?: return MediatorResult.Success(true)


                    remoteKey.next_page ?:  return MediatorResult.Success(true)
                }
            }
            Log.d("Paging",page.toString())
            val networkResponse = nowPlayingApi.getNowPlaying(page = page)

            when (networkResponse) {
                is NetworkResponse.ApiError -> MediatorResult.Error(Throwable(networkResponse.body.message))
                is NetworkResponse.NetworkError -> MediatorResult.Error(networkResponse.error)
                is NetworkResponse.UnknownError -> MediatorResult.Error(networkResponse.error)
                is NetworkResponse.Success -> {

                    movieDatabase.withTransaction {
                        if(loadType == LoadType.REFRESH) {
                            nowPlayingDao.clearAllNowPlayingMovie()
                        }
                        val data =
                            networkResponse.body?.results?.map { it.asNowPlayingMovieEntity() }
                                ?: emptyList()

                        val nextPage = if(networkResponse.body?.results!!.isEmpty()) {
                            null
                        } else {
                            page + 1
                        }

                        remoteKeyDao.insertKey(
                            RemoteKey(
                            id = "nowplaying_movie",
                            next_page = nextPage,
                        )
                        )
                        nowPlayingDao.insertAllNowPlayingMovie(data)
                    }
                    MediatorResult.Success(
                        endOfPaginationReached = networkResponse.body?.results!!.isEmpty()
                    )

                }}

        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }}




