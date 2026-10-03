package com.ipeksavas.cryptotracker.di

import com.ipeksavas.cryptotracker.data.repository.RealCryptoRepositoryImpl
import com.ipeksavas.cryptotracker.domain.repository.CryptoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule { // <-- abstract class ve içinde sadece @Binds var!
    
    @Binds
    @Singleton
    abstract fun bindCryptoRepository(
        realRepository: RealCryptoRepositoryImpl
    ): CryptoRepository
}