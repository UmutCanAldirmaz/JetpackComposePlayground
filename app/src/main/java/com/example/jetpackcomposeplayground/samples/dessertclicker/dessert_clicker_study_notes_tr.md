# Unit 4: Dessert Clicker App - Çalışma ve Teknik Notlar

## Özet ve Konseptler

Dessert Clicker uygulaması, Android Yaşam Döngüsünü (Lifecycle), ViewModel ile durum yönetimini ve Intent ile dış uygulamalara veri paylaşımını gösterir.

### 1. Android Yaşam Döngüsü ve State Handling
- `DessertClickerViewModel`: Satılan tatlı sayısı (`dessertsSold`) ve elde edilen geliri (`revenue`) tutar.
- `collectAsStateWithLifecycle()`: Lifecycle durumuna duyarlı olarak `StateFlow` verisini Compose tarafında güvenle dinler.

### 2. Dış Uygulamalarla Paylaşım (`Intent.ACTION_SEND`)
- `shareSoldDessertsInformation()`: `Intent(Intent.ACTION_SEND)` ve `Intent.createChooser()` kullanarak kullanıcının satılan tatlı istatistiklerini mesajlaşma veya sosyal medya uygulamalarında paylaşmasını sağlar.
