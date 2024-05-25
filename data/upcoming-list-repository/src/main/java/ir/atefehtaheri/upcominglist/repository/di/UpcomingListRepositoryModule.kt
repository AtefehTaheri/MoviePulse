package ir.atefehtaheri.upcominglist.repository.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.atefehtaheri.upcominglist.repository.UpcomingListRepository
import ir.atefehtaheri.upcominglist.repository.UpcomingListRepositoryImpl
import javax.inject.Singleton



@Module
@InstallIn(SingletonComponent::class)
interface UpcomingListRepositoryModule {
    @Singleton
    @Binds
    fun getUpcomingListRepositoryModule(
        upcomingListRepositoryImpl: UpcomingListRepositoryImpl
    ): UpcomingListRepository
}