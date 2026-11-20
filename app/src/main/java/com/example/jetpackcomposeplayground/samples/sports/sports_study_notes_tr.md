# Sports Uygulaması - Uyarlanabilir Liste ve Detay Notları

Bu doküman, Google **Android Basics with Compose** kursunun uyarlanabilir düzen (adaptive layout) pratiği olan **Sports** uygulaması için hazırlanan çalışma notlarını içerir. Uygulama, playground kataloğundan `SportsSampleScreen` ile açılır.

---

## 1. Projenin Amacı ve Kapsamı

Sports, spor dallarının listesini ve seçilen sporun detayını gösteren örnek bir uygulamadır. Amaç, aynı ekranı hem telefonda hem geniş pencerede çalıştırmaktır:

* Dar pencerede önce liste, bir karta basınca detay gösterilir.
* Geniş pencerede liste ve detay yan yana durur.

Reply uygulamasından farkı şudur: Sports, alt gezinme çubuğu, navigation rail veya drawer değiştirmez. Yalnızca içeriğin `ListOnly` ya da `ListAndDetail` olarak yerleşmesine karar verir.

---

## 2. Temel Gereksinimler ve Bağımlılıklar

* **Jetpack Compose ve Material 3:** `Scaffold`, `TopAppBar`, `Card`, `LazyColumn`.
* **Window Size Classes (`androidx.compose.material3:material3-window-size-class`):** Pencere genişliğini `Compact`, `Medium` ve `Expanded` olarak okumak için.
* **ViewModel ve StateFlow:** Seçili sporun ve “listede miyiz, detayda mıyız?” bilgisinin yaşam döngüsünden bağımsız tutulması.
* **Kaynak dosyaları:** Metinler `res/values/sports_strings.xml`, boşluklar `res/values/sports_dimens.xml`, görseller `res/drawable/ic_*_square.xml` ve `ic_*_banner.xml` içindedir.

---

## 3. Dosya Yapısı

| Dosya | Görevi |
| --- | --- |
| `SportsSampleScreen.kt` | Katalog giriş noktası. Pencere sınıfını hesaplar, `SportsTheme` ve `SportsApp` açar. |
| `ui/SportsScreens.kt` | Liste, detay ve iki bölmeli düzen composable'ları. |
| `ui/SportsViewModel.kt` | `SportsUiState` ve liste/detay geçişleri. |
| `model/Sport.kt` | Spor modeli. Metin ve görseller kaynak kimliği olarak tutulur. |
| `data/LocalSportsDataProvider.kt` | Başlangıç spor listesi. |
| `utils/WindowStateUtils.kt` | `SportsContentType`: `ListOnly`, `ListAndDetail`. |
| `ui/theme/` | Sports'a özel renk, tipografi ve `SportsTheme`. |

---

## 4. Adım Adım Akış

### Adım 1: Veri modeli

`Sport` bir spor dalını temsil eder: kimlik, başlık, alt yazı, oyuncu sayısı, olimpik olup olmadığı, liste görseli, banner görseli ve detay metni. Metinler `@StringRes`, görseller `@DrawableRes` ile tutulur. Böylece ekran, ham metin yerine `stringResource` ve `painterResource` kullanır.

`LocalSportsDataProvider` bu modeli doldurur. Varsayılan seçim listenin ilk elemanıdır.

### Adım 2: Durum

`SportsUiState` üç alan tutar:

* `sportsList`: gösterilecek sporlar
* `currentSport`: seçili spor
* `isShowingListPage`: dar pencerede listenin mi detayın mı göründüğü

`SportsViewModel` bu durumu `MutableStateFlow` içinde günceller:

* `updateCurrentSport`: seçilen kartı yazar
* `navigateToDetailPage`: `isShowingListPage = false`
* `navigateToListPage`: `isShowingListPage = true`

Ekran, `collectAsState()` ile bu akışı okur.

### Adım 3: Pencere genişliğine göre içerik

`SportsSampleScreen`, `calculateWindowSizeClass` ile genişlik sınıfını alır. Activity bulunamazsa `Compact` kabul edilir.

`SportsApp` sınıfı içeriğe şöyle çevirir:

* **Compact ve Medium:** `SportsContentType.ListOnly`
* **Expanded:** `SportsContentType.ListAndDetail`

`ListOnly` iken `isShowingListPage` true ise `SportsList`, false ise `SportsDetail` tam ekran çizilir. Bir karta basınca seçim güncellenir ve detay sayfasına geçilir.

`ListAndDetail` iken `SportsListAndDetail` bir `Row` kurar. Liste `weight(2f)`, detay `weight(3f)` alır. Kart seçimi yalnızca `currentSport` değerini değiştirir; sayfa değişmez.

### Adım 4: Üst çubuk ve geri davranışı

Üst çubuk başlığı dar penceredeki detayda `sports_detail_header`, diğer durumlarda `sports_list_header` olur. Geri oku yalnızca Expanded olmayan pencerede ve detay görünürken çıkar; basılınca listeye dönülür.

Sistem geri tuşu:

* Dar pencerede detay açıkken listeye döner (`SportsDetail` içindeki `BackHandler`).
* Liste görünürken veya geniş penceredeyken katalogdaki `onBackPressed` çalışır ve ana listeye dönülür. `SportsSampleScreen` de kendi `BackHandler` kaydını tutar.

### Adım 5: Liste kartı ve detay

Kart, kare görseli, başlığı, alt yazıyı, `sports_player_count` çoğul metnini ve spor olimpikse `sports_olympic_caption` yazısını gösterir. Köşe yarıçapı, kart yüksekliği ve boşluklar `sports_dimens.xml` dosyasından okunur.

Detay ekranı banner görselinin altına bir degrade çeker, başlığı ve oyuncu sayısını bunun üzerine yazar, ardından `sport_detail_text` metnini kaydırılabilir bir sütunda gösterir.

---

## 5. Öğrenilen Kavramlar

1. **Window Size Classes:** Tasarım, cihaz adına göre değil kullanılabilir pencere genişliğine göre seçilir. Bu uygulamada iki bölmeli düzen yalnızca `Expanded` pencerede açılır; `Medium` hâlâ tek sütundur.
2. **Liste–detay deseni:** Aynı veriyi dar ekranda art arda, geniş ekranda yan yana göstermek. Seçili öğe her iki düzende de `currentSport` alanındadır.
3. **Tek yönlü veri akışı:** Composable'lar durumu kendileri saklamaz. Tıklama ViewModel fonksiyonunu çağırır, ekran yeni `SportsUiState` ile yeniden çizilir.
4. **Kaynak kimlikleri:** Modelde `String` veya dosya yolu yerine `R.string` ve `R.drawable` tutulur. Metin ve boyut değişince Kotlin dosyasına girmeden `sports_strings.xml` ve `sports_dimens.xml` düzenlenir.
5. **`findActivity`:** `LocalContext` bazen bir `ContextWrapper` olur. `calculateWindowSizeClass` için alttaki `Activity` örneğine `tailrec` bir döngüyle inilir.
6. **Önizleme:** `SportsAppCompactPreview` (400dp) tek sütunu, `SportsAppExpandedPreview` (1000dp) liste–detay düzenini Android Studio önizlemesinde gösterir.
