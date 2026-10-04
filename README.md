# 🪙 Crypto Tracker Android App

**Kotlin** ve **Jetpack Compose** kullanılarak geliştirilen, internet üzerinden CoinPaprika API'sine **Retrofit** ile bağlanarak anlık kripto para verilerini çeken modern ve temiz mimarili bir **Android** uygulamasıdır. 
Proje; **Clean Architecture, Dependency Injection (Hilt), Coroutines, Flow ve Sealed Interface** tabanlı reaktif durum yönetimini benimser.

---

## 🛠️ Tech Stack & Architecture

- **Language:** [Kotlin](https://kotlinlang.org/)
- **UI Toolkit:** [Jetpack Compose](https://developer.android.com/jetpack/compose) & Material 3
- **Dependency Injection:** [Hilt](https://dagger.dev/hilt/)
- **Networking:** **Retrofit & Gson Converter**
- **State Management:** **Kotlin Coroutines, Flow & Sealed Interface** (`Loading`, `Success`, `Error`)
- **Concurrency:** **Kotlin Coroutines** (`suspend` functions)
- **Architecture:** **Clean Architecture** (Data, Domain, Presentation, DI)

---

## 🔄 Development Journey & Key Features

Proje geliştirme sürecinde izlenen aşamalar ve teknik detaylar:

1. **Sealed Interface ile Tip Güvenli Durum Yönetimi (State Management):** 
   - Arayüzün yüklenme (`Loading`), başarılı veri alımı (`Success`) ve hata (`Error`) durumları `sealed interface` kullanılarak modellendi. 
   - Bu sayede ViewModel ile UI arasındaki veri akışı tamamen tip güvenli (type-safe) ve öngörülebilir hale getirildi.
2. **Retrofit ile Ağ İletişimi (Networking):** 
   - İnternet dünyasından güvenli ve hızlı bir şekilde veri çekebilmek için **Retrofit** kütüphanesi entegre edildi. 
   - `CoinApi` arayüzü üzerinden CoinPaprika sunucularına istekler atıldı ve gelen karmaşık JSON verileri **Gson** ile parse edildi.
3. **İnternet İzinleri ve Güvenlik:** 
   - Uygulamanın dış dünya ile iletişim kurabilmesi için `AndroidManifest.xml` dosyasına gerekli **`INTERNET` izni** eklendi.
4. **Endpoint Optimizasyonu & Veri Sadeleştirme:** 
   - Endpoint güncellenerek (`v1/tickers`) veri akışı optimize edildi ve ekran sadece kritik finansal metriklere (**fiyat** ve **24 saatlik yüzde değişim**) odaklanacak şekilde sadeleştirildi.
5. **Dependency Injection (Hilt):** 
   - Tüm ağ servisleri, repository'ler ve use case'ler Google **Hilt** altyapısıyla modüler ve merkezi (`AppModule`, `RepositoryModule`) bir şekilde yönetildi.

---

## 📂 Project Package Structure

Projenin katmanları ve paket dağılımı şu şekildedir:

```text
com.ipeksavas.cryptotracker/
│
├── data/
│   ├── remote/
│   │   └── dto/
│   │       ├── CoinApi.kt              # Retrofit API interface
│   │       └── CoinDto.kt              # Network DTOs & Mappers
│   │
│   └── repository/
│       ├── FakeCryptoRepositoryImpl.kt # Mock/Fake repository for testing
│       └── RealCryptoRepositoryImpl.kt # Production repository implementation
│
├── di/
│   ├── AppModule.kt                    # Network & Hilt dependency modules
│   └── RepositoryModule.kt             # Repository binding modules
│
├── domain/
│   ├── model/
│       └── CryptoCoin.kt               # Clean domain data model
│   ├── repository/
│       └── CryptoRepository.kt         # Repository interface
│   └── util/
│       ├── NetworkError.kt             # Error types
│       └── Result.kt                   # Generic success/failure wrapper
│
├── presentation/
│   └── coin_list/
│       ├── CoinListScreen.kt           # Main UI Screen (Handles Loading/Success/Error states)
│       ├── CoinListItem.kt             # Expandable Compose card component
│       ├── CoinListViewModel.kt        # ViewModel handling UI state & logic
│       └── CryptoUiState.kt            # UI state data class (sealed interface structure)
│
├── ui/theme/                           # Material 3 Theme, Color & Typography
│
├── CryptoTrackerApp.kt                 # Application class (@HiltAndroidApp)
└── MainActivity.kt                     # Entry point activity (@AndroidEntryPoint)
```
