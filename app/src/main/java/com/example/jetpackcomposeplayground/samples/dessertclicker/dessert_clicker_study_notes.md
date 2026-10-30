# Dessert Clicker Uygulaması - Çalışma ve Öğrenme Notları

## Proje Amacı
Dessert Clicker, Android **Activity Yaşam Döngüsünü (Lifecycle)** ve ekran döndürme gibi konfigürasyon değişikliklerinde verilerin nasıl korunduğunu anlamak için geliştirilmiştir.

## Temel Öğrenilenler ve Teknikler
1. **Activity Yaşam Döngüsü:**
   - `onCreate`, `onStart`, `onResume`, `onPause`, `onStop`, `onDestroy` metotlarının gözlemlenmesi ve loglanması.
2. **`remember` vs `rememberSaveable`:**
   - Normal `remember` state'inin ekran döndüğünde kaybolduğunu, ancak `rememberSaveable` veya `ViewModel` kullanıldığında verinin korunduğunu görmek.
3. **Implicit Intents (Paylaşım):**
   - Elde edilen skorun veya tatlı sayısının başka uygulamalarla paylaşılması.
