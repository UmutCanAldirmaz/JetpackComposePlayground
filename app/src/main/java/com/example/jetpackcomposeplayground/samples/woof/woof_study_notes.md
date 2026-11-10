# Unit 3: Woof App - Çalışma ve Teknik Notlar

## Özet ve Konseptler

Woof uygulaması, Material Design 3 temalandırmasını, genişletilebilir kart animasyonlarını (`animateContentSize`, `animateColorAsState`) ve karanlık mod (Dark Theme) uyumluluğunu gösterir.

### 1. Yumuşak Animasyonlar (Animations)
- **`animateContentSize()`**: Kart tıklandığında genişleme/daralma hareketini `spring` fiziksel yay animasyon spesifikasyonu ile pürüzsüzleştirir.
- **`animateColorAsState()`**: Kart genişlediğinde arka plan rengini `surfaceVariant` ile `tertiaryContainer` arasında kademeli olarak değiştirir.

### 2. Düzeyli UI Bileşenleri
- **`DogItemButton`**: `ExpandMore` ve `ExpandLess` ikonları ile genişleme durumunu gösterir ve tetikler.
- **`CircleShape` & `ContentScale.Crop`**: Köpek profil resimlerini dairesel kırpar.

### 3. Material Design 3 Temalandırması
- Light ve Dark Mode desteği `@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)` ile test edilebilir.
