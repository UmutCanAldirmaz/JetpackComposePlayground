# Blur-O-Matic - WorkManager ile Arka Plan İşleri Çalışma Notları

## 📌 Giriş ve Genel Bakış
Android uygulamalarında kullanıcı arayüzünü (UI thread / Main thread) bloklamadan, arka planda güvenilir bir şekilde yürütülmesi gereken işler için **WorkManager** kullanılır. Bu modülde Google Codelab Unit 7 "Background Work with WorkManager" kapsamında geliştirilen Blur-O-Matic uygulamasının başlangıç yapısı ve mimarisi incelenmiştir.

---

## 🛠️ Temel Kavramlar ve Bileşenler

### 1. WorkManager
- Uygulama kapalı veya cihaz yeniden başlatılmış olsa bile ertelenebilir (deferrable) ve garantili (guaranteed) arka plan işlerini çalıştırmak için önerilen Jetpack kütüphanesidir.
- Arka planda görsel işleme (blur filtresi uygulama), dosya indirme/yükleme, veri senkronizasyonu gibi senaryolarda tercih edilir.

### 2. Worker / CoroutineWorker
- Arka planda gerçekleştirilecek somut iş mantığını barındıran sınıflardır.
- `doWork()` metodunda `Result.success()`, `Result.failure()` veya `Result.retry()` döndürerek işin durumunu bildirir.

### 3. WorkRequest (OneTimeWorkRequest / PeriodicWorkRequest)
- Worker'ın ne zaman ve nasıl çalıştırılacağını tanımlayan istek nesnesidir.
- **Data (Input Data):** `workDataOf()` veya `Data.Builder` ile iş parçasına parametre (örneğin görsel URI, blur seviyesi) geçirmeyi sağlar.
- **Constraints (Kısıtlamalar):** İşin sadece şarjdayken, Wi-Fi bağlıyken veya cihaz boşta (idle) iken çalışmasını sağlar.

### 4. WorkInfo & WorkState
- Arka planda çalışan işin durumunu (`ENQUEUED`, `RUNNING`, `SUCCEEDED`, `FAILED`, `CANCELLED`) izlemek ve UI tarafına güncelleme geçmek için kullanılan bilgi yapısıdır.

---

## 🏗️ Proje Mimarisi

```
com.example.jetpackcomposeplayground.samples.bluromatic
├── Constants.kt                          # Bildirim kanalı, Intent ve WorkManager sabitleri
├── data/
│   ├── BlurAmount.kt                     # Blur seviyesi modeli
│   ├── BlurAmountData.kt                 # Seçilebilir blur seviyeleri listesi
│   ├── BluromaticRepository.kt           # Repository arayüzü (WorkInfo Flow, applyBlur, cancelWork)
│   ├── WorkManagerBluromaticRepository.kt# Repository uygulaması (WorkManager ile haberleşme)
│   └── AppContainer.kt                   # Manuel DI Container
├── workers/
│   └── WorkerUtils.kt                    # Bildirim oluşturma ve görsel blurlaştırma yardımcı metotları
└── ui/
    ├── BlurViewModel.kt                  # UI durum yönetimi ve Repository etkileşimi
    └── BluromaticScreen.kt               # Blur-O-Matic ekranı ve radio button bileşenleri
```

---

## 💡 Best Practices ve Önemli Notlar
1. **Manuel DI Entegrasyonu:** Uygulamanın `PlaygroundApplication` sınıfında `bluromaticContainer` tanımlanarak Repository nesnesi ViewModel Factory üzerinden enjekte edilmiştir.
2. **Bildirim Yönetimi (Notifications):** Android 8.0 (API 26) ve üzeri için `NotificationChannel` oluşturulmalı, Android 13+ (API 33) için `POST_NOTIFICATIONS` izni dikkate alınmalıdır.
3. **Threading Safety:** Görsel işleme veya ağır G/Ç işlemleri ana iş parçacığında yapılmamalı, `Worker` veya `CoroutineWorker` içerisinde yürütülmelidir.
