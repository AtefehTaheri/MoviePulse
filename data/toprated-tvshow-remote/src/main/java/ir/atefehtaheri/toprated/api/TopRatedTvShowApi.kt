package ir.atefehtaheri.toprated.remote.api

import ir.atefehtaheri.network.ErrorResponse
import ir.atefehtaheri.network.NetworkResponse
import ir.atefehtaheri.toprated.remote.models.TopRatedMovieDto
import retrofit2.http.GET
import retrofit2.http.Query


interface TopRatedMovieApi {


    @GET("3/movie/top_rated?")
    suspend fun getTopRatedMovieList(
        @Query("language") language:String= "en-US",
        @Query("page") page:Int = 1,
        @Query("api_key") key:String  ="be0fe8d68a81b318e913dbc895f0fe97",
    ): NetworkResponse<TopRatedMovieDto, ErrorResponse>

}