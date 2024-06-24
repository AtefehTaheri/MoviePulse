package ir.atefehtaheri.topratedtvshow.remote.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.atefehtaheri.topratedtvshow.remote.TopRatedTvShowDatasource
import ir.atefehtaheri.topratedtvshow.remote.TopRatedTvShowDatasourceImpl
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