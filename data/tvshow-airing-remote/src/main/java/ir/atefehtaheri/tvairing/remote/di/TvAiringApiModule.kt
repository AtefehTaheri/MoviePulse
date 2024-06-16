package ir.atefehtaheri.weatherforecasts.data.currentweather.remote.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.atefehtaheri.network.di.createApiService
import ir.atefehtaheri.tvairing.remote.api.TvAiringApi
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TvAiringApiModule {

    @Provides
    @Singleton
    fun getTvAiringApiModule(retrofit: Retrofit): TvAiringApi {
        return createApiService(TvAiringApi::class.java, retrofit)
    }

}