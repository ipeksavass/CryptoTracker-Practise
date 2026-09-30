package com.ipeksavas.cryptotracker.presentation.coin_list

import com.ipeksavas.cryptotracker.domain.model.CryptoCoin
import com.ipeksavas.cryptotracker.domain.util.NetworkError
import com.ipeksavas.cryptotracker.domain.util.Result.Success

//UI kısmında mesaj paketini değil direkt veriyi alıyoruz o yüzden başarılı durum için veri listesini,
// hatalı durum için ise hata mesajını veriyoruz.
sealed interface CryptoUiState {
    data object Idle: CryptoUiState
    data object Loading: CryptoUiState
    data class Success(val success: List<CryptoCoin>): CryptoUiState
    data class Error(val error: NetworkError): CryptoUiState
}