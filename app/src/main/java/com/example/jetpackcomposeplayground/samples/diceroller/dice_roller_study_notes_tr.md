# Unit 2: Dice Roller App - Çalışma ve Teknik Notlar

## Özet ve Konseptler

Dice Roller uygulaması, Jetpack Compose'da durum yönetiminin (State Management) temel prensiplerini ve interaktif buton olaylarını gösterir.

### 1. Durum Yönetimi (State Management)
- **`remember { mutableStateOf(1) }`**: Zarların sonucunu (`diceResult`) Compose Recomposition döngüsü boyunca bellekte tutar.
- **`Random.nextInt(1, 7)`**: Butona tıklandığında 1 ile 6 arasında rastgele bir tamsayı üretir ve durumu günceller.

### 2. Görsel Kaynaklar ve Koşullu Rendering
- `when (diceResult)` bloğu ile zar sonucuna karşılık gelen `R.drawable.dice_x` görsel kaynağı belirlenir.
- `Image(painter = painterResource(id = imageResource))` ile zar görseli dinamik olarak güncellenir.

### 3. Kullanıcı Arayüzü Düzeni (Layout)
- `Scaffold` ve `TopAppBar` ile sayfa düzeni sağlanır.
- `Column`, `Arrangement.Center` ve `Alignment.CenterHorizontally` ile zar ve buton ekranın ortasına hizalanır.
- `BackHandler` ile sistem geri tuşu dinlenerek ana kataloğa dönülmesi sağlanır.
