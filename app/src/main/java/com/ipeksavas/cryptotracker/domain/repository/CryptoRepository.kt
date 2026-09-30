package com.ipeksavas.cryptotracker.domain.repository

import com.ipeksavas.cryptotracker.domain.model.CryptoCoin
import com.ipeksavas.cryptotracker.domain.util.NetworkError
import com.ipeksavas.cryptotracker.domain.util.Result

import kotlinx.coroutines.flow.Flow

interface CryptoRepository {
    fun getCoins(): Flow<Result<List<CryptoCoin>, NetworkError>>
    /*
        Amaç bu fonksiyon çağırıldığında, bir Flow nesnesi döndürmek. Bu Flow nesnesi, zaman içinde birden fazla değer yayabilir.
        Biz bu fonksiyonu bir kez çağırırız ve Flow'un ucuna bir kova koymuş oluruz "collect()". Kovaya bir şeyler geldikçe yakalarız.
        Örneğin, ilk olarak bir yükleme durumu (loading) yayabilir, ardından başarılı bir veri (success) veya hata (error) durumu yayabilir.
     */
}