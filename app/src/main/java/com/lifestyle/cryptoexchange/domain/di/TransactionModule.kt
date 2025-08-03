package com.lifestyle.cryptoexchange.domain.di

import com.lifestyle.cryptoexchange.data.repository.TransactionRepositoryImpl
import com.lifestyle.cryptoexchange.domain.repository.TransactionRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object TransactionModule {
    @Provides
    fun provideTransactionRepository(): TransactionRepository {
        return TransactionRepositoryImpl()
    }
}