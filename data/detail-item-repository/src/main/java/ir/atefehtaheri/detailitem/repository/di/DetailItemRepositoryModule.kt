package ir.atefehtaheri.detailitem.repository.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.atefehtaheri.detailitem.repository.DetailItemRepository
import ir.atefehtaheri.detailitem.repository.DetailItemRepositoryImpl
import javax.inject.Singleton



@Module
@InstallIn(SingletonComponent::class)
interface DetailItemRepositoryModule {
    @Singleton
    @Binds
    fun getDetailItemRepositoryModule(
        detailItemRepositoryImpl: DetailItemRepositoryImpl
    ): DetailItemRepository
}