# Unit 3: Lazy Grid Practice - Çalışma ve Teknik Notlar

## Özet ve Konseptler

Lazy Grid uygulaması, `LazyVerticalGrid` ve `LazyHorizontalGrid` bileşenleri ile ızgara (Grid) düzenlerini, esnek sütun yapılarını (`GridCells.Adaptive`, `GridCells.Fixed`) ve öge kaplama alanlarını (`GridItemSpan`) gösterir.

### 1. Dikey Izgara (`LazyVerticalGrid`)
- **`GridCells.Adaptive(minSize = 100.dp)`**: Ekran genişliğine göre dinamik olarak sütun sayısını hesaplar.
- **`GridItemSpan(maxLineSpan)`**: 5'in katı olan öğelerin (5, 10, 15 vb.) tüm satırı kaplamasını sağlar.

### 2. Yatay Izgara (`LazyHorizontalGrid`)
- **`GridCells.Fixed(3)`**: Sabit 3 satırdan oluşan ve yatay kaydırılabilen profil kartları grid yapısı.
