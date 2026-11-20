# Unit 3: Order History App - Çalışma ve Teknik Notlar

## Özet ve Konseptler

Order History uygulaması, polimorfik liste öğelerini (`sealed interface OrderFeedItem`), `key` ve `contentType` parametrelerini kullanarak yüksek performanslı çoklu görünüm tipli liste mimarisini gösterir.

### 1. Sealed Interface ve Polimorfizm
- `OrderFeedItem`: `SummaryCard`, `ActiveOrder`, `PromotionBanner` veri tiplerini kapsar.
- `when (item)` bloğu ile her veri tipine karşılık gelen farklı UI kartı çizilir.

### 2. LazyColumn Performans Optimizasyonu
- **`key = { item -> item.id }`**: Öğelerin kimliklerini tanımlayarak Recomposition maliyetini düşürür.
- **`contentType = { item -> item::class.java.name }`**: Farklı kart tiplerini gruplayarak görünüm dönüşümünü (View Recycling) optimize eder.
