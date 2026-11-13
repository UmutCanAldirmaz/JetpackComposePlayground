# Unit 8: Juice Tracker — Android Views, Fragments & Interoperability Çalışma Notları

Bu doküman, Android Kotlin Compose eğitim müfredatının **Unit 8 (Android Views & Interoperability)** konularını kapsayan Juice Tracker uygulamasının mimari yapısını, View-Compose birlikte çalışabilirlik (interoperability) konseptlerini ve karşılaşılan teknik zorlukların çözümlerini detaylıca açıklayan ders çalışma notudur.

---

## 📌 1. Uygulama Adım Adım Nasıl Çalışır? (Çalışma Akışı)

Juice Tracker; kullanıcıların meyve suyu tariflerini (isim, açıklama, renk, 1-5 arası yıldız derecelendirmesi) kaydedebildiği, düzenleyebildiği ve silebildiği bir takip uygulamasıdır.

Uygulamamız, Android'in iki farklı UI dünyasını (XML Views & Jetpack Compose) **tek bir ana ekran altında sekmeli (TabRow)** olarak sunar:

### 🔄 Uygulama Mimarisi ve Ekran Akış Tablosu

| Sekme | Çalıştırılan Mimari | Kullanılan Interoperability Tekniği | Açıklama |
| :---: | :--- | :--- | :--- |
| **Sekme 1: "Views in Compose"** | XML Navigation Graph + Fragment + RecyclerView | **`AndroidViewBinding` & `ComposeView`** | Compose `Scaffold` içinde `AndroidViewBinding` ile `activity_juice_tracker.xml` çalıştırılır. `TrackerFragment` içindeki `RecyclerView` elemanları `ComposeView` ile çizdirilir. |
| **Sekme 2: "Compose with Views"** | %100 Jetpack Compose UI + ModalBottomSheet | **`AndroidView`** | Pure Compose ekranında native Android `RatingBar`, `Spinner` ve `TextView` (AdBanner) bileşenleri `AndroidView` köprüsü ile Compose içine gömülür. |

---

## 🏗️ 2. Modüler Paket Mimarisi (Clean Sub-Packaging)

Kod karmaşasını önlemek ve her iki öğrenme konusunu izole etmek amacıyla paket yapısı sorumluluklara göre ayrılmıştır:

```
com.example.jetpackcomposeplayground.samples.juicetracker/
├── data/                               <-- Room Database, Entity, Dao & Repository
│   ├── Juice.kt                        (@Entity Veri Modeli)
│   ├── JuiceDao.kt                     (Room Data Access Object)
│   ├── JuiceRepository.kt              (Repository Arayüzü)
│   └── RoomJuiceRepository.kt          (Room Gerçekleştirimi)
│
├── views_with_compose/                 <-- Modül 1: View Tabanlı Uygulamaya Compose Ekleme
│   ├── TrackerFragment.kt              (XML Fragment - Liste Ekranı)
│   ├── EntryDialogFragment.kt         (XML BottomSheetDialogFragment - Ekleme/Düzenleme)
│   ├── JuiceListAdapter.kt             (RecyclerView ViewHolder içinde ComposeView kullanımı)
│   ├── TrackerViewModel.kt             (Fragment için ViewModel)
│   └── EntryViewModel.kt               (Dialog için ViewModel)
│
├── compose_with_views/                 <-- Modül 2: Compose Uygulamasına Native View Ekleme
│   ├── JuiceTrackerComposeScreen.kt    (Pure Compose UI İçeriği)
│   ├── JuiceTrackerComposeViewModel.kt (Compose Ekranı için ViewModel)
│   ├── bottomsheet/
│   │   ├── EntryBottomSheet.kt        (Compose BottomSheetForm)
│   │   ├── RatingInputRow.kt          (AndroidView -> RatingBar)
│   │   └── ColorSpinnerRow.kt          (AndroidView -> Spinner)
│   └── homescreen/
│       └── AdBanner.kt                 (AndroidView -> Native AdBanner View)
│
└── ui/                                 <-- Ana Birleştirici Ekran & ViewModel Factory
    ├── AppViewModelProvider.kt         (ViewModel Factory)
    └── JuiceTrackerScreen.kt           (Material 3 PrimaryTabRow ile Ana Birleştirici Ekran)
```

---

## 💡 3. Temel Interoperability (Birlikte Çalışabilirlik) Konseptleri

### A. `AndroidViewBinding` (View Binding'i Compose'a Gömme)
- **Amaç:** Mevcut bir XML layout dosyasını (örneğin `activity_juice_tracker.xml` içindeki `FragmentContainerView` ve `NavHostFragment` yapısını) Jetpack Compose ekranına gömmek.
- **Kullanım:**
```kotlin
AndroidViewBinding(
    factory = ActivityJuiceTrackerBinding::inflate,
    modifier = Modifier.fillMaxSize()
)
```

### B. `ComposeView` (View / RecyclerView İçine Compose Gömme)
- **Amaç:** `RecyclerView.ViewHolder` veya geleneksel XML View yerleşimleri içinde Jetpack Compose UI bileşenleri çizdirmek.
- **Kullanım:**
```kotlin
class JuiceListViewHolder(
    private val composeView: ComposeView,
    private val onEdit: (Juice) -> Unit,
    private val onDelete: (Juice) -> Unit
) : RecyclerView.ViewHolder(composeView) {

    fun bind(input: Juice) {
        composeView.setContent {
            ListItem(
                input = input,
                onDelete = onDelete,
                modifier = Modifier.fillMaxWidth().clickable { onEdit(input) }
            )
        }
    }
}
```

### C. `AndroidView` (Native Android View'ları Compose'a Gömme)
- **Amaç:** Jetpack Compose'da henüz doğrudan dengi bulunmayan veya legacy Android View bileşenlerini (`RatingBar`, `Spinner`, `AdView` vb.) Compose UI içinde kullanmak.
- **`factory` Parametresi:** View ilk kez oluşturulduğunda bir kez çalışır.
- **`update` Parametresi:** Recomposition gerçekleştiğinde veya State değiştiğinde tetiklenir, View özelliklerini günceller.
- **Örnek - `RatingBar` Entegrasyonu:**
```kotlin
AndroidView(
    factory = { context ->
        RatingBar(context).apply {
            stepSize = 1f
            numStars = 5
        }
    },
    update = { ratingBar ->
        ratingBar.rating = rating.toFloat()
        ratingBar.onRatingBarChangeListener = RatingBar.OnRatingBarChangeListener { _, _, _ ->
            onRatingChange(ratingBar.rating.toInt())
        }
    }
)
```

---

## 🐞 Karşılaşılan Kritik Hatalar ve Çözümleri

### 1. `java.lang.reflect.InvocationTargetException` (Inflation Crash)
- **Kök Neden 1:** `MainActivity` sınıfının `ComponentActivity` miras alması. `FragmentContainerView` (`NavHostFragment`) XML'den türetilirken `FragmentManager` bulamadığı için `UnsupportedOperationException` fırlatıyordu.
  - **Çözüm:** `MainActivity` sınıfı `AppCompatActivity` (dolayısıyla `FragmentActivity`) sınıfından türetildi.
- **Kök Neden 2:** App teması `android:Theme.Material.Light.NoActionBar` iken XML içinde Material 3 bileşenleri (`MaterialButton`, `MaterialDivider`, `FloatingActionButton`) kullanılması. `MaterialButton` stili eksik olduğu için reflection esnasında `IllegalArgumentException` fırlatıyordu.
  - **Çözüm:** `themes.xml` içindeki tema ebeveyni `Theme.Material3.DayNight.NoActionBar` olarak güncellendi.

### 2. Material 3 Renk Hiyerarşisi (Surface Color Separation)
- **Sorun:** TopAppBar ve Liste Kartları aynı `surface` rengini kullandığı için üst bar ile kartlar görsel olarak birbirine karışıyordu.
- **Çözüm:** 
  - `TopAppBar`: `MaterialTheme.colorScheme.surfaceContainer`
  - `ElevatedCard`: `MaterialTheme.colorScheme.surfaceContainerLow`
  - `Scaffold Background`: `MaterialTheme.colorScheme.background`

---

## 🧪 Sınama ve Doğrulama
- **Gradle Build:** `app:assembleDebug` sorunsuz tamamlandı.
- **Compose Previews:** `PreviewListItem` ve `PreviewAdBanner` render testlerinden başarıyla geçti.
