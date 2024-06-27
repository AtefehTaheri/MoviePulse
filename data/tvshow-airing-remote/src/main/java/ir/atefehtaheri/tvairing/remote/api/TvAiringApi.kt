package ir.atefehtaheri.tvairing.remote.api

import ir.atefehtaheri.network.BuildConfig
import ir.atefehtaheri.network.ErrorResponse
import ir.atefehtaheri.network.NetworkResponse
import ir.atefehtaheri.tvairing.remote.models.TvAiringDto
import retrofit2.http.GET
import retrofit2.http.Query


interface TvAiringApi {


    @GET("3/tv/airing_today?")
    suspend fun getTvAiringList(
        @Query("language") language:String= "en-US",
        @Query("page") page:Int = 1,
    ): NetworkResponse<TvAiringDto, ErrorResponse>

}