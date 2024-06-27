package ir.atefehtaheri.detailitem.remote.api

import ir.atefehtaheri.detailitem.remote.models.movie.MovieDetailDto
import ir.atefehtaheri.detailitem.remote.models.tvshow.TvShowDetailDto
import ir.atefehtaheri.network.BuildConfig
import ir.atefehtaheri.network.ErrorResponse
import ir.atefehtaheri.network.NetworkResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface DetailItemApi {


    @GET("3/movie/{movie_id}?")
    suspend fun getDetailMovie(
        @Path("movie_id") movie_id:String,
        @Query("append_to_response") append_to_response:String= "credits,images",
    ): NetworkResponse<MovieDetailDto, ErrorResponse>


    @GET("3/tv/{series_id}?")
    suspend fun getDetailTvShow(
        @Path("series_id") series_id:String,
        @Query("append_to_response") append_to_response:String= "credits,images",
    ): NetworkResponse<TvShowDetailDto, ErrorResponse>


}

