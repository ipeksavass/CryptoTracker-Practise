package com.ipeksavas.cryptotracker.data.remote.dto

import retrofit2.http.GET

interface CoinApi {
    @GET("v1/tickers")//dokümantasyondan aldım bu bilgiyi kafamıza göre istek atmıyoruz.
    suspend fun getCryptoCoinInfo(): List<CoinDto>
}