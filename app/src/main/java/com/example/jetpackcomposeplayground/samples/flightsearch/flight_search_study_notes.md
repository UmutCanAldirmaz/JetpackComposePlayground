# Unit 6: Flight Search App - Room, DataStore & DI Notları

## 📌 Özet ve Teknik Konseptler

Flight Search uygulaması; Room SQLite veritabanı (önceden doldurulmuş asset DB), Preferences DataStore ve Manuel Bağımlılık Enjeksiyonu (AppContainer) kalıplarını bir araya getiren kapsamlı bir Unit 6 projesidir.

### 1. Önceden Doldurulmuş Room Veritabanı (Pre-populated Database)
- **Asset DB:** `Room.databaseBuilder(...).createFromAsset("database/flight_search.db")` kullanılarak hazırdan `assets/database/` klasöründe yer alan havalimanı verileri yüklenmiştir.
- **Airport Entity (`airport`):** `id`, `name`, `iata_code`, `passengers` sütunlarını barındırır.
- **Favorite Entity (`favorite`):** Kullanıcının kaydettiği rotaları tutar (`id`, `departure_code`, `destination_code`).

### 2. Preferences DataStore
- Kullanıcının arama çubuğuna yazdığı son arama metni veya tıkladığı havalimanı IATA kodu `flight_search_preferences` DataStore dosyasında `search_text` anahtarıyla asenkron olarak saklanır.
- Uygulama yeniden başlatıldığında son arama durumu otomatik olarak geri yüklenir.

### 3. Manuel DI ve Container Kalıbı (Clean Architecture)
- **`FlightSearchAppContainer` (Arayüz):** Repository bağımlılıklarını soyutlar.
- **`DefaultFlightSearchAppContainer`:** Database ve DataStore nesnelerini `by lazy` ile tembel ilklendirir.
- **`PlaygroundApplication` Entegrasyonu:** `Application` sınıfı veritabanı nesnelerini doğrudan tutmak yerine sadece `flightSearchContainer` nesnesini ilklendirir (Single Responsibility Principle).

### 4. Sistem Geri Tuşu Yönetimi (Predictive Back / BackHandler)
- `androidx.activity.compose.BackHandler` entegrasyonu ile:
  - Ekran üzerinde seçili havalimanı veya arama metni varsa: Sistem geri tuşu aramayı sıfırlar ve **Favori Rotalar** ekranına döner.
  - Arama boşken sistem geri tuşuna basılırsa: Katalog ana ekranına (`HomeScreen`) döner.

---

## 🐞 Karşılaşılan Hatalar ve Teknik Çözümleri

### 1. `collectLatest` Coroutine İptal Döngüsü (Cancellation Loop)
- **Sorun:** `FlightSearchViewModel` ilklendirilirken DataStore akışı `collectLatest` ile dinleniyordu. İlk açılışta okunan veri `selectAirport` fonksiyonunu çağırıyor, o da DataStore'a tekrar yazma yapıyordu. DataStore yeni değer yayınladığında `collectLatest` önceki bloğu anında iptal ediyordu (`cancel`). Bu durum Room veritabanından uçuş verilerini çeken Coroutine'i yarıda kestiği için uygulama açılışında ekran boş kalıyordu.
- **Çözüm:** `init` bloğunda `collectLatest` yerine `.first()` kullanılarak DataStore'dan ilk değer tek seferlik okundu. İlk yükleme esnasında `saveToDataStore = false` parametresi geçilerek kısır döngü tamamen kırıldı.

### 2. Room Şema Uyumsuzluğu ve Yıkıcı Göç (Schema Mismatch & Destructive Migration)
- **Sorun:** Orijinal SQLite asset veritabanında `passengers` sütununun SQL tipi `INT` olarak tanımlanmıştı. Room Kotlin `@Entity` tarafında `passengers: Int` tanımı `INTEGER` tipinde şema beklentisi oluşturuyordu. Room her açılışta şema uyumsuzluğu tespit ediyor ve `fallbackToDestructiveMigration(dropAllTables = true)` seçili olduğu için disktedeki veritabanını silip (`DROP TABLE`) sıfır asset dosyasını kopyalıyordu. Bu da kullanıcının kaydettiği tüm favorilerin silinmesine neden oluyordu.
- **Çözüm:** Asset veritabanındaki `airport` tablosunun `passengers` sütun tipi `INTEGER` yapıldı. `FlightSearchDatabase` üzerinden `fallbackToDestructiveMigration` kaldırılarak verilerin diske %100 kalıcı olarak yazılması sağlandı.

---

## 🧪 Enstrümante Test Stratejisi (`androidTest`)

- **`FlightSearchDaoPersistenceTest.kt`**: Favorilere eklenen rotanın veritabanı kapatılıp (`db.close()`) diskten tekrar açıldığında korunduğunu doğrular (**PASSED** ✅).
- **`FlightSearchPersistenceUiTest.kt`**: Arama yapma, havalimanı seçme, uçuşu favorileme, aramayı temizleme ve ekranda favoriyi kontrol etme adımlarını içeren uçtan uca UI testi (**PASSED** ✅).
