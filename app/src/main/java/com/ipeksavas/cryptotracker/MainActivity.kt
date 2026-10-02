package com.ipeksavas.cryptotracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.ipeksavas.cryptotracker.data.repository.FakeCryptoRepositoryImpl
import com.ipeksavas.cryptotracker.presentation.coin_list.CoinListViewModel
import com.ipeksavas.cryptotracker.ui.theme.CryptoTrackerTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ipeksavas.cryptotracker.presentation.coin_list.CoinListScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint//hilt'in müdahalesine izin veriyoruz.
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        setContent {
            CryptoTrackerTheme {
                val repository = FakeCryptoRepositoryImpl()//Çalışanı yarattım.
                val viewModel = viewModel<CoinListViewModel>{//Yönetmeni yaratıp işçiyi ona verdim.
                    CoinListViewModel(repository)
                }
                /*
                Neden doğrudan eşitlemek yerine viewModel { } adında özel bir blok kullandım? Çünkü bu blok Android'e şunu söyler:
                "Kullanıcı telefonu yan çevirdiğinde ekranı baştan çizsen bile, bu ViewModel'i öldürme ve içindeki verileri koru."
                Bunu yapmasaydım telefon her yan döndüğünde uygulama en başa dönüp 2 saniye yüklenme çemberi gösterirdi.
                 */
                CoinListScreen(viewModel = viewModel)
            }
        }
    }
}