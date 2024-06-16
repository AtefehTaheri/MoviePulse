package ir.atefehtaheri.weatherforecasts.data.currentweather.remote.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.atefehtaheri.tvairing.remote.TvAiringDatasource
import ir.atefehtaheri.tvairing.remote.TvAiringDatasourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface TvAiringDataSourceModule {

    @Singleton
    @Binds
    fun getTvAiringDataSourceModule(
        TvAiringDatasourceImpl: TvAiringDatasourceImpl
    ): TvAiringDatasource

}