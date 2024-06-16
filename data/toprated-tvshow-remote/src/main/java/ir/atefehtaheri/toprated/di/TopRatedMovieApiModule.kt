package ir.atefehtaheri.toprated.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.atefehtaheri.network.di.createApiService
import ir.atefehtaheri.toprated.api.TopRatedTvShowApi
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TopRatedTvShowApiModule {

    @Provides
    @Singleton
    fun getTopRatedTvShowApiModule(retrofit: Retrofit): TopRatedTvShowApi {
        return createApiService(TopRatedTvShowApi::class.java, retrofit)
    }

}