package ir.atefehtaheri.weatherforecasts.data.currentweather.remote.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.atefehtaheri.detailitem.remote.DetailItemDatasource
import ir.atefehtaheri.detailitem.remote.DetailItemDatasourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DetailItemDataSourceModule {

    @Singleton
    @Binds
    fun getDetailItemDataSourceModule(
        detailItemDatasourceImpl: DetailItemDatasourceImpl
    ): DetailItemDatasource

}