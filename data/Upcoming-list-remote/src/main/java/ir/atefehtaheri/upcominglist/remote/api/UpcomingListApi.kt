package ir.atefehtaheri.upcominglist.remote.api

import ir.atefehtaheri.network.ErrorResponse
import ir.atefehtaheri.network.NetworkResponse
import ir.atefehtaheri.upcominglist.remote.models.UpcomingListDto
import retrofit2.http.GET
import retrofit2.http.Query


interface UpcomingListApi {


    @GET("/movie/upcoming?")
    suspend fun getUpcomingList(
        @Query("language") lat:String= "en-US",
        @Query("page") lon:Int = 1,
        @Query("api_key") key:String,
    ): NetworkResponse<UpcomingListDto, ErrorResponse>

}