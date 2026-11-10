# Unit 3: Affirmations App - Çalışma ve Teknik Notlar

## Özet ve Konseptler

Affirmations uygulaması, Jetpack Compose'da dinamik verileri `LazyColumn` ile listelemeyi ve `Card` mimarisini gösterir.

### 1. Dikey Kaydırılabilir Liste (`LazyColumn`)
- `LazyColumn`: Yalnızca ekranda görünen öğeleri belleğe yükleyerek (Lazy Loading) yüksek performanslı kaydırma sağlar.
- `items(affirmations)`: Liste elemanlarını döngüye alarak kart bileşenlerine bağlar.
- `verticalArrangement = Arrangement.spacedBy(16.dp)`: Kartlar arasına dinamik boşluk ekler.

### 2. Görsel ve Metin Kart Tasarımı (`Card`)
- `ContentScale.Crop`: Görsellerin en-boy oranını koruyarak kart genişliğini tam kaplamasını sağlar.
- `shape = RoundedCornerShape(12.dp)`: Kart köşelerini yuvarlatır.
- `elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)`: Kartlara derinlik gölgesi kazandırır.
