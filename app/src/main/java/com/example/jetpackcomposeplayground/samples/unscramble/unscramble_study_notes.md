# Unscramble Uygulaması - Çalışma ve Öğrenme Notları

## Proje Amacı
Unscramble, harfleri karıştırılmış kelimeleri tahmin etme üzerine kurulmuş bir kelime bulmaca oyunudur. Amaç, **ViewModel** ve **UI State** mimarisini öğrenmektir.

## Temel Öğrenilenler ve Teknikler
1. **MVVM ve StateFlow:**
   - Oyun verilerinin ve durumunun (`GameUiState`) `ViewModel` içinde tutulması, UI katmanının ise sadece bu duruma göre tepki vermesi.
2. **Kullanıcı Girdisi Doğrulama:**
   - Kullanıcının tahmin ettiği kelimenin kontrol edilmesi, doğruysa skor artışı ve yeni kelimeye geçiş, yanlışsa hata mesajı gösterilmesi.
3. **AlertDialog Kullanımı:**
   - Oyun bittiğinde tebrik mesajı ve yeniden başlatma seçeneği sunan `AlertDialog` entegrasyonu.
4. **State Hoisting:**
   - Composable bileşenlerin durumdan arındırılarak (stateless) yeniden kullanılabilir hale getirilmesi.
