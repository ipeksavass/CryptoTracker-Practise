package com.ipeksavas.cryptotracker.domain.model

data class CryptoCoin(
    val id: String,
    val name: String,
    val symbol: String,
    val price: Double,
    val percentChange24h: Double
)