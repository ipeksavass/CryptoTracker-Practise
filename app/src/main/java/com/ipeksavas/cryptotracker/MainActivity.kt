package com.ipeksavas.cryptotracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.ipeksavas.cryptotracker.presentation.coin_list.CoinListViewModel
import com.ipeksavas.cryptotracker.ui.theme.CryptoTrackerTheme
import com.ipeksavas.cryptotracker.presentation.coin_list.CoinListScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint // Hilt'in kapıyı çalabilmesi için gereken anahtarımız
class MainActivity : ComponentActivity() {
    private val viewModel: CoinListViewModel by viewModels()//hilt sayesinde (otomatik)
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        setContent {
            CryptoTrackerTheme {
                CoinListScreen(viewModel = viewModel)
            }
        }
    }
}