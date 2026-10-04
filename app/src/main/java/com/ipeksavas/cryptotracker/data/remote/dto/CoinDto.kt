package com.ipeksavas.cryptotracker.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.ipeksavas.cryptotracker.domain.model.CryptoCoin

data class CoinDto(
    val id:String,
    val name: String,
    val symbol: String,
    val quotes: Map<String, PriceDetailsDto>
)

data class PriceDetailsDto(
    val price: Double,
    @SerializedName("percent_change_24h")
    val percentChange24h: Double
)

fun CoinDto.toCryptoCoin(): CryptoCoin{
    val quote = this.quotes["USD"]
    
    return CryptoCoin(
        id = this.id,
        name = this.name,
        symbol = this.symbol,
        price = quote?.price ?: 0.0,
        percent_change_24h = quote?.percentChange24h ?: 0.0
    )
}

