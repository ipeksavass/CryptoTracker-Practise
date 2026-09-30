package com.ipeksavas.cryptotracker.domain.model

data class CryptoCoin(
    val id: String,
    val name: String,
    val price: Double,
    val changeRate: Double
)