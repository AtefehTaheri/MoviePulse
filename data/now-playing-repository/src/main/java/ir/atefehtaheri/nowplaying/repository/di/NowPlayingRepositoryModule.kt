package ir.atefehtaheri.nowplaying.repository.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.atefehtaheri.nowplaying.repository.NowPlayingRepository
import ir.atefehtaheri.nowplaying.repository.NowPlayingRepositoryImpl
import javax.inject.Singleton



@Module
@InstallIn(SingletonComponent::class)
interface NowPlayingRepositoryModule {
    @Singleton
    @Binds
    fun getNowPlayingRepositoryModule(
        nowPlayingRepositoryImpl: NowPlayingRepositoryImpl
    ): NowPlayingRepository
}