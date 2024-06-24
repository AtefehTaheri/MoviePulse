package ir.atefehtaheri.toprated.repository.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.atefehtaheri.toprated.repository.TopRatedMovieRepository
import ir.atefehtaheri.toprated.repository.TopRatedMovieRepositoryImpl
import javax.inject.Singleton



@Module
@InstallIn(SingletonComponent::class)
interface TopRatedMovieRepositoryModule {
    @Singleton
    @Binds
    fun getTopRatedMovieRepositoryModule(
        topRatedMovieRepositoryImpl: TopRatedMovieRepositoryImpl
    ): TopRatedMovieRepository
}