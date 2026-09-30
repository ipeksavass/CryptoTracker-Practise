package com.ipeksavas.cryptotracker.presentation.coin_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ipeksavas.cryptotracker.domain.repository.CryptoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import com.ipeksavas.cryptotracker.domain.util.Result

class CoinListViewModel(
    private val repository: CryptoRepository
): ViewModel(){

    //stateflow değil de state of olsa by kullanır .value yazmamıza gerek kalmazdı.
    private val _state = MutableStateFlow<CryptoUiState>(CryptoUiState.Idle)
    val state = _state.asStateFlow()
    
    init{
        fetchCoins()
    }
    private fun fetchCoins(){
        _state.value = CryptoUiState.Loading//fonksiyon çağrıldığında loading durumunu veriyoruz.
        repository.getCoins().onEach{ result ->
            when(result){//cevap geldiğinde
                is Result.Success ->{//başarılı ise veriyi alıyoruz ve success durumunu veriyoruz.
                    _state.value = CryptoUiState.Success(result.data)
                }
                is Result.Error ->{//hata ise hata mesajını veriyoruz.
                    _state.value = CryptoUiState.Error(result.error)
                }
            }
        }.launchIn(viewModelScope)
    }
}