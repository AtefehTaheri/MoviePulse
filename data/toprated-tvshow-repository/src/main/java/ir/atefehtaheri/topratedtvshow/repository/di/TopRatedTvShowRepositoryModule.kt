package ir.atefehtaheri.topratedtvshow.repository.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.atefehtaheri.topratedtvshow.repository.TopRatedTvShowRepository
import ir.atefehtaheri.topratedtvshow.repository.TopRatedTvShowRepositoryImpl
import javax.inject.Singleton



@Module
@InstallIn(SingletonComponent::class)
interface TopRatedTvShowRepositoryModule {
    @Singleton
    @Binds
    fun getTopRatedTvShowRepositoryModule(
        topRatedTvShowRepositoryImpl: TopRatedTvShowRepositoryImpl
    ): TopRatedTvShowRepository
}