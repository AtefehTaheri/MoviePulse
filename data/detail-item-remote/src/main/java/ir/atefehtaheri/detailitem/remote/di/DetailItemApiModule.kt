package ir.atefehtaheri.weatherforecasts.data.currentweather.remote.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.atefehtaheri.detailitem.remote.api.DetailItemApi
import ir.atefehtaheri.network.di.createApiService
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DetailItemApiModule {

    @Provides
    @Singleton
    fun getDetailItemApiModule(retrofit: Retrofit): DetailItemApi {
        return createApiService(DetailItemApi::class.java, retrofit)
    }

}