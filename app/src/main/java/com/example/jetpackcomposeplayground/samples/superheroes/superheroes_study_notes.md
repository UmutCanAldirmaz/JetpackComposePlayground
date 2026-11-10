# Unit 3: SuperHeroes App - Çalışma ve Teknik Notlar

## Özet ve Konseptler

SuperHeroes uygulaması, liste içi yatay hizalamayı (`Row`), görsel kırpmayı ve Material Design 3 tipografi yerleşimini gösterir.

### 1. Yatay Yerleşim ve Ağırlık (`Row` & `weight`)
- `Column(modifier = Modifier.weight(1f))`: Kahraman adı ve açıklamasının yatay eksende kalan tüm boşluğu kaplamasını sağlar.
- `Image` bileşeni sağ tarafta `72.dp` sabit boyutta hizalanır.

### 2. Kart ve Şekil Tasarımı
- `Card(shape = RoundedCornerShape(16.dp))`: Yuvarlatılmış modern kart yapısı.
- `clip(RoundedCornerShape(8.dp))`: Kahraman görsellerinin köşelerini yuvarlatır.
