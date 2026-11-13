# Blur-O-Matic — WorkManager ve Arka Plan İşleri Kapsamlı Çalışma Notları

Bu doküman, Android'de **WorkManager** kütüphanesini kullanarak garantili arka plan işleri zamanlama, sıralı iş zincirleri (work chaining) oluşturma, kısıtlamalar (constraints) tanımlama, verileri iş parçacıkları arasında taşıma ve bu süreçleri **Jetpack Compose** ile reaktif olarak ekrana yansıtma konularını detaylıca açıklayan ders çalışma notudur.

---

## 📌 1. Uygulama Adım Adım Nasıl Çalışır? (Çalışma Akışı)

Bluromatic uygulaması, seçilen bir görsel üzerine bulanıklaştırma (blur) filtresi uygulayıp sonucu cihazın medya galerisine kaydeden adım adım çalışan bir arka plan iş zincirine (Work Chain) sahiptir:

### 🔄 Uygulama Çalışma Akış Tablosu

| Sıra | Aşama / Bileşen | Gerçekleştirilen İşlem |
| :---: | :--- | :--- |
| **1** | **Kullanıcı Etkileşimi** | Kullanıcı blur seviyesini (1, 2 veya 3) seçer ve **"Start"** butonuna basar. |
| **2** | **`BlurViewModel`** | `applyBlur(blurLevel)` çağrılır ve Repository katmanına istek iletilir. |
| **3** | **`WorkManagerRepository`** | `WorkManager.beginUniqueWork(...)` ile **`IMAGE_MANIPULATION_WORK_NAME`** zinciri başlatılır. |
| **4** | **1. Adım: `CleanupWorker`** | Eski geçici `.png` dosyaları uygulamanın dahili dizininden (`filesDir`) silinir. |
| **5** | **2. Adım: `BlurWorker`** | Pil kısıtlaması (`setRequiresBatteryNotLow`) uygunsa seçilen blur seviyesi görsele uygulanır. |
| **6** | **3. Adım: `SaveImageToFileWorker`** | `TAG_OUTPUT` etiketli iş, işlenen görseli cihazın galeri dizinine (MediaStore API) kaydeder. |
| **7** | **WorkManager Çıktısı** | Kalıcı resim URI adresi `outputData` paketi içinde döndürülür. |
| **8** | **`BlurViewModel` Akışı** | `outputWorkInfo` dinlenerek UI durumu `BlurUiState.Complete(outputUri)` olarak güncellenir. |
| **9** | **`BluromaticScreen` (UI)** | Ekran **"See File"** butonunu gösterir; tıklandığında resim varsayılan galeri uygulamasında açılır. |

**İş Zinciri Akış Sıralaması:**
`Kullanıcı Seviye Seçimi` ➔ `BlurViewModel.applyBlur()` ➔ `CleanupWorker` ➔ `BlurWorker` ➔ `SaveImageToFileWorker` ➔ `BlurUiState.Complete` ➔ `Resmi Galeride Görüntüleme`

1. **Giriş ve Seviye Seçimi:**
   - Kullanıcı ekrandaki radyo butonlarından bir bulanıklık seviyesi seçer (Seviye 1, 2 veya 3).
   - "Start" butonuna basıldığında `BlurViewModel.applyBlur(blurLevel)` fonksiyonu tetiklenir.

2. **Repository ve İş Zincirinin Oluşturulması:**
   - `WorkManagerBluromaticRepository`, `WorkManager.beginUniqueWork()` çağrısı ile **`IMAGE_MANIPULATION_WORK_NAME`** isimli benzersiz bir iş zinciri başlatır.
   - `ExistingWorkPolicy.REPLACE` politikası sayesinde, eğer halihazırda devam eden bir iş varsa iptal edilir ve yenisi başlatılır.

3. **İş Zincirinin Adımları:**
   - **Adım 1 - `CleanupWorker`:** Daha önceki işlemlerden kalan temporary `.png` dosyalarını uygulamanın dahili dizininden (`filesDir/blur_filter_outputs`) temizler.
   - **Adım 2 - `BlurWorker`:** 
     - Girdi verisinden (`inputData`) resim URI'sini ve seçilen bulanıklık seviyesini okur.
     - `setRequiresBatteryNotLow(true)` kısıtlamasına tabidir; cihaz pili kritik seviyedeyse pil şarja takılana kadar bu adım bekletilir.
     - Görseli bulanıklaştırıp geçici bir dosyaya yazar ve oluşan geçici dosya URI'sini bir sonraki adıma `workDataOf(KEY_IMAGE_URI to outputUri)` ile iletir.
   - **Adım 3 - `SaveImageToFileWorker`:**
     - `TAG_OUTPUT` etiketi ile etiketlenmiştir.
     - Bir önceki adımdan gelen geçici resmi okur.
     - **Android 10+ (API 29+):** Scoped Storage uyumlu `CreateImageUriUseCase` ve `SaveImageUseCase` yapılarını kullanarak resmi cihazın `Pictures/Blur-O-Matic` MediaStore galerisine kaydeder.
     - **Android 9 ve Öncesi:** `MediaStore.Images.Media.insertImage` yöntemi ile kaydeder.
     - Sonuç olarak kalıcı galeri URI adresini `outputData` içinde döndürür.

4. **UI Durumunun Reaktif Güncellenmesi:**
   - WorkManager'ın yayımladığı `LiveData<List<WorkInfo>>`, Repository katmanında `asFlow()` ile Kotlin Flow'a dönüştürülür.
   - `BlurViewModel` içerisindeki `stateIn` operasyonu ile `StateFlow<BlurUiState>` olarak UI'a sunulur.
   - İş devam ederken UI `BlurUiState.Loading` durumuna geçer; "Cancel Work" butonu ve dairesel ilerleme çubuğu (`CircularProgressIndicator`) gösterilir.
   - İş bittiğinde `BlurUiState.Complete(outputUri)` durumuna geçilir ve "See File" butonu belirir. Kullanıcı tıkladığında örtük bir Intent (`ACTION_VIEW`) ile görsel açılır.

---

## 🛠️ 2. Bu Proje Bize Teknik Anlamda Ne Öğretir?

1. **Garantili Arka Plan İş Yönetimi (WorkManager):**
   - Uygulama kapatılsa veya cihaz yeniden başlatılsa bile tamamlanması garanti edilen işlerin yönetimi.
2. **Sıralı İş Zincirleri (Work Chaining & WorkContinuation):**
   - Bağımlı işleri `beginUniqueWork().then().then().enqueue()` zinciriyle sırayla çalıştırma.
3. **Çalışma Kısıtlamaları (Constraints):**
   - Cihaz pil durumu, ağ bağlantısı veya depolama alanına göre işlerin çalışmasını kısıtlama (`Constraints.Builder`).
4. **İş Parçacıkları Arası Veri Aktarımı (Data Input/Output):**
   - `Data.Builder` ve `inputData` / `outputData` kullanarak zincirdeki Worker'lar arasında güvenli veri paslama.
5. **Flow ve LiveData Birlikte Çalışabilirliği (Interoperability):**
   - `WorkManager`'ın varsayılan `LiveData` çıktısını `asFlow()` ile modern Kotlin Coroutine Flow yapısına dönüştürme.
6. **Ekran Döndürme Güvenlikli StateFlow (`WhileSubscribed(5_000)`):**
   - `SharingStarted.WhileSubscribed(5_000)` ile ekran konfigürasyon değişikliklerinde (rotation) akışın boş yere sıfırlanmasını engelleme.
7. **Scoped Storage ve MediaStore Entegrasyonu:**
   - Android 10+ (API 29) sürümlerinde `ContentValues` ve `ContentResolver` kullanarak Scoped Storage standartlarına uygun medya kaydetme.
8. **Manuel Bağımlılık Enjeksiyonu (Manual Dependency Injection):**
   - `AppContainer` ve `ViewModelProvider.Factory` kullanarak Repository nesnelerini bağımlılık olarak yönetme.

---

## 📘 3. Teknik Kavramlar ve Mimari Bileşenlerin Detaylı Anlatımı

### A. WorkManager Temel Bileşenleri

#### 1. `WorkManager`
Android Jetpack'in ertelenebilir (deferrable) ve garantili (guaranteed) arka plan işleri için sunduğu mimari bileşendir. `WorkManager.getInstance(context)` ile erişilir.

#### 2. `CoroutineWorker`
`Worker` sınıfının Kotlin Coroutines destekli türevidir. `doWork()` fonksiyonu bir `suspend` fonksiyondur ve ağır işleri `Dispatchers.IO` iş parçacığı üzerinde güvenle çalıştırmamızı sağlar.

Fonksiyondan 3 farklı sonuç dönebilir:
- `Result.success(outputData)`: İş başarıyla tamamlandı (isteğe bağlı çıktı verisi ile).
- `Result.failure()`: İş başarısız oldu ve tekrar denenmeyecek.
- `Result.retry()`: İş başarısız oldu ancak kısıtlamalara göre tekrar denenmeli.

#### 3. `WorkRequest` ve `OneTimeWorkRequestBuilder`
Bir Worker'ın ne zaman ve nasıl çalıştırılacağını belirler:
- `OneTimeWorkRequest`: Tek seferlik işler için kullanılır.
- `PeriodicWorkRequest`: Belirli periyotlarla tekrarlanan işler için kullanılır (minimum periyot 15 dakikadır).
- `addTag(tag)`: İşleri gruplamak veya takip etmek için etiket ekler.

#### 4. Veri Boyutu Kısıtlaması (`Data`)
`Data` objesi anahtar-değer (Key-Value) çiftleri tutar. **Önemli Kısıtlama:** `Data` paketinin maksimum boyutu **10 KB**'tır. Bu yüzden resim gibi büyük dosyaların kendisi (Bitmap/ByteArray) değil, dosya URI adresleri (`String`) aktarılır.

```kotlin
val outputData = workDataOf(KEY_IMAGE_URI to outputUri.toString())
```

#### 5. Kısıtlamalar (`Constraints`)
İşin sadece belirli cihaz koşulları sağlandığında çalışmasını sağlar:

```kotlin
val constraints = Constraints.Builder()
    .setRequiresBatteryNotLow(true) // Cihaz pili kritik seviyede değilse çalıştır
    .setRequiresCharging(true)       // Sadece şarja takılıyken çalıştır
    .build()
```

#### 6. Benzersiz İş Politikaları (`ExistingWorkPolicy`)
`beginUniqueWork` ile bir işe benzersiz isim verildiğinde uygulanacak davranışlar:
- `REPLACE`: Devam eden eski iş cancel edilir ve yeni iş başlatılır.
- `KEEP`: Devam eden iş varsa yeni gelen iş göz ardı edilir.
- `APPEND` / `APPEND_OR_REVISE`: Yeni işi mevcut işin arkasına zincir olarak ekler.

---

### B. Projedeki Kod Sınıflarının Analizi

#### 1. `WorkManagerBluromaticRepository.kt`
Arka plan iş zincirini oluşturan ana merkezdir.

```kotlin
override fun applyBlur(blurLevel: Int) {
    // 1. Temizlik İşlemi
    var continuation = workManager.beginUniqueWork(
        IMAGE_MANIPULATION_WORK_NAME,
        ExistingWorkPolicy.REPLACE,
        OneTimeWorkRequest.from(CleanupWorker::class.java)
    )

    // 2. Kısıtlama Tanımı
    val constraints = Constraints.Builder()
        .setRequiresBatteryNotLow(true)
        .build()

    // 3. Blur İş İsteyi
    val blurBuilder = OneTimeWorkRequestBuilder<BlurWorker>()
    blurBuilder.setInputData(createInputDataForWorkRequest(blurLevel, imageUri))
    blurBuilder.setConstraints(constraints)

    // Zincire Ekleme
    continuation = continuation.then(blurBuilder.build())

    // 4. Kaydetme İş İsteyi
    val save = OneTimeWorkRequestBuilder<SaveImageToFileWorker>()
        .addTag(TAG_OUTPUT)
        .build()
    continuation = continuation.then(save)

    // Zinciri Başlatma
    continuation.enqueue()
}
```

- **`getWorkInfosByTagLiveData(TAG_OUTPUT).asFlow()`:** İş zincirinin en sonundaki `SaveImageToFileWorker`'a `TAG_OUTPUT` vererek sadece bu işin durumunu dinleriz. `asFlow()` dönüşümü ile LiveData'yı Coroutine Flow yapısına aktarırız.

#### 2. `BlurViewModel.kt`
Repository'den gelen `WorkInfo` akışını UI durumuna (`BlurUiState`) dönüştürür.

```kotlin
val blurUiState: StateFlow<BlurUiState> = bluromaticRepository.outputWorkInfo
    .map { info ->
        val outputImageUri = info.outputData.getString(KEY_IMAGE_URI)
        when {
            info.state.isFinished && !outputImageUri.isNullOrEmpty() -> {
                BlurUiState.Complete(outputUri = outputImageUri)
            }
            info.state == WorkInfo.State.CANCELLED -> {
                BlurUiState.Default
            }
            else -> BlurUiState.Loading
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = BlurUiState.Default
    )
```

- **`SharingStarted.WhileSubscribed(5_000)`:** UI ekranı (Compose) görünmez olduğunda dinleme durur. Ancak ekran döndürüldüğünde (configuration change) akışın hemen kapanmasını önlemek için 5 saniyelik bir stop timeout süresi verilir. Bu süre dolmadan ekran tekrar oryantasyonunu tamamlarsa aynı akış kesintisiz devam eder.

#### 3. UseCase Yapısı (`CreateImageUriUseCase.kt` & `SaveImageUseCase.kt`)
Android 10 (API level 29) ve sonrasında gelen **Scoped Storage** gereksinimlerine göre galeriye dosya yazma işlemini modülerleştirir:
- `CreateImageUriUseCase`: `MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY)` üzerinden yeni bir kayıt yeri açar (`IS_PENDING = 1`).
- `SaveImageUseCase`: `ContentResolver.openOutputStream` ile resmi `JPEG` formatında akışa yazar ve `IS_PENDING = 0` yaparak galeriye açar.

#### 4. `BluromaticScreen.kt`
Compose arayüzüdür. `blurUiState` durumuna göre ekran elemanlarını dinamik çizer:
- **`BlurActions`:**
  - `BlurUiState.Default`: "Start" butonu aktif.
  - `BlurUiState.Loading`: "Cancel Work" butonu ve `CircularProgressIndicator` aktif.
  - `BlurUiState.Complete`: "Start" ve "See File" butonları aktif.

---

## 📝 Özet ve CheckList

| Konu | Projedeki Karşılığı |
| :--- | :--- |
| **Worker Sınıfı** | `BlurWorker`, `CleanupWorker`, `SaveImageToFileWorker` |
| **İş Zinciri (Chaining)** | `workManager.beginUniqueWork(...).then(...).then(...)` |
| **Kısıtlamalar** | `Constraints.Builder().setRequiresBatteryNotLow(true)` |
| **Veri Taşıma** | `workDataOf(KEY_IMAGE_URI to outputUri)` |
| **İş İptali** | `workManager.cancelUniqueWork(...)` |
| **Reaktif Durum Takibi** | `getWorkInfosByTagLiveData().asFlow().map { ... }.stateIn(...)` |
| **MediaStore (Scoped Storage)** | `CreateImageUriUseCase` & `SaveImageUseCase` (API 29+) |

Bu çalışma notları sayesinde Blur-O-Matic projesinin tüm mimarisi, arka plan iş süreçleri ve modern Android Kotlin Compose entegrasyonu eksiksiz bir şekilde anlaşılabilir.
