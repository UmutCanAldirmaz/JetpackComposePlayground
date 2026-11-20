# Amphibians Uygulaması - Çalışma ve Öğrenme Notları

## Proje Amacı
Amphibians uygulaması, Android Basics with Compose kursunun 5. ünitesindeki öğrendikleri pekiştirmek için geliştirilen bir mini projedir. İki yaşamlı canlılar (kurbağalar, semenderler vb.) hakkında veri ve görselleri REST API üzerinden çekip listeler.

## Temel Öğrenilenler ve Teknikler
1. **Ağ Servisi Yapılandırması:**
   - `AmphibiansApiService` üzerinden `@GET("amphibians")` isteği yapılması.
   - Retrofit + `kotlinx.serialization` entegrasyonu ile JSON cevabının `Amphibian` liste modeline dönüştürülmesi.
2. **Repository Yapısı:**
   - `AmphibiansRepository` arayüzü ve `DefaultAmphibiansRepository` sınıfı ile veri erişiminin ViewModel'den ayrılması.
3. **UI State ve Asenkron Durum Yönetimi:**
   - `AmphibiansUiState` (`Loading`, `Success`, `Error`) yapısıyla uygulamanın anlık durumunun Jetpack Compose ekranına yansıtılması.
4. **Material 3 Kart Listesi Tasarımı:**
   - `LazyColumn`, `Card`, `Text` ve `AsyncImage` bileşenleriyle canlı adı, türü ve açıklamasını içeren kart tasarımları.

## Manuel DI ve Playground Entegrasyonu
- Projedeki çakışmaları önlemek için tekil `AmphibiansApplication` sınıfı kaldırılmıştır.
- Tüm bağımlılıklar projenin ana [PlaygroundApplication](file:///C:/Users/Umut/AndroidStudioProjects/JetpackComposePlayground/app/src/main/java/com/example/jetpackcomposeplayground/PlaygroundApplication.kt) sınıfı içindeki `amphibiansContainer` üzerinden yönetilmektedir.
- `AmphibiansViewModel.Factory` içerisinde `PlaygroundApplication` cast edilerek repository enjeksiyonu gerçekleştirilir.

## Birim Testleri (Unit Tests)
- `FakeDataSource`: Test ortamı için sahte `Amphibian` liste nesneleri sağlar.
- `FakeAmphibiansApiService`: Network katmanını taklit eden sahte Retrofit servisi.
- `FakeAmphibiansRepository`: Repository katmanını taklit eden sahte repository.
- `TestDispatcherRule`: Coroutine testlerinde `Dispatchers.Main` yönetimini sağlar.
- `AmphibiansViewModelTest`: ViewModel'in `getAmphibians()` çağrısında `AmphibiansUiState.Success` durumuna doğru verilerle geçtiğini doğrular.
- `NetworkAmphibiansRepositoryTest`: `DefaultAmphibiansRepository` sınıfının servisten gelen verileri doğru şekilde döndürdüğünü doğrular.
