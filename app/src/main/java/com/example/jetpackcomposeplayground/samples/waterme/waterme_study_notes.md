# Unit 7: Water Me! App - WorkManager ve Bildirim (Notification) Çalışma Notları

Bu doküman, Android'de **WorkManager** kütüphanesini kullanarak gecikmeli (initial delay) arka plan zamanlamaları oluşturmayı, verileri `Data` nesneleri ile Worker'a taşımayı, `NotificationManager` ile sistem bildirimleri yayınlamayı ve bu yapıyı **Jetpack Compose** arayüzü ile entegre etmeyi açıklayan teknik çalışma notudur.

---

## 📌 1. Uygulama Adım Adım Nasıl Çalışır? (Çalışma Akışı)

Water Me! uygulaması, kullanıcıların bitki sulama hatırlatıcıları kurmasını ve belirlenen süre sonunda WorkManager aracılığıyla sistem bildirimi almasını sağlar:

### 🔄 Uygulama Çalışma Akış Tablosu

| Sıra | Aşama / Bileşen | Gerçekleştirilen İşlem |
| :---: | :--- | :--- |
| **1** | **Bitki Seçimi** | Kullanıcı listeden sulama hatırlatıcısı kurmak istediği bir bitkiye tıklar. |
| **2** | **Hatırlatıcı Diyaloğu** | `ReminderDialogContent` açılır; kullanıcı süre seçeneğini (5 saniye, 1 dakika, 2 dakika, 3 dakika) seçer. |
| **3** | **`WaterViewModel`** | `scheduleReminder(reminder)` çağrılarak repository katmanına istek gönderilir. |
| **4** | **`WorkManagerWaterRepository`** | `OneTimeWorkRequestBuilder<WaterReminderWorker>()` ile gecikmeli iş talebi (`setInitialDelay`) oluşturulur. |
| **5** | **Benzersiz İş Kuyruğu** | `workManager.enqueueUniqueWork(...)` ile iş `ExistingWorkPolicy.REPLACE` politikasıyla kuyruğa eklenir. |
| **6** | **`WaterReminderWorker`** | Süre dolduğunda arka planda `doWork()` tetiklenir ve `inputData` içinden bitki adı okunur. |
| **7** | **Sistem Bildirimi** | `makePlantReminderNotification()` çağrılarak cihaza yüksek öncelikli sulama bildirimi gönderilir. |
| **8** | **Bildirime Tıklama** | Kullanıcı bildirime tıkladığında `PendingIntent` ile `MainActivity` güvenli şekilde yeniden açılır. |

---

## 🛠️ 2. Mimari ve Temel Konseptler

### 1. WorkManager ile Gecikmeli Zamanlama (`setInitialDelay`)
- **`OneTimeWorkRequestBuilder`**: Tek seferlik arka plan görevleri oluşturur.
- **`setInitialDelay(duration, unit)`**: İşin hemen değil, belirtilen süre kadar sonra (ör. 5 saniye veya 1 dakika) çalışmasını sağlar.
- **`setInputData(data)`**: Worker'a veri taşımak için `Data.Builder().putString(nameKey, plantName).build()` kullanılır.
- **`ExistingWorkPolicy.REPLACE`**: Aynı bitki ve süre için yeni hatırlatıcı kurulduğunda çakışan eski iş iptal edilip yenisiyle değiştirilir.

### 2. `WaterReminderWorker` (`CoroutineWorker`)
- `doWork()` metodu içerisinde asenkron arka plan görevi yürütülür.
- `inputData.getString(nameKey)` ile zamanlama kurulurken iletilen bitki ismi alınır.
- Görev başarılı tamamlandığında `Result.success()` döndürülür.

### 3. Bildirim Kanalı (NotificationChannel) ve Bildirim Oluşturma
- **Android 8.0+ (API 26+) Uyumluğu**: `NotificationChannel` oluşturularak kanal önceliği `IMPORTANCE_HIGH` olarak ayarlanır.
- **`NotificationCompat.Builder`**: Bildirim simgesi, başlığı, metni, titreşim ayarları ve tıklama eylemi yapılandırılır.
- **`PendingIntent` Güvenliği**: Android 12+ (API 31+) güvenlik gereksinimleri için `PendingIntent.FLAG_IMMUTABLE` bayrağı kullanılır.

### 4. Manuel Bağımlılık Enjeksiyonu (AppContainer)
- `AppContainer` arayüzü ve `DefaultAppContainer` sınıfı üzerinden `WorkManagerWaterRepository` ilklendirilir.
- `WaterViewModel.Factory` vasıtasıyla `PlaygroundApplication` bağımlılığı reaktif olarak ViewModel'e aktarılır.
