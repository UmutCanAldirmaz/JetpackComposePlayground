# Unit 4: Unscramble Game App - Çalışma ve Teknik Notlar

## Özet ve Konseptler

Unscramble uygulaması, ViewModel ile oyun mantığı yönetimini, kelime karıştırma algoritmalarını ve diyalog pencerelerini (`AlertDialog`) gösterir.

### 1. Oyun Mantığı ve State Management
- **`GameViewModel`**: Karıştırılmış kelime (`currentScrambledWord`), puan (`score`), kelime sayısı (`currentWordCount`) ve oyun bitti mi (`isGameOver`) durumlarını `GameUiState` veri sınıfı içerisinde tutar.
- **`shuffle()`**: Orijinal kelimenin karakterlerini rastgele karıştırır. Eğer tesadüfen orijinal kelimeyle aynı çıkarsa `while` döngüsü ile tekrar karıştırılır.

### 2. Girdi Doğrulama ve Klavye Olayları
- **`KeyboardOptions(imeAction = ImeAction.Done)`**: Klavyedeki Onayla (Done) tuşuna basıldığında `checkUserGuess()` tetiklenerek tahmin kontrol edilir.
- **`isError = isGuessWrong`**: Hatalı tahminde `OutlinedTextField` kırmızı hata durumuna geçer ve etiket metni değişir.

### 3. Oyun Sonu Diyaloğu
- **`FinalScoreDialog`**: 10 kelime tamamlandığında `AlertDialog` göstererek toplam skoru sunar ve "Tekrar Oyna" seçeneği ile durumu sıfırlar (`resetGame()`).
