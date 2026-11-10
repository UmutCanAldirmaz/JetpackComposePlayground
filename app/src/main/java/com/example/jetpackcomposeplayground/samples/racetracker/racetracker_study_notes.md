# Unit 5: Race Tracker App - Çalışma ve Teknik Notlar

## Özet ve Konseptler

Race Tracker uygulaması, Kotlin Coroutines, `coroutineScope`, `launch`, `delay` ve coroutine iptali/duraklatması ile eşzamanlı yarış simülasyonunu gösterir.

### 1. Eşzamanlı Coroutine Çalıştırma (`coroutineScope` & `launch`)
- **`coroutineScope` & `launch`**: İki oyuncunun (`playerOne`, `playerTwo`) yarış adımlarını paralel iki coroutine olarak çalıştırır.
- **`delay(progressDelayMillis)`**: Yarış adımları arasında gecikme ekleyerek asenkron ilerleme simüle eder.

### 2. Duraklatma ve İptal Mekanizması
- `raceInProgress` değişkeni `false` olduğunda veya sayfa kapandığında `LaunchedEffect` coroutine kapsamı otomatik iptal edilir veya duraklatılır.

### 3. UI İlerleme Göstergeleri
- `LinearProgressIndicator(progress = { progressFactor })`: İlerleme çubuğunu reaktif günceller.
