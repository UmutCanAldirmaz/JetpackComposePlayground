# Unit 4: Lunch Tray App - Çalışma ve Teknik Notlar

## Özet ve Konseptler

Lunch Tray uygulaması, menü seçimleri (Ana Yemek, Yan Yemek, İçecek/Eşlikçi) ve hesap kartı özeti adımlarından oluşan bir Navigation sipariş akışıdır.

### 1. Çok Aşamalı Sipariş Navigasyonu
- **`LunchTrayScreenRoute`**: `Start`, `Entree`, `SideDish`, `Accompaniment`, `Checkout` adımlarını temsil eder.
- Her menü seçim ekranı tamamlandığında bir sonraki rotaya (`navController.navigate()`) geçilir.

### 2. Fiyat ve Vergi Hesaplaması
- `OrderViewModel`: Seçilen her `MenuItem` için ara toplamı (`subtotal`), vergiyi (`tax` - %8) ve genel toplamı (`total`) reaktif günceller.
- `resetOrder()`: İptal veya sipariş tamamlama durumunda sepet verilerini temizler.
