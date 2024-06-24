package ir.atefehtaheri.toprated.remote.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.atefehtaheri.toprated.remote.TopRatedTvShowDatasource
import ir.atefehtaheri.toprated.remote.TopRatedTvShowDatasourceImpl
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