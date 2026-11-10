# Unit 2: Tip Calculator App - Çalışma ve Teknik Notlar

## Özet ve Konseptler

Tip Calculator uygulaması, kullanıcı girdilerini almayı (Forms & Input), klavye türü yapılandırmalarını, durum saklamayı (`rememberSaveable`) ve hesaplama mantığını kapsar.

### 1. Form ve Klavye Yönetimi
- **`OutlinedTextField`**: Fatura tutarı (`amountInput`), bahşiş oranı (`tipInput`) ve kişi sayısı (`personCountInput`) için metin girdileri alır.
- **`KeyboardOptions(keyboardType = KeyboardType.Number)`**: Kullanıcıya sadece sayısal klavye gösterir.
- **`rememberSaveable`**: Ekran döndürüldüğünde (configuration change) metin girdilerinin ve Switch durumunun korunmasını sağlar.

### 2. Hesaplama ve Yuvarlama Mantığı
- **`calculateTip`**: Fatura tutarı ve bahşiş oranına göre kişi başı düşen bahşiş miktarını hesaplar.
- **`ceil()` & `Switch`**: Bahşişi yukarı yuvarlama seçeneği aktifse `ceil()` ile en yakın üst tamsayıya yuvarlar.
- **`NumberFormat.getCurrencyInstance()`**: Yerel para birimi formatına (₺, $, € vb.) dönüştürür.

### 3. Birim Testi Katmanı
- `@VisibleForTesting internal fun calculateTip(...)` metodu birim testler ile bağımsız olarak doğrulanabilir.
