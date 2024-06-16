package ir.atefehtaheri.tvairing.repository.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.atefehtaheri.tvairing.repository.TvAiringRepository
import ir.atefehtaheri.tvairing.repository.TvAiringRepositoryImpl
import javax.inject.Singleton



@Module
@InstallIn(SingletonComponent::class)
interface TvAiringRepositoryModule {
    @Singleton
    @Binds
    fun getTvAiringRepositoryModule(
        tvAiringRepositoryImpl: TvAiringRepositoryImpl
    ): TvAiringRepository
}