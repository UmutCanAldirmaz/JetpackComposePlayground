# Unit 4: Cupcake App - Çalışma ve Teknik Notlar

## Özet ve Konseptler

Cupcake uygulaması, Jetpack Navigation component ile çok ekranlı sipariş akışını, `SharedViewModel` kalıbını ve Android Share Intent entegrasyonunu gösterir.

### 1. Jetpack Navigation & NavHost
- **`NavHost` & `NavHostController`**: Sipariş başlangıcı (`Start`), Aroma seçimi (`Flavor`), Tarih seçimi (`Pickup`) ve Özet (`Summary`) ekranları arasında geçişi yönetir.
- **`currentBackStackEntryAsState()`**: Aktif rota değiştiğinde `CupcakeAppBar` başlığını otomatik günceller.

### 2. Paylaşımlı Durum (Shared ViewModel)
- `OrderViewModel`: Sipariş adedi, aroma, teslimat tarihi ve hesaplanan toplam tutarı ekranlar arasında ortak tutar.
- `cancelOrderAndNavigateToStart()`: Sipariş iptal edildiğinde state sıfırlanır ve `popBackStack(Start.name, inclusive = false)` ile başlangıç ekranına dönülür.

### 3. Sipariş Paylaşımı (`Intent.ACTION_SEND`)
- Sipariş özeti `Intent.createChooser` ile WhatsApp, E-posta vb. dış uygulamalarla paylaşılır.
