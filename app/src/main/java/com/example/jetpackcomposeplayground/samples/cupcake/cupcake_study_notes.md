# Cupcake Uygulaması - Çalışma ve Öğrenme Notları

## Proje Amacı
Cupcake uygulaması, Android Basics with Compose kursunun 4. ünitesinde **Jetpack Navigation** bileşenini öğrenmek için geliştirilmiş çok ekranlı bir sipariş yönetim uygulamasıdır.

## Temel Öğrenilenler ve Teknikler
1. **Jetpack Navigation Compose:**
   - `NavHostController`: Navigasyonun kontrolcüsü.
   - `NavHost`: Navigasyon grafiğini tanımlayan ana kapsayıcı.
   - `composable(route)`: Rotaların tanımlandığı ve ekranların bağlandığı yer.
2. **Ekranlar Arası Veri Paylaşımı:**
   - `ViewModel` kullanarak sipariş verilerinin (`OrderUiState`) ekranlar arasında korunması ve paylaşılması.
3. **Geri Yığını (Back Stack) ve Navigasyon Aksiyonları:**
   - `navController.navigate(...)` ile ileri gitme.
   - `navController.popBackStack(...)` ile belirli bir ekrana dönme veya siparişi iptal etme.
4. **Implicit Intents (Dolaylı Niyetler):**
   - Sipariş özetini e-posta veya başka bir uygulama üzerinden paylaşmak için `Intent.ACTION_SEND` kullanımı.
