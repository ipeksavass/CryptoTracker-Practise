package com.ipeksavas.cryptotracker.data.repository

import com.ipeksavas.cryptotracker.data.remote.dto.CoinApi
import com.ipeksavas.cryptotracker.data.remote.dto.toCryptoCoin
import com.ipeksavas.cryptotracker.domain.model.CryptoCoin
import com.ipeksavas.cryptotracker.domain.repository.CryptoRepository
import com.ipeksavas.cryptotracker.domain.util.NetworkError
import com.ipeksavas.cryptotracker.domain.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class RealCryptoRepositoryImpl(
    private val api: CoinApi
): CryptoRepository {
    override fun getCoins(): Flow<Result<List<CryptoCoin>, NetworkError>> {
        return flow{
            try{
                val coinDtoList = api.getCryptoCoinInfo()//burada getfonk çalıştırıyoruz ve veriyi alıyoruz.
                val cryptoCoinList = coinDtoList.map { it.toCryptoCoin() }//burada veriyi cryptocoin türümüzdeki nesnelere dönüştürdük. Bir liste haline verdik.
                /*  .map fonksiyonu, elindeki bir listenin her bir elemanını tek tek ele alıp,
                    onları başka bir şeye dönüştürmek için kullanılır. map fonksiyonu bir liste döner  */
                emit(Result.Success(cryptoCoinList))
                
            }catch(e: IOException){
                emit(Result.Error(NetworkError.Timeout))
            }catch(e: HttpException){
                emit(Result.Error(NetworkError.ServerOut))
            }catch(e: Exception){
                emit(Result.Error(NetworkError.Unknown))
            }
        }
    }
}