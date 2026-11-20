# Unit 2: Art Space App - Çalışma ve Teknik Notlar

## Özet ve Konseptler

Art Space uygulaması, görsel sanat galerisi sergileme düzenini, kart tasarımlarını ve liste içi gezinme durumlarını gösterir.

### 1. Veri Yapısı ve Sanat Eseri Modeli
- `data class Artwork(val titleRes: Int, val artistRes: Int, val imageRes: Int)` veri sınıfı ile sanat eserlerinin başlığı, sanatçısı ve görsel kaynağı tanımlanmıştır.

### 2. Galeri Gezinme Mantığı
- `var currentIndex by remember { mutableStateOf(0) }` ile şu an gösterilen sanat eserinin indeksi tutulur.
- **Önceki (Previous) Butonu**: `if (currentIndex > 0) currentIndex - 1 else artworks.size - 1` ile başa veya sona döner.
- **Sonraki (Next) Butonu**: `if (currentIndex < artworks.size - 1) currentIndex + 1 else 0` ile döngüsel geçiş sağlar.

### 3. UI Tasarımı ve Layout
- `Card` ve `Surface` bileşenlerine yükseklik gölgesi (`elevation`) ve sınır çizgisi (`BorderStroke`) eklenerek çerçeveli müze sergisi görünümü elde edilmiştir.
- `ContentScale.Fit` ile görsellerin oranları bozulmadan kart içine sığdırılması sağlanmıştır.
