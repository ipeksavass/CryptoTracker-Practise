package com.ipeksavas.cryptotracker.domain.util

/* Başarılı olma ya da hatalı olma durumu sabit değil.
   Coin için düşündüğümüzde bitcoin verisi de gelebilir, ethereum verisi de gelebilir.
   İçine farklı değerler alabilmesi için (val data:D) şeklinde bir tanımlama yapıyoruz.

   Bunları birer "Joker Boşluk" olarak düşünebiliriz. Result sınıfını yazarken içine ne koyacağımızı
   henüz bilmiyoruz. Kotlin'e diyoruz ki: "Ben sana ileride D ve E adında iki farklı tip söyleyeceğim.
   Şimdilik sen bunları boşluk olarak tut."
   
   Gelecekte nasıl kullanacağız? (Birazdan Repository'de yapacağımız şey):
   Biz bu kargo kutusunu birazdan çağırırken jokerleri dolduracağız. Diyeceğiz ki: Result<List<CryptoCoin>, NetworkError>

   Kotlin bunu gördüğünde arka planda senin o joker kodunu şöyle hayal edecek:

   D gördüğü yere List<CryptoCoin> koyacak. E gördüğü yere NetworkError koyacak.

   Böylece Success kutusunun içindeki data değişkeni otomatik olarak bir kripto para listesi taşımaya başlayacak.
   Error kutusunun içindeki error değişkeni de otomatik olarak bizim Kırmızı Kartlarımızı (Timeout, ServerOut vb.) taşımaya başlayacak.
*/

sealed interface Result<D, E> {
    data class Success<D,E>(val data:D): Result<D,E>
    data class Error<D,E>(val error:E): Result<D,E>
}