# Lunch Tray Uygulaması - Çalışma ve Öğrenme Notları

## Proje Amacı
Lunch Tray, öğle yemeği menüsü (Ana yemek, yan yemek, tatlı/eşlikçi) seçimi ve çok adımlı sipariş akışını **Navigation Compose** ile yönetmeyi pekiştiren bir uygulamadır.

## Temel Öğrenilenler ve Teknikler
1. **Çok Adımlı Sipariş Akışı:**
   - Start -> Entree -> Side Dish -> Accompaniment -> Checkout şeklinde sıralı navigasyon adımları.
2. **ViewModel ve State Güncellemeleri:**
   - Her adımda seçilen menü öğesinin (`MenuItem`) sipariş durumuna (`OrderUiState`) eklenmesi ve anlık fiyat/vergi hesaplamaları.
3. **Rotalar ve Enum Yönetimi:**
   - `LunchTrayScreenRoute` enum sınıfı ile ekran rotalarının `name` üzerinden güvenli yönetilmesi.
4. **Geri ve İptal Yönetimi:**
   - Sipariş iptal edildiğinde sepetin sıfırlanıp (`resetOrder`) başlangıç ekranına dönülmesi (`popBackStack`).
