package com.ipeksavas.cryptotracker.data.repository

import com.ipeksavas.cryptotracker.domain.model.CryptoCoin
import com.ipeksavas.cryptotracker.domain.repository.CryptoRepository
import com.ipeksavas.cryptotracker.domain.util.NetworkError
import com.ipeksavas.cryptotracker.domain.util.Result
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

// KURALLARI UYGULAN KİŞİ - ÇALIŞAN
class FakeCryptoRepositoryImpl: CryptoRepository{
    override fun getCoins(): Flow<Result<List<CryptoCoin>, NetworkError>> {
        return flow {
            while (true) {
                delay(2000L)
                
                val hasError = kotlin.random.Random.nextBoolean()//yazı-tura attırma işlemidir.
                //hata var ya da yok
                if(hasError){
                    //hatanın türü ne olsun rastgele seçiyor
                    val randomError = listOf(
                        NetworkError.Timeout,
                        NetworkError.ServerOut,
                        NetworkError.Unknown
                    ).random()
                    emit(Result.Error(randomError))//hatayı fırlatıyorum.
                }else{//başarılıysa verileri yolluyorum.
                    val fakeList = listOf(
                        CryptoCoin("1","elmas","mavi",5,true,true,"xxx")
                    )
                    emit(Result.Success(fakeList))
                }
            }
        }
    }
}