package com.ipeksavas.cryptotracker.di

import com.ipeksavas.cryptotracker.data.remote.dto.CoinApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import kotlin.jvm.java


//boilerplate
@Module
@InstallIn(SingletonComponent::class) // Bu modül tüm uygulama boyunca yaşayacak demektir
object AppModule {
    
    // 1. Retrofit nesnesini üreten pattern
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.coinpaprika.com/") // İnternette gideceğimiz ana adres (Base URL)
            .addConverterFactory(GsonConverterFactory.create()) // JSON'u Kotlin sınıflarına çeviren kurye
            .build()
    }
    
    // 2. CoinApi arayüzünü üreten pattern
    @Provides
    @Singleton
    fun provideCoinApi(retrofit: Retrofit): CoinApi {
        return retrofit.create(CoinApi::class.java) // Retrofit bizim soyut interface'imizi somut bir nesneye dönüştürür
    }
}