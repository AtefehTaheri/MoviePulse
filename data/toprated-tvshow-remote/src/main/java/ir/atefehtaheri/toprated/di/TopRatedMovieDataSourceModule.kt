package ir.atefehtaheri.toprated.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.atefehtaheri.toprated.TopRatedTvShowDatasource
import ir.atefehtaheri.toprated.TopRatedTvShowDatasourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface TopRatedTvShowDataSourceModule {

    @Singleton
    @Binds
    fun getTopRatedTvShowDataSourceModule(
        topRatedTvShowDatasourceImpl: TopRatedTvShowDatasourceImpl
    ): TopRatedTvShowDatasource

}