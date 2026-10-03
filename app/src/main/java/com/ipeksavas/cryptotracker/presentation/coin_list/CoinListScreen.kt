package com.ipeksavas.cryptotracker.presentation.coin_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun CoinListScreen( viewModel: CoinListViewModel){
//    val uiState by viewModel.state.collectAsState()
//    bu değişiklik ile gereksiz işlemci, bellek ve batarya tüketiminin önüne geçilir.
    val uiState by viewModel.state.collectAsStateWithLifecycle()
    when(val currentState = uiState){
        //data object olanları is ile kontrol etmedik çünkü onlardan zaten sadece bir tane var başka bir kopyası yok
        //yani data object durumunda "Equality Checking" yapıyoruz.
        CryptoUiState.Idle -> {}
        CryptoUiState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                CircularProgressIndicator()
            }
        }
        //data class oldukları için birden çok kopya var "Type Checking" yapmamız lazım.
        is CryptoUiState.Success -> {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(currentState.success) { coin ->
                    CoinListItem(coin = coin)
                }
            }
        }
        is CryptoUiState.Error -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                Text(
                    text = currentState.error.toString(),
                    color = Color.Red
                )
            }
        }
    }
}
