package ir.atefehtaheri.toprated.pager

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import ir.atefehtaheri.database.MovieDatabase
import ir.atefehtaheri.database.entities.MovieTopRatedEntity
import ir.atefehtaheri.database.entities.NowPlayingMovieEntity
import ir.atefehtaheri.database.entities.RemoteKey
import ir.atefehtaheri.database.entities.TvTopRatedEntity
import ir.atefehtaheri.network.NetworkResponse
import ir.atefehtaheri.toprated.api.TopRatedTvShowApi
import ir.atefehtaheri.toprated.models.asTvTopRatedEntity
import ir.atefehtaheri.toprated.remote.api.TopRatedMovieApi
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class MovieTopRatedRemoteMediator @Inject constructor(
    private val topRatedMovieApi: TopRatedMovieApi,
    private val movieDatabase: MovieDatabase
) : RemoteMediator<Int, MovieTopRatedEntity>() {

    private val remoteKeyDao = movieDatabase.remoteKeyDao
    private val movieTopRatedDao = movieDatabase.movieTopRatedDao

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, MovieTopRatedEntity>
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
                        remoteKeyDao.getKeyByMovie("toprated_movie")
                    } ?: return MediatorResult.Success(true)


                    remoteKey.next_page ?:  return MediatorResult.Success(true)
                }
            }
            Log.d("Paging",page.toString())
            val networkResponse = topRatedMovieApi.getTopRatedMovieList(page = page)

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
                            id = "toprated_movie",
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




