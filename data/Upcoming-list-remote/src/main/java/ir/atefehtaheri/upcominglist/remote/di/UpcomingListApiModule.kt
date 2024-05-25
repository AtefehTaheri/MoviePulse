package ir.atefehtaheri.weatherforecasts.data.currentweather.remote.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.atefehtaheri.network.di.createApiService
import ir.atefehtaheri.upcominglist.remote.api.UpcomingListApi
import retrofit2.Retrofit
import javax.inject.Singleton

//
//@Qualifier
//@Retention(AnnotationRetention.BINARY)
//annotation class CurrentWeather
@Module
@InstallIn(SingletonComponent::class)
object UpcomingListApiModule {

    @Provides
    @Singleton
    fun getUpcomingListApiModule(retrofit: Retrofit): UpcomingListApi {
        return createApiService(UpcomingListApi::class.java, retrofit)
    }

}