package com.ipeksavas.cryptotracker.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.ipeksavas.cryptotracker.domain.model.CryptoCoin

data class CoinDto(
    val id:String,
    val name: String,
    val symbol: String,
    val rank: Int,
    @SerializedName("is_new")
    val isNew: Boolean,
    @SerializedName("is_active")
    val isActive: Boolean,
    val type:String
)

fun CoinDto.toCryptoCoin(): CryptoCoin{
    return CryptoCoin(
        id = this.id,
        name = this.name,
        symbol = this.symbol,
        rank = this.rank,
        isNew = this.isNew,
        isActive = this.isActive,
        type = this.type
    )
}