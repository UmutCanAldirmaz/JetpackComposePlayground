# Unit 2: Lemonade App - Çalışma ve Teknik Notlar

## Özet ve Konseptler

Lemonade uygulaması, Jetpack Compose ile durum makinesi (State Machine) tasarımını ve dinamik tıklama sayaçlarını gösterir.

### 1. Çok Aşamalı Durum Makinesi (State Machine)
- Uygulama 4 adımdan oluşur:
  1. **Aşama 1 (Limon Seçme)**: Limon ağacından limon seçilir (`step = 1`).
  2. **Aşama 2 (Sıkma)**: Limon sıkılır. Rastgele üretilen `requiredSqueezes` (2 ile 4 arası) kadar tıklama gerekir (`step = 2`).
  3. **Aşama 3 (İçme)**: Limonata içilir (`step = 3`).
  4. **Aşama 4 (Yenileme)**: Boş bardak tıklanarak başa dönülür (`step = 4 -> 1`).

### 2. Tıklama Sayacı ve Koşullu Mantık
- `squeezeCount++` ile her tıklamada sayaç artırılır.
- `if (squeezeCount >= requiredSqueezes)` şartı sağlandığında bir sonraki aşamaya (`step = 3`) geçilir.

### 3. UI Tasarımı ve Bileşenler
- `Box`, `clip(RoundedCornerShape)`, `border` ve `clickable` Modifier özellikleri kullanılarak şık ve interaktif kart tasarımı elde edilmiştir.
- `Pair(instructionText, imageRes)` ile adıma uygun metin ve görsel dinamik olarak bağlanır.
