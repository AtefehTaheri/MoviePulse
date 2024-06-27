package ir.atefehtaheri.topratedtvshow.remote.api

import ir.atefehtaheri.network.BuildConfig
import ir.atefehtaheri.network.ErrorResponse
import ir.atefehtaheri.network.NetworkResponse
import ir.atefehtaheri.topratedtvshow.remote.models.TopRatedTvShowDto
import retrofit2.http.GET
import retrofit2.http.Query


interface TopRatedTvShowApi {


    @GET("3/tv/top_rated?")
    suspend fun getTopRatedTvShowList(
        @Query("language") language:String= "en-US",
        @Query("page") page:Int = 1,
    ): NetworkResponse<TopRatedTvShowDto, ErrorResponse>

}