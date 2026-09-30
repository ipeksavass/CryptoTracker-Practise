package com.ipeksavas.cryptotracker.domain.util

//object -> durumun kendisi zaten veri. Bir tane üretilir, o kullanılır.
sealed interface NetworkError {
    data object Timeout: NetworkError
    data object ServerOut: NetworkError
    data object Unknown: NetworkError
}