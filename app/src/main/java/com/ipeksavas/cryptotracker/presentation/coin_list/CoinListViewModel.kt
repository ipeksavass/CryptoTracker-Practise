package com.ipeksavas.cryptotracker.presentation.coin_list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ipeksavas.cryptotracker.domain.repository.CryptoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import com.ipeksavas.cryptotracker.domain.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject

@HiltViewModel
class CoinListViewModel(
    @Inject private val repository: CryptoRepository//Dependency Injection yaptık.
): ViewModel(){

    //stateflow değil de state of olsa by kullanır .value yazmamıza gerek kalmazdı.
    private val _state = MutableStateFlow<CryptoUiState>(CryptoUiState.Idle)
    val state = _state.asStateFlow()
    
    init{
        fetchCoins()
    }
    
    private fun fetchCoins(){
        Log.d("CryptoTrackerLog", "1. durum Loading'e geçiliyor.")
        _state.value = CryptoUiState.Loading//fonksiyon çağrıldığında loading durumunu veriyoruz.
        Log.d("CryptoTrackerLog", "2. durum Loading'e geçildi.")
        repository.getCoins().onEach{ result ->
            
            when(result){//cevap geldiğinde
                is Result.Success ->{//başarılı ise veriyi alıyoruz ve success durumunu veriyoruz.
                    Log.d("CryptoTrackerLog", "3. durum Success'e geçiliyor. Başarılı veri geldi ${result.data.size}")
                    _state.value = CryptoUiState.Success(result.data)
                    Log.d("CryptoTrackerLog"," 4. durum Success'e geçildi.")
                }
                is Result.Error ->{//hata ise hata mesajını veriyoruz.
                    Log.d("CryptoTrackerLog","5. durum Error'a geçildi. Hata mesajı: ${result.error}")
                    _state.value = CryptoUiState.Error(result.error)
                    Log.d("CryptoTrackerLog","6. durum Error'a geçildi.")
                }
            }
        }.launchIn(viewModelScope)
    }
}