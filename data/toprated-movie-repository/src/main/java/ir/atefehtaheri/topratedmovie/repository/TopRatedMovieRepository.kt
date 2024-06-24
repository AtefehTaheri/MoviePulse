package ir.atefehtaheri.topratedmovie.repository

import androidx.paging.PagingData
import ir.atefehtaheri.common.models.ResultStatus
import ir.atefehtaheri.topratedmovie.repository.models.TopRatedMovieDataModel
import ir.atefehtaheri.topratedmovie.repository.models.TopRatedMovieListDataModel
import kotlinx.coroutines.flow.Flow

interface TopRatedMovieRepository {
    companion object {
        const val NETWORK_PAGE_SIZE = 20
    }
    suspend fun getTopRatedMoviePager(): ResultStatus<TopRatedMovieListDataModel>

    fun getTopRatedMovie(): Flow<PagingData<TopRatedMovieDataModel>>

}