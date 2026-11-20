# Mars Photos Uygulaması - Çalışma ve Öğrenme Notları

## Proje Amacı
Mars Photos uygulaması, Android Basics with Compose kursunun 5. ünitesinde **Retrofit**, **kotlinx.serialization** ve **Coil** kütüphanelerini kullanarak internet üzerinden veri çekme (Web Services) ve görselleri asenkron işleme konularını öğrenmek amacıyla geliştirilmiştir.

## Temel Öğrenilenler ve Teknikler
1. **Retrofit ve REST API Entegrasyonu:**
   - `MarsApiService` interface'i ile `@GET("photos")` HTTP isteklerinin tanımlanması.
   - `Retrofit.Builder` ile `baseUrl` ve dökümleyici (`ConverterFactory`) yapılandırması.
2. **kotlinx.serialization ile JSON Parsing:**
   - JSON verilerini Kotlin veri nesnelerine (`MarsPhoto`) dönüştürme.
   - `@Serializable` ve `@SerialName(value = "img_src")` annotation'ları ile JSON alan adlarının Kotlin değişken isimleriyle eşleştirilmesi.
3. **Coil ile Asenkron Görsel Yükleme:**
   - Ekranda görsellerin URL üzerinden yüklenmesi, yüklenme aşamasında placeholder ve hata durumlarında error ikonlarının gösterilmesi.
4. **UI State Yönetimi (Sealed Interface):**
   - `MarsUiState` interface'i (`Loading`, `Success`, `Error`) ile ekranın tüm durumlarının temsil edilmesi.
   - Hata durumunda kullanıcıya "Yeniden Dene" (Retry) aksiyonunun sunulması.
5. **Repository Deseni ve Manuel Dependency Injection (DI):**
   - `MarsPhotosRepository` interface'i ve `NetworkMarsPhotosRepository` uygulaması ile veri kaynağının ViewModel'den soyutlanması.
   - `AppContainer` interface'i ve `DefaultAppContainer` ile bağımlılıkların oluşturulması.

## Manuel DI ve Playground Entegrasyonu
- Proje tekil bir uygulama yerine çoklu örneklerin toplandığı **JetpackComposePlayground** çatısında yer aldığı için tekil `MarsPhotosApplication` kaldırılmıştır.
- Tüm bağımlılıklar merkezi [PlaygroundApplication](file:///C:/Users/Umut/AndroidStudioProjects/JetpackComposePlayground/app/src/main/java/com/example/jetpackcomposeplayground/PlaygroundApplication.kt) içerisindeki `marsPhotosContainer` üzerinden sağlanır.
- `MarsViewModel.Factory` içerisinde `this[APPLICATION_KEY] as PlaygroundApplication` cast edilerek ilgili `MarsPhotosRepository` enjekte edilir.
