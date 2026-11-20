<div align="center">

# 🎮 JetpackComposePlayground

### Android Kotlin + Jetpack Compose için eksiksiz referans: 30+ gerçek dünya uygulaması, çalışma notları ve üretim seviyesinde mimari kalıplar.

<p>
  <img src="https://img.shields.io/badge/Platform-Android-3DDC84?style=for-the-badge&logo=android&logoColor=white" alt="Android" />
  <img src="https://img.shields.io/badge/Kotlin-2.x-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white" alt="Kotlin" />
  <img src="https://img.shields.io/badge/Jetpack%20Compose-100%25-4285F4?style=for-the-badge&logo=jetpackcompose&logoColor=white" alt="Jetpack Compose" />
  <img src="https://img.shields.io/badge/Material%20Design-3-6750A4?style=for-the-badge&logo=materialdesign&logoColor=white" alt="Material 3" />
</p>
<p>
  <img src="https://img.shields.io/badge/Gradle-Kotlin%20DSL-02303A?style=for-the-badge&logo=gradle&logoColor=white" alt="Gradle" />
  <img src="https://img.shields.io/badge/Min%20SDK-24-brightgreen?style=for-the-badge" alt="Min SDK 24" />
  <img src="https://img.shields.io/badge/Target%20SDK-37-blue?style=for-the-badge" alt="Target SDK 37" />
  <img src="https://img.shields.io/badge/Lisans-MIT-yellow?style=for-the-badge" alt="Lisans" />
</p>

<p>
  <img src="https://img.shields.io/badge/Mimari-MVVM%20%2B%20UDF-orange?style=flat-square" alt="MVVM + UDF" />
  <img src="https://img.shields.io/badge/%C3%96rnek-31-success?style=flat-square" alt="31 örnek" />
  <img src="https://img.shields.io/badge/%C3%9Cnite-8-informational?style=flat-square" alt="8 ünite" />
  <img src="https://img.shields.io/badge/PR%27lar-a%C3%A7%C4%B1k-ff69b4?style=flat-square" alt="PR'lar açık" />
</p>

<p>
  🇹🇷 <b>Türkçe</b> &nbsp;·&nbsp; 🇬🇧 <a href="English.md">English</a>
</p>

<img src="docs/screenshots/home.png" width="260" alt="JetpackComposePlayground ana ekranı" />

<img src="docs/screenshots/home2.png" width="260" alt="ana ekranı gece modu" />

<br/><br/>

**[Genel Bakış](#-proje-genel-bakış) · [Ekran Görüntüleri](#-ekran-görüntüleri) · [Mimari](#-mimari-ve-temel-özellikler) · [Uygulama Kataloğu](#-etkileşimli-uygulama-kataloğu) · [Interop Vitrini](#-view-ve-compose-birlikte-çalışabilirlik-vitrini) · [Kurulum](#-başlarken-ve-kurulum) · [Testler](#-test-stratejisi)**

</div>

---

## 📑 İçindekiler

- [Proje Genel Bakış](#-proje-genel-bakış)
- [Ekran Görüntüleri](#-ekran-görüntüleri)
- [Mimari ve Temel Özellikler](#-mimari-ve-temel-özellikler)
- [Teknoloji Yığını](#-teknoloji-yığını)
- [Etkileşimli Uygulama Kataloğu](#-etkileşimli-uygulama-kataloğu)
- [View ve Compose Birlikte Çalışabilirlik Vitrini](#-view-ve-compose-birlikte-çalışabilirlik-vitrini)
- [Başlarken ve Kurulum](#-başlarken-ve-kurulum)
- [Proje Yapısı](#-proje-yapısı)
- [Çalışma Notları ve Dokümantasyon](#-çalışma-notları-ve-dokümantasyon)
- [Test Stratejisi](#-test-stratejisi)
- [Katkıda Bulunma](#-katkıda-bulunma)
- [Lisans ve Yazar](#-lisans-ve-yazar)

---

## 🌟 Proje Genel Bakış

**JetpackComposePlayground**, **Android Kotlin Compose müfredatının tamamını (Ünite 1 → Ünite 8)** tek bir çatı altında toplayan, bütünlüklü bir Android uygulamasıdır. Birbirinden kopuk 30'dan fazla örnek proje yerine; basit bir `Button`'dan zincirleme bir `WorkManager` akışına kadar her kavramın yan yana durduğu, gerçek cihazda çalışan ve yazılı çalışma notlarıyla desteklenen tek bir kod tabanı sunar.

Hem bir **öğrenme laboratuvarı** hem de bir şeyin *nasıl doğru yapılacağını* hatırlamak istediğinde **dönüp bakabileceğin bir referans** olarak tasarlandı.

### 🎯 Geliştiricilere neler sunuyor?

| | |
|---|---|
| 🧱 **Kademeli öğrenme yolu** | State temellerinden Room, Retrofit, DataStore, WorkManager ve View interoperability'ye uzanan sekiz ünite. |
| 🏗️ **Oyuncak değil, gerçek mimari** | MVVM, Unidirectional Data Flow, Repository pattern ve `AppContainer` ile manuel DI. |
| 🔌 **Üretim tarzı entegrasyonlar** | Type-safe navigasyon, çevrimdışı öncelikli kalıcılık, arka plan iş zincirleri ve doğru hata durumlarına sahip ağ katmanları. |
| 🔁 **Eski ↔ Compose köprüleri** | Kademeli geçiş için `ComposeView`, `AndroidView` ve `AndroidViewBinding` ile çalışan örnekler. |
| 🧪 **Test edilmiş kod** | Compose UI testleri, navigasyon testleri, DAO kalıcılık testleri, WorkManager ve fake tabanlı `ViewModel` testleri. |
| 📝 **Her uygulama için çalışma notu** | Her örnekte yalnızca *ne*'yi değil, *neden*'i de anlatan bir `*_study_notes.md` bulunur. |
| 🧪 **Bir sandbox** | *Exercise Lab*, seçilmiş örneklere dokunmadan serbestçe denemeler yapabileceğin bir alandır. |

> [!NOTE]
> Bu depo bir **öğrenme ve referans projesidir**. Örnekler bilinçli olarak odaklı tutulmuştur: her biri tek bir kavramı veya kalıbı izole eder; böylece okumak, kopyalamak ve uyarlamak kolaydır.

---

## 📸 Ekran Görüntüleri

**⭐ Öne Çıkanlar**

<div align="center">

<table>
  <tr>
    <td align="center" width="25%"><img src="docs/screenshots/reply.png" width="170" alt="Reply" /><br/><sub><b>📧 Reply</b></sub></td>
    <td align="center" width="25%"><img src="docs/screenshots/flightsearch.png" width="170" alt="Flight Search" /><br/><sub><b>✈️ Flight Search</b></sub></td>
    <td align="center" width="25%"><img src="docs/screenshots/bluromatic.png" width="170" alt="Bluromatic" /><br/><sub><b>🌫️ Bluromatic</b></sub></td>
    <td align="center" width="25%"><img src="docs/screenshots/juicetracker.png" width="170" alt="Juice Tracker" /><br/><sub><b>🧃 Juice Tracker</b></sub></td>
  </tr>
</table>

</div>

**🗂 Tüm Örnekler (ünitelere göre)**

<details>
<summary><b>Ünite 1 · Temeller</b> · 4 uygulama</summary>
<br/>

<div align="center">

<table>
  <tr>
    <td align="center" width="25%"><img src="docs/screenshots/diceroller.png" width="170" alt="Dice Roller" /><br/><sub><b>🎲 Dice Roller</b></sub></td>
    <td align="center" width="25%"><img src="docs/screenshots/lemonade.png" width="170" alt="Lemonade" /><br/><sub><b>🍋 Lemonade</b></sub></td>
    <td align="center" width="25%"><img src="docs/screenshots/tipcalculator.png" width="170" alt="Tip Calculator" /><br/><sub><b>💸 Tip Calculator</b></sub></td>
    <td align="center" width="25%"><img src="docs/screenshots/affirmations.png" width="170" alt="Affirmations" /><br/><sub><b>💬 Affirmations</b></sub></td>
  </tr>
</table>

</div>

</details>

<details>
<summary><b>Ünite 2 · UI ve Layout'lar</b> · 3 uygulama</summary>
<br/>

<div align="center">

<table>
  <tr>
    <td align="center" width="25%"><img src="docs/screenshots/woof.png" width="170" alt="Woof" /><br/><sub><b>🐶 Woof</b></sub></td>
    <td align="center" width="25%"><img src="docs/screenshots/superheroes.png" width="170" alt="Superheroes" /><br/><sub><b>🦸 Superheroes</b></sub></td>
    <td align="center" width="25%"><img src="docs/screenshots/artspace.png" width="170" alt="Art Space" /><br/><sub><b>🖼️ Art Space</b></sub></td>
  </tr>
</table>

</div>

</details>

<details>
<summary><b>Ünite 3 · Mimari ve State</b> · 3 uygulama</summary>
<br/>

<div align="center">

<table>
  <tr>
    <td align="center" width="25%"><img src="docs/screenshots/dessertclicker.png" width="170" alt="Dessert Clicker" /><br/><sub><b>🍰 Dessert Clicker</b></sub></td>
    <td align="center" width="25%"><img src="docs/screenshots/counter.png" width="170" alt="Counter Exercise" /><br/><sub><b>➕ Counter Exercise</b></sub></td>
    <td align="center" width="25%"><img src="docs/screenshots/unscramble.png" width="170" alt="Unscramble" /><br/><sub><b>🔤 Unscramble</b></sub></td>
  </tr>
</table>

</div>

</details>

<details>
<summary><b>Ünite 4 · Navigasyon</b> · 4 uygulama</summary>
<br/>

<div align="center">

<table>
  <tr>
    <td align="center" width="25%"><img src="docs/screenshots/cupcake.png" width="170" alt="Cupcake" /><br/><sub><b>🧁 Cupcake</b></sub></td>
    <td align="center" width="25%"><img src="docs/screenshots/lunchtray.png" width="170" alt="Lunch Tray" /><br/><sub><b>🥗 Lunch Tray</b></sub></td>
    <td align="center" width="25%"><img src="docs/screenshots/reply.png" width="170" alt="Reply" /><br/><sub><b>📧 Reply</b></sub></td>
    <td align="center" width="25%"><img src="docs/screenshots/sports2.png" width="170" alt="Sports" /><br/><sub><b>⚽ Sports</b></sub></td>
  </tr>
  <tr>
  <td align="center" width="25%"><img src="docs/screenshots/sports.png" width="170" alt="Sports" /><br/><sub><b>⚽ Sports Tablet</b></sub></td>
    </tr>
</table>

</div>

</details>

<details>
<summary><b>Ünite 5 · Listeler ve Animasyonlar</b> · 6 uygulama</summary>
<br/>

<div align="center">

<table>
  <tr>
    <td align="center" width="25%"><img src="docs/screenshots/lazygrid.png" width="170" alt="LazyGrid Practice" /><br/><sub><b>🔲 LazyGrid Practice</b></sub></td>
    <td align="center" width="25%"><img src="docs/screenshots/lazyliststate.png" width="170" alt="LazyListState Practice" /><br/><sub><b>📜 LazyListState Practice</b></sub></td>
    <td align="center" width="25%"><img src="docs/screenshots/advancedlazylists.png" width="170" alt="Advanced Lazy Lists" /><br/><sub><b>🧬 Advanced Lazy Lists</b></sub></td>
    <td align="center" width="25%"><img src="docs/screenshots/orderhistory.png" width="170" alt="Order History" /><br/><sub><b>🧾 Order History</b></sub></td>
  </tr>
  <tr>
    <td align="center" width="25%"><img src="docs/screenshots/dynamiclistanimations.png" width="170" alt="Dynamic List Animations" /><br/><sub><b>✨ Dynamic List Animations</b></sub></td>
    <td align="center" width="25%"><img src="docs/screenshots/racetracker.png" width="170" alt="Race Tracker" /><br/><sub><b>🏁 Race Tracker</b></sub></td>
  </tr>
</table>

</div>

</details>

<details>
<summary><b>Ünite 6 · Veri ve Kalıcılık</b> · 7 uygulama</summary>
<br/>

<div align="center">

<table>
  <tr>
    <td align="center" width="25%"><img src="docs/screenshots/marsphotos.png" width="170" alt="Mars Photos" /><br/><sub><b>🪐 Mars Photos</b></sub></td>
    <td align="center" width="25%"><img src="docs/screenshots/amphibians.png" width="170" alt="Amphibians" /><br/><sub><b>🐸 Amphibians</b></sub></td>
    <td align="center" width="25%"><img src="docs/screenshots/sqlbasics.png" width="170" alt="SQL Basics" /><br/><sub><b>🗄️ SQL Basics</b></sub></td>
    <td align="center" width="25%"><img src="docs/screenshots/busschedule.png" width="170" alt="Bus Schedule" /><br/><sub><b>🚌 Bus Schedule</b></sub></td>
  </tr>
  <tr>
    <td align="center" width="25%"><img src="docs/screenshots/inventory.png" width="170" alt="Inventory" /><br/><sub><b>📦 Inventory</b></sub></td>
    <td align="center" width="25%"><img src="docs/screenshots/dessertrelease.png" width="170" alt="Dessert Release" /><br/><sub><b>🍨 Dessert Release</b></sub></td>
    <td align="center" width="25%"><img src="docs/screenshots/flightsearch.png" width="170" alt="Flight Search" /><br/><sub><b>✈️ Flight Search</b></sub></td>
  </tr>
</table>

</div>

</details>

<details>
<summary><b>Ünite 7 · WorkManager</b> · 2 uygulama</summary>
<br/>

<div align="center">

<table>
  <tr>
    <td align="center" width="25%"><img src="docs/screenshots/bluromatic.png" width="170" alt="Bluromatic" /><br/><sub><b>🌫️ Bluromatic</b></sub></td>
    <td align="center" width="25%"><img src="docs/screenshots/waterme.png" width="170" alt="Water Me" /><br/><sub><b>💧 Water Me</b></sub></td>
  </tr>
</table>

</div>

</details>

<details>
<summary><b>Ünite 8 · View ve Interop</b> · 1 uygulama</summary>
<br/>

<div align="center">

<table>
  <tr>
    <td align="center" width="25%"><img src="docs/screenshots/juicetracker.png" width="170" alt="Juice Tracker" /><br/><sub><b>🧃 Juice Tracker</b></sub></td>
  </tr>
</table>

</div>

</details>

<details>
<summary><b>Sandbox</b> · 1 örnek</summary>
<br/>

<div align="center">

<table>
  <tr>
    <td align="center" width="25%"><img src="docs/screenshots/sandbox.png" width="170" alt="Exercise Lab" /><br/><sub><b>🧪 Exercise Lab</b></sub></td>
  </tr>
</table>

</div>

</details>


---

## 🧠 Mimari ve Temel Özellikler

### 🧭 Üst düzey veri akışı

```mermaid
flowchart LR
    subgraph UI["🎨 UI Katmanı (Jetpack Compose + M3)"]
        S[Composable Ekranlar]
    end
    subgraph VM["🧠 State Holder"]
        V[ViewModel]
    end
    subgraph DATA["💾 Veri Katmanı"]
        R[Repository]
        ROOM[(Room / SQLite)]
        NET[Retrofit + Kotlinx Serialization]
        DS[(Preferences DataStore)]
    end
    WM[⚙️ WorkManager]

    S -- "olaylar ⬆" --> V
    V -- "StateFlow ⬇" --> S
    V --> R
    R --> ROOM
    R --> NET
    R --> DS
    WM --> R
```

### ✨ Öne çıkan özellikler

| Alan | Kod tabanında neler var? |
|---|---|
| 🧩 **MVVM + UDF** | State'i `ViewModel` yönetir ve `StateFlow` olarak dışarı açar; UI olayları yukarı gönderir, state aşağı akar. Ekranlar state tutmaz ve preview'lanabilir. |
| 🌊 **StateFlow / SharedFlow** | UI state'i için `StateFlow`, tek seferlik olaylar için `SharedFlow`; Compose içinde yaşam döngüsüne duyarlı toplama. |
| 🧼 **Clean Architecture + Repository** | UI, state holder'lar ve veri kaynakları arasında net ayrım. Repository, verinin Room'dan, ağdan ya da DataStore'dan geldiğini gizler. |
| 💉 **`AppContainer` DI** | Bağımlılıksız, hafif manuel enjeksiyon. Anlaşılması ve test edilmesi kolay; Hilt'e güzel bir geçiş basamağı. |
| 🗄️ **Room** | `Flow` döndüren DAO'lar, asset tabanlı ve önceden doldurulmuş veritabanları, Repository destekli CRUD. |
| ⚙️ **DataStore** | Kalıcı kullanıcı ayarları için Preferences DataStore; Flight Search uygulamasında Room ile birlikte kullanılır. |
| 🌐 **Ağ katmanı** | Retrofit 2 + Kotlinx Serialization + Coil; `Loading / Success / Error` için sealed UI state'leri. |
| ⏱️ **WorkManager** | Unique work zincirleri, kısıtlamalar ve periyodik işler; sonuçları kaydetmek için MediaStore. |
| 🧭 **Navigasyon** | Navigation Compose, **type-safe rotalar (2.8+)**, `BackHandler` ve `AndroidViewBinding` ile barındırılan XML nav graph'ları. |
| 🎨 **Tema** | Light/Dark tema ve Dynamic Color ile Material 3. |
| 📐 **Uyarlanabilir UI** | Pencere boyutuna uyum sağlayan layout'lar ve `SaveableStateHolder` ile örnek bazında state koruma. |
| ✍️ **Özel çizim ve hareket** | Özel `Canvas` çizimleri ve özel animasyonlar. |

<details>
<summary><b>📌 Örnek: <code>ViewModel</code> + <code>StateFlow</code> ile UDF</b></summary>

```kotlin
data class GameUiState(
    val currentWord: String = "",
    val userGuess: String = "",
    val isGuessWrong: Boolean = false,
    val score: Int = 0,
)

class GameViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(GameUiState())
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()

    fun onGuessChanged(guess: String) {
        _uiState.update { it.copy(userGuess = guess, isGuessWrong = false) }
    }
}

@Composable
fun GameScreen(viewModel: GameViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    GameContent(
        state = uiState,
        onGuessChanged = viewModel::onGuessChanged, // olaylar yukarı akar ⬆
    )                                               // state aşağı akar ⬇
}
```

</details>

<details>
<summary><b>📌 Örnek: Repository + <code>AppContainer</code> DI</b></summary>

```kotlin
interface AppContainer {
    val itemsRepository: ItemsRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    override val itemsRepository: ItemsRepository by lazy {
        OfflineItemsRepository(InventoryDatabase.getDatabase(context).itemDao())
    }
}

class PlaygroundApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}
```

</details>

<details>
<summary><b>📌 Örnek: <code>Flow</code> döndüren Room DAO</b></summary>

```kotlin
@Dao
interface ItemDao {

    @Query("SELECT * FROM items ORDER BY name ASC")
    fun getAllItems(): Flow<List<Item>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: Item)

    @Update
    suspend fun update(item: Item)

    @Delete
    suspend fun delete(item: Item)
}
```

</details>

<details>
<summary><b>📌 Örnek: Type-safe navigasyon (Navigation 2.8+)</b></summary>

```kotlin
@Serializable object Start
@Serializable data class Flavor(val quantity: Int)
@Serializable object Summary

NavHost(navController = navController, startDestination = Start) {
    composable<Start> {
        StartOrderScreen(onNext = { qty -> navController.navigate(Flavor(qty)) })
    }
    composable<Flavor> { backStackEntry ->
        val args: Flavor = backStackEntry.toRoute()
        SelectFlavorScreen(quantity = args.quantity)
    }
    composable<Summary> { OrderSummaryScreen() }
}
```

</details>

<details>
<summary><b>📌 Örnek: WorkManager unique work zinciri + kısıtlamalar</b></summary>

```kotlin
val constraints = Constraints.Builder()
    .setRequiresBatteryNotLow(true)
    .build()

var continuation = workManager.beginUniqueWork(
    IMAGE_MANIPULATION_WORK_NAME,
    ExistingWorkPolicy.REPLACE,
    OneTimeWorkRequest.from(CleanupWorker::class.java),
)

val blurRequest = OneTimeWorkRequestBuilder<BlurWorker>()
    .setInputData(createInputDataForWorkRequest(blurLevel, imageUri))
    .build()

val saveRequest = OneTimeWorkRequestBuilder<SaveImageToFileWorker>()
    .setConstraints(constraints)
    .addTag(TAG_OUTPUT)
    .build()

continuation
    .then(blurRequest)
    .then(saveRequest)
    .enqueue()
```

</details>

---

## 🧰 Teknoloji Yığını

| Kategori | Teknolojiler |
|---|---|
| 🎨 **UI ve Tasarım** | %100 Jetpack Compose · Material Design 3 · Dynamic Theming (Light/Dark) · Özel Canvas · Özel Animasyonlar · Uyarlanabilir Layout'lar (`SaveableStateHolder`) |
| 🏗️ **Mimari ve State** | MVVM · Unidirectional Data Flow · `StateFlow` · `SharedFlow` · `ViewModel` · Clean Architecture · Repository Pattern · `AppContainer` DI |
| 🧭 **Navigasyon** | Navigation Compose · Type-Safe Navigation (2.8+) · `BackHandler` · `AndroidViewBinding` interoperability |
| 💾 **Veri ve Depolama** | Room (Asset DB'ler, Önceden Doldurulmuş DB'ler, DAO, Flow) · Preferences DataStore |
| 🌐 **Ağ ve Asenkron** | Retrofit 2 · Kotlinx Serialization · Kotlin Coroutines · Coil |
| ⚙️ **Arka Plan İşleri** | WorkManager · Unique Work Zincirleri · Constraints · MediaStore API |
| 🔁 **Interoperability** | `ComposeView` · `AndroidView` · `AndroidViewBinding` |
| 🧪 **Test** | JUnit 4 · Compose UI Test Rule'ları · `TestNavHostController` · DAO Kalıcılık Testleri |
| 🛠️ **Derleme** | Gradle (Kotlin DSL) · Version Catalog (`libs.versions.toml`) · Min SDK 24 · Target SDK 37 |

---

## 📱 Etkileşimli Uygulama Kataloğu

> [!TIP]
> **8 ünitede 30 örnek uygulama ve Exercise Lab sandbox'ı (toplam 31 örnek).** Her örneğin `samples/` altında kendi klasörü, kaynak kodu ve bir `*_study_notes.md` dosyası vardır.

| 📚 Ünite | 📱 Örnek | 🧩 Odak |
|:---:|:---:|:---|
| 8 | 31 | Temeller → Mimari → Navigasyon → Listeler → Veri → Arka Plan İşleri → Interop |

<br/>

### ![Ünite 1](https://img.shields.io/badge/%C3%9Cnite_1-Temeller-4285F4?style=for-the-badge&logo=android&logoColor=white)

> Compose temelleri: composable'lar, state ve ilk etkileşimli arayüzler.

| | Uygulama | Neleri gösteriyor? | Önemli kavramlar |
|:---:|---|---|---|
| 🎲 | **Dice Roller** | Butonla çalışan, görseli değişen zar | `remember`, `mutableStateOf`, `Image`, preview'lar |
| 🍋 | **Lemonade** | Dokunarak ilerleyen bir durum makinesi | State odaklı UI, `when`, koşullu çizim |
| 💸 | **Tip Calculator** | Kullanıcı girdisinden anlık bahşiş hesabı | `TextField`, state hoisting, `NumberFormat` |
| 💬 | **Affirmations** | Kaydırılabilir kart listesi | `LazyColumn`, `Card`, data class'lar, kaynaklar |

### ![Ünite 2](https://img.shields.io/badge/%C3%9Cnite_2-UI_ve_Layout%27lar-34A853?style=for-the-badge&logo=materialdesign&logoColor=white)

> Layout'lar, Material tema ve composable cilası.

| | Uygulama | Neleri gösteriyor? | Önemli kavramlar |
|:---:|---|---|---|
| 🐶 | **Woof** | Genişletilebilir öğelere sahip temalı evcil hayvan listesi | Material 3 tema, `animateContentSize`, shape ve typography |
| 🦸 | **Superheroes** | Üst uygulama çubuklu kart listesi | `Scaffold`, `TopAppBar`, `LazyColumn` |
| 🖼️ | **Art Space** | Gezinme butonlu basit galeri | `Column`/`Row`/`Box`, `Surface`, state |

### ![Ünite 3](https://img.shields.io/badge/%C3%9Cnite_3-Mimari_ve_State-FBBC04?style=for-the-badge&logo=kotlin&logoColor=black)

> `ViewModel`, state ve yaşam döngüsüne duyarlı arayüz.

| | Uygulama | Neleri gösteriyor? | Önemli kavramlar |
|:---:|---|---|---|
| 🍰 | **Dessert Clicker** | Tıklamayla gelir ve tatlı ilerlemesi olan oyun | State, yaşam döngüsü, `ViewModel`, paylaşım intent'i |
| ➕ | **Counter Exercise** | Odaklı bir state alıştırması | State hoisting, `rememberSaveable` |
| 🔤 | **Unscramble** | Kelime bulmaca oyunu | `ViewModel`, `StateFlow`, UDF, `AlertDialog` |

### ![Ünite 4](https://img.shields.io/badge/%C3%9Cnite_4-Navigasyon-EA4335?style=for-the-badge&logo=android&logoColor=white)

> Çok ekranlı uygulamalar ve uyarlanabilir navigasyon.

| | Uygulama | Neleri gösteriyor? | Önemli kavramlar |
|:---:|---|---|---|
| 🧁 | **Cupcake** | Çok adımlı sipariş akışı | **Type-safe Navigation**, paylaşılan `ViewModel`, paylaşım intent'i |
| 🥗 | **Lunch Tray** | Çok ekranlı yemek sipariş akışı | `NavHost`, back stack kontrolü, paylaşılan sipariş state'i |
| 📧 | **Reply** | Uyarlanabilir e-posta istemcisi | Pencere boyutu sınıfları, navigation rail/drawer, liste-detay |
| ⚽ | **Sports** | Uyarlanabilir liste/detay uygulaması | `BackHandler`, uyarlanabilir layout'lar |

### ![Ünite 5](https://img.shields.io/badge/%C3%9Cnite_5-Listeler_ve_Animasyonlar-9C27B0?style=for-the-badge&logo=jetpackcompose&logoColor=white)

> Lazy layout'lar, kaydırma state'i, hareket ve eşzamanlılık.

| | Uygulama | Neleri gösteriyor? | Önemli kavramlar |
|:---:|---|---|---|
| 🔲 | **LazyGrid Practice** | Izgara tabanlı layout'lar | `LazyVerticalGrid`, adaptive/fixed hücreler |
| 📜 | **LazyListState Practice** | Kaydırma konumunu okuma ve kontrol etme | `LazyListState`, `derivedStateOf`, başa dön |
| 🧬 | **Advanced Lazy Lists** | Optimize edilmiş, zengin yapılı listeler | Sticky header'lar, öğe `key`, `contentType` |
| 🧾 | **Order History** | Geçmiş/liste senaryosu | Listeler, öğe state'i |
| ✨ | **Dynamic List Animations** | Animasyonlu öğe ekleme/silme/sıralama | `animateItem`, `AnimatedVisibility` |
| 🏁 | **Race Tracker** | Eşzamanlı ilerleme simülasyonu | Coroutine'ler, `LaunchedEffect`, state holder'lar |

### ![Ünite 6](https://img.shields.io/badge/%C3%9Cnite_6-Veri_ve_Kal%C4%B1c%C4%B1l%C4%B1k-00ACC1?style=for-the-badge&logo=sqlite&logoColor=white)

> Ağ, SQLite/Room ve tercihler.

| | Uygulama | Neleri gösteriyor? | Önemli kavramlar |
|:---:|---|---|---|
| 🪐 | **Mars Photos** | Uzaktan görsel çekme ve gösterme | **Retrofit**, Kotlinx Serialization, **Coil**, sealed UI state'leri |
| 🐸 | **Amphibians** | Ağ destekli liste/detay uygulaması | Repository, Retrofit, hata yönetimi |
| 🗄️ | **SQL Basics** | Temel SQL kavramları ve sorguları | SQLite temelleri, sorgu pratiği |
| 🚌 | **Bus Schedule** | Önceden doldurulmuş veritabanını okuma | **Room**, asset DB, DAO + `Flow` |
| 📦 | **Inventory** | Çevrimdışı kalıcılıkla tam CRUD | **Room**, Repository, `AppContainer` DI |
| 🍨 | **Dessert Release** | Bir arayüz tercihini kalıcı hale getirme | **Preferences DataStore** |
| ✈️ | **Flight Search** | Aranabilir havalimanı/uçuş uygulaması | **Asset DB + DataStore**, `Flow`, arama UX'i |

### ![Ünite 7](https://img.shields.io/badge/%C3%9Cnite_7-WorkManager-FF6D00?style=for-the-badge&logo=android&logoColor=white)

> Güvenilir arka plan çalıştırma.

| | Uygulama | Neleri gösteriyor? | Önemli kavramlar |
|:---:|---|---|---|
| 🌫️ | **Bluromatic** | İş hattı olarak görsel bulanıklaştırma | **Work chain'ler**, unique work, kısıtlamalar, `MediaStore` |
| 💧 | **Water Me** | Tekrarlayan hatırlatıcılar | **Periyodik iş**, bildirimler |

### ![Ünite 8](https://img.shields.io/badge/%C3%9Cnite_8-View_ve_Interop-6750A4?style=for-the-badge&logo=android&logoColor=white)

> Klasik View'lar ile Compose arasında her iki yönde köprü kurma.

| | Uygulama | Neleri gösteriyor? | Önemli kavramlar |
|:---:|---|---|---|
| 🧃 | **Juice Tracker** | **Compose içinde View'lar** ve **View'larla Compose** | `ComposeView`, `AndroidView`, `AndroidViewBinding` |

### ![Sandbox](https://img.shields.io/badge/Sandbox-Deneyler-607D8B?style=for-the-badge&logo=beaker&logoColor=white)

| | Uygulama | Neleri gösteriyor? | Önemli kavramlar |
|:---:|---|---|---|
| 🧪 | **Exercise Lab** | Deney ve pratik için serbest alan | Her şey serbest |

---

## 🔁 View ve Compose Birlikte Çalışabilirlik Vitrini

Üretimdeki çoğu uygulama ilk günden %100 Compose değildir. **Ünite 8 (Juice Tracker)**, geçişi ve karışık kullanımı kademeli olarak, **her iki yönde** nasıl yapacağını gösterir.

```mermaid
flowchart TB
    subgraph XML["🧱 Klasik View Sistemi"]
        RV[RecyclerView öğesi]
        FRAG[Fragment + XML Nav Graph]
    end
    subgraph COMPOSE["🎨 Jetpack Compose"]
        UI[Composable ekran]
        VIEW[Native View widget'ları]
    end

    RV -- "ComposeView" --> UI
    FRAG -- "AndroidViewBinding" --> UI
    UI -- "AndroidView" --> VIEW
```

| Yön | API | Ne için kullanılıyor? | Nerede? |
|---|---|---|---|
| 🧱 ➜ 🎨 **View'ların içinde Compose** | `ComposeView` | Her XML `RecyclerView` öğesinin içinde Compose UI çizmek | Juice listesi |
| 🎨 ➜ 🧱 **Compose içinde View'lar** | `AndroidView` | Bir composable içine `RatingBar`, `Spinner` ve `TextView` gömmek | Juice giriş formu |
| 🎨 ➜ 🧱 **Compose içinde XML navigasyon** | `AndroidViewBinding` | Bir composable içinde XML Navigation Graph ve Fragment'leri barındırmak | Uygulama kabuğu |

### 1️⃣ `ComposeView`: `RecyclerView` içinde Compose

```kotlin
class JuiceViewHolder(
    private val composeView: ComposeView,
) : RecyclerView.ViewHolder(composeView) {

    init {
        // RecyclerView için önerilir: view tree yaşam döngüsü bitince temizle.
        composeView.setViewCompositionStrategy(
            ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed
        )
    }

    fun bind(juice: Juice) {
        composeView.setContent {
            JuiceTrackerTheme {
                JuiceListItem(juice = juice)
            }
        }
    }
}
```

### 2️⃣ `AndroidView`: Compose içinde native `RatingBar`

```kotlin
@Composable
fun JuiceRatingBar(
    rating: Float,
    onRatingChanged: (Float) -> Unit,
    modifier: Modifier = Modifier,
) {
    AndroidView(
        modifier = modifier,
        factory = { context ->
            // Bir kez çalışır: View'ı oluştur.
            RatingBar(context).apply {
                numStars = 5
                stepSize = 1f
            }
        },
        update = { ratingBar ->
            // Her recomposition'da çalışır: Compose state'ini View'a aktar.
            ratingBar.rating = rating
            ratingBar.setOnRatingBarChangeListener { _, value, fromUser ->
                if (fromUser) onRatingChanged(value)
            }
        },
    )
}
```

### 3️⃣ `AndroidViewBinding`: Compose içinde XML Navigation Graph

```xml
<!-- res/layout/fragment_container_host.xml -->
<androidx.fragment.app.FragmentContainerView
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/nav_host_fragment"
    android:name="androidx.navigation.fragment.NavHostFragment"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    app:defaultNavHost="true"
    app:navGraph="@navigation/nav_graph" />
```

```kotlin
@Composable
fun LegacyNavHost(modifier: Modifier = Modifier) {
    AndroidViewBinding(
        factory = FragmentContainerHostBinding::inflate,
        modifier = modifier,
    )
}
```

> [!IMPORTANT]
> `AndroidViewBinding`, **View Binding**'in etkin olmasını ve `androidx.compose.ui:ui-viewbinding` bağımlılığını gerektirir. Bir `FragmentContainerView` barındırırken, host Activity'nin bir `FragmentActivity` (örneğin `AppCompatActivity`) olması gerekir.

> [!TIP]
> **Pratik kural:** *Tek doğruluk kaynağını* bir `ViewModel` içinde tut ve iki dünyanın (View'lar ve Compose) da aynı state'i gözlemlemesini sağla. Bu, geçiş sırasında birbiriyle yarışan iki state oluşmasını önler.

---

## 🚀 Başlarken ve Kurulum

### ✅ Gereksinimler

| Gereksinim | Sürüm |
|---|---|
| 🧠 **Android Studio** | En son kararlı sürüm (Compose araçları ve preview'lar önerilir) |
| ☕ **JDK** | 17 veya üstü (Android Studio ile gelen JBR yeterlidir) |
| 🤖 **Android SDK** | SDK Manager üzerinden **37** platformu kurulu olmalı |
| 📱 **Cihaz / Emülatör** | API **24+** |

### 📥 Kurulum

**1. Depoyu klonla**

```bash
git clone https://github.com/UmutCanAldirmaz/JetpackComposePlayground.git
cd JetpackComposePlayground
```

**2. Android Studio'da aç**

1. **Android Studio**'yu başlat → **File ▸ Open…**
2. `JetpackComposePlayground` klasörünü seç.
3. **Gradle sync** işleminin bitmesini bekle.

**3. Uygulamayı çalıştır**

1. Bir emülatör veya bağlı cihaz seç (API 24+).
2. `app` çalıştırma yapılandırmasını seç.
3. **▶ Run**'a bas (`Shift + F10`).

**Ya da komut satırını kullan:**

```bash
# macOS / Linux
./gradlew installDebug

# Windows
gradlew.bat installDebug
```

> [!NOTE]
> İlk Gradle sync bağımlılıkları indirir ve birkaç dakika sürebilir. Android SDK Platform 37'nin kurulu olduğundan emin ol; aksi halde sync, eksik platform hatası verir.

> [!WARNING]
> Bazı örnekler internet bağlantısı gerektirir (örneğin **Mars Photos** ve **Amphibians**). Ağ destekli örnekler, çevrimdışıyken tasarım gereği bir hata durumu gösterir.

---

## 📂 Proje Yapısı

Tüm örnek uygulamalar tek bir `samples/` paketinde yaşar; her uygulama kendi içinde bağımsız bir klasördür. Örnekleri başlatan kabuk `home/` içinde, ortak Material 3 teması ise `core/theme/` içinde bulunur.

```text
app/src/
├── main/
│   ├── AndroidManifest.xml
│   ├── assets/                                  # Önceden doldurulmuş / asset Room veritabanları
│   ├── res/
│   └── kotlin+java/com/example/jetpackcomposeplayground/
│       ├── MainActivity.kt                      # Tek Activity girişi
│       ├── PlaygroundApplication.kt             # Application sınıfı (global kurulum)
│       │
│       ├── core/theme/                          # 🎨 Ortak Material 3 teması
│       │   ├── Color.kt
│       │   ├── Theme.kt
│       │   └── Type.kt
│       │
│       ├── home/                                # 🏠 Playground kabuğu ve başlatıcı
│       │   ├── AppRoute.kt
│       │   ├── HomeScreen.kt
│       │   ├── PracticeApp.kt
│       │   └── PracticeAppCatalog.kt
│       │
│       └── samples/                             # 📱 Her örnek için bir klasör (31 adet)
│           ├── advancedlazylists/                   # Ünite 5
│           ├── affirmations/                        # Ünite 1
│           ├── amphibians/                          # Ünite 6
│           ├── artspace/                            # Ünite 2
│           ├── bluromatic/                          # Ünite 7
│           ├── busschedule/                         # Ünite 6
│           ├── counter/ui/                          # Ünite 3
│           ├── cupcake/                             # Ünite 4
│           ├── dessertclicker/                      # Ünite 3
│           ├── dessertrelease/                      # Ünite 6
│           ├── diceroller/                          # Ünite 1
│           ├── dynamiclistanimations/               # Ünite 5
│           ├── flightsearch/                        # Ünite 6
│           ├── inventory/                           # Ünite 6
│           ├── juicetracker/                        # Ünite 8
│           ├── lazygrid/                            # Ünite 5
│           ├── lazyliststate/                       # Ünite 5
│           ├── lemonade/                            # Ünite 1
│           ├── lunchtray/                           # Ünite 4
│           ├── marsphotos/                          # Ünite 6
│           ├── orderhistory/                        # Ünite 5
│           ├── racetracker/                         # Ünite 5
│           ├── reply/                               # Ünite 4
│           ├── sandbox/                             # Sandbox
│           ├── sports/                              # Ünite 4
│           ├── sqlbasics/                           # Ünite 6
│           ├── superheroes/                         # Ünite 2
│           ├── tipcalculator/                       # Ünite 1
│           ├── unscramble/                          # Ünite 3
│           ├── waterme/                             # Ünite 7
│           └── woof/                                # Ünite 2
│
├── androidTest/…/samples/                       # 🤖 Enstrümantasyon testleri (bkz. Test Stratejisi)
└── test/…/samples/                              # 🧮 Yerel birim testleri (bkz. Test Stratejisi)
```

| Paket | Sorumluluk |
|---|---|
| 🎨 **`core/theme`** | Material 3 renkleri, tipografi ve uygulama teması (Light/Dark + Dynamic Color). |
| 🏠 **`home`** | Playground kabuğu: uygulama düzeyinde rotalar (`AppRoute`), başlatıcı ekran (`HomeScreen`), kök composable (`PracticeApp`) ve tüm örneklerin kayıt defteri (`PracticeAppCatalog`). |
| 📱 **`samples`** | 31 örnek (30 uygulama + Exercise Lab sandbox). Her klasör kendi ekranlarını, state holder'larını, veri katmanını ve çalışma notlarını barındırır. |

> [!TIP]
> Her örnek kendi paketinde izole olduğu için, birini diğerlerine dokunmadan okuyabilir, kopyalayabilir veya silebilirsin.


---

## 📝 Çalışma Notları ve Dokümantasyon

Her örnek klasörü, o uygulamanın neyi öğrettiğini özetleyen kompakt bir **`*_study_notes.md`** dosyası içerir.

```text
samples/<örnek>/
├── …                              # ekranlar, ViewModel'ler, veri katmanı
└── <örnek>_study_notes.md         👈 kavramlar, tuzaklar, önemli çıkarımlar
```

**Tipik bir çalışma notunun kapsamı:**

| Bölüm | İçerik |
|---|---|
| 🎯 **Amaç** | Uygulamanın neyi öğretmeyi hedeflediği |
| 🧠 **Temel kavramlar** | Tanımlar ve zihinsel modeller |
| 🧩 **Kod incelemesi** | Uygulamanın önemli kısımları ve açıklamaları |
| ⚠️ **Tuzaklar** | Sık yapılan hatalar ve nasıl kaçınılacağı |
| ✅ **Çıkarımlar** | Hatırlanacaklar için kısa bir kontrol listesi |

> [!TIP]
> **Önerilen çalışma yöntemi:** önce çalışma notunu oku → uygulamayı çalıştır → kaynak kodu oku → ardından örneğin daha küçük bir versiyonunu **Exercise Lab**'de ezberden yeniden yaz.

---

## 🧪 Test Stratejisi

Testler, doğruladıkları kodun yanında yer alır ve `samples/` paket düzenini yansıtır. Proje, **yerel JVM birim testlerini** ve cihaz ya da emülatörde çalışan **enstrümantasyon testlerini** bir arada kullanır.

### 🗺️ Kapsam haritası

| Örnek | 🧮 Birim | 🤖 Enstrümantasyon | Neler doğrulanıyor? |
|---|:---:|:---:|---|
| 🐸 **Amphibians** | ✅ | | **Fake** API/veri kaynaklarıyla `ViewModel` ve Repository mantığı |
| 🪐 **Mars Photos** | ✅ | | Fake'lerle `ViewModel` UI state'leri ve ağ Repository'si |
| 🏁 **Race Tracker** | ✅ | | Yarış katılımcısı mantığı ve ilerleme kuralları |
| 💸 **Tip Calculator** | ✅ | | Saf bahşiş hesaplama mantığı |
| 🔤 **Unscramble** | ✅ | | `GameViewModel` oyun mantığı (`WordsData` ile) |
| 🧁 **Cupcake** | | ✅ | Sipariş ekranı için Compose UI testleri ve **navigasyon** testleri |
| ✈️ **Flight Search** | | ✅ | UI testleri, **DAO kalıcılık** testleri ve UI + kalıcılık entegrasyonu |
| 📦 **Inventory** | | ✅ | Room **DAO** testleri (`ItemDaoTest`) |
| 🌫️ **Bluromatic** | | ✅ | **WorkManager** worker enstrümantasyon testi |

<details>
<summary><b>📁 Test dosyası envanteri</b></summary>

```text
androidTest/…/samples/                          # 🤖 Enstrümantasyon
├── bluromatic/
│   └── WorkManagerWorkerInstrumentationTest
├── cupcake/test/
│   ├── ComposeRuleExtensions.kt                # ortak test yardımcıları
│   ├── CupcakeOrderScreenTest
│   ├── CupcakeScreenNavigationTest
│   └── ScreenAssertions.kt                     # ortak assertion'lar
├── flightsearch/
│   ├── FlightSearchDaoPersistenceTest
│   └── test/
│       ├── FlightSearchPersistenceUiTest
│       └── FlightSearchUiTest
├── inventory/
│   └── ItemDaoTest
└── ExampleInstrumentedTest

test/…/samples/                                 # 🧮 Yerel birim testleri
├── amphibians/
│   ├── fake/                                   # FakeAmphibiansApiService, FakeAmphibiansRepository, FakeDataSource
│   ├── rules/                                  # TestDispatcherRule
│   ├── AmphibiansViewModelTest
│   └── NetworkAmphibiansRepositoryTest
├── marsphotos/
│   ├── fake/                                   # FakeMarsApiService, FakeNetworkMarsPhotosRepository, FakeDataSource
│   ├── rules/
│   ├── MarsViewModelTest
│   └── NetworkMarsRepositoryTest
├── racetracker/
│   └── RaceParticipantTest
├── tipcalculator/
│   └── TipCalculatorTest
├── unscramble/
│   ├── data/WordsData.kt
│   └── ui/test/GameViewModelTest
└── ExampleUnitTest
```

</details>

### 🧰 Kullanılan kalıplar

| Kalıp | Neden önemli? |
|---|---|
| 🎭 **Mock yerine Fake'ler** | `Fake*ApiService` ve `Fake*Repository` sınıfları ağ testlerini hızlı, deterministik ve okunabilir tutar. |
| ⏱️ **`TestDispatcherRule`** | `Dispatchers.Main`'i değiştiren bir JUnit rule'u; coroutine tabanlı `ViewModel`'lerin test edilebilmesini sağlar. |
| 🧩 **Ortak test yardımcıları** | `ComposeRuleExtensions.kt` ve `ScreenAssertions.kt`, Compose testleri arasındaki tekrarı ortadan kaldırır. |
| 🗄️ **In-memory / kalıcılık testleri** | DAO testleri, üretim verisine dokunmadan gerçek Room davranışını doğrular. |

### ▶️ Testleri çalıştırma

```bash
# 🧮 Yerel birim testleri (JVM, cihaz gerekmez)
./gradlew testDebugUnitTest

# 🎯 Tek bir birim test sınıfı
./gradlew testDebugUnitTest --tests "*TipCalculatorTest"

# 🤖 Enstrümantasyon testleri (bağlı cihaz veya emülatör gerekir)
./gradlew connectedDebugAndroidTest

# 🎯 Tek bir enstrümantasyon test sınıfı
./gradlew connectedDebugAndroidTest \
  -Pandroid.testInstrumentationRunnerArguments.class=com.example.jetpackcomposeplayground.samples.inventory.ItemDaoTest
```

> [!TIP]
> Android Studio'da bir test sınıfına veya `src/test` / `src/androidTest` klasörüne sağ tıklayıp **Run Tests**'i seçebilirsin.

> [!NOTE]
> Aşağıdaki kod parçaları, bu testlerin kullandığı *kalıpları* göstermek içindir. Birebir uygulamalar için ilgili test dosyalarını aç.

<details>
<summary><b>📌 Örnek: <code>TestDispatcherRule</code> + fake'li <code>ViewModel</code> testi</b></summary>

```kotlin
@OptIn(ExperimentalCoroutinesApi::class)
class TestDispatcherRule(
    private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher(),
) : TestWatcher() {
    override fun starting(description: Description) = Dispatchers.setMain(testDispatcher)
    override fun finished(description: Description) = Dispatchers.resetMain()
}

class MarsViewModelTest {

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @Test
    fun marsViewModel_getMarsPhotos_updatesUiStateToSuccess() = runTest {
        val viewModel = MarsViewModel(
            marsPhotosRepository = FakeNetworkMarsPhotosRepository()
        )

        assertTrue(viewModel.marsUiState is MarsUiState.Success)
    }
}
```

</details>

<details>
<summary><b>📌 Örnek: Compose UI testi</b></summary>

```kotlin
class CupcakeOrderScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun startOrderScreen_displaysOrderButtons() {
        composeTestRule.setContent {
            StartOrderScreen(onNext = {})
        }

        composeTestRule.onNodeWithText("Six Cupcakes").assertIsDisplayed()
    }
}
```

</details>

<details>
<summary><b>📌 Örnek: <code>TestNavHostController</code> ile navigasyon testi</b></summary>

```kotlin
class CupcakeScreenNavigationTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var navController: TestNavHostController

    @Before
    fun setUp() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current).apply {
                navigatorProvider.addNavigator(ComposeNavigator())
            }
            CupcakeApp(navController = navController)
        }
    }

    @Test
    fun cupcakeNavHost_verifyStartDestination() {
        val route = navController.currentBackStackEntry?.destination?.route
        assertTrue(route?.contains("Start") == true)
    }
}
```

</details>

<details>
<summary><b>📌 Örnek: DAO kalıcılık testi</b></summary>

```kotlin
@RunWith(AndroidJUnit4::class)
class ItemDaoTest {

    private lateinit var itemDao: ItemDao
    private lateinit var db: InventoryDatabase

    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        db = Room.inMemoryDatabaseBuilder(context, InventoryDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        itemDao = db.itemDao()
    }

    @After
    fun closeDb() = db.close()

    @Test
    fun daoInsert_insertsItemIntoDb() = runBlocking {
        itemDao.insert(Item(id = 1, name = "Apples", price = 10.0, quantity = 20))

        val items = itemDao.getAllItems().first()
        assertEquals("Apples", items[0].name)
    }
}
```

</details>

---

## 🤝 Katkıda Bulunma

Katkılar, issue'lar ve fikirler memnuniyetle karşılanır.

1. 🍴 Projeyi fork'la
2. 🌿 Özellik dalını oluştur: `git checkout -b feature/harika-ornek`
3. 💾 Değişikliklerini commit'le: `git commit -m "Harika bir örnek ekle"`
4. 📤 Dalı push'la: `git push origin feature/harika-ornek`
5. 🔃 Bir Pull Request aç

> [!NOTE]
> Yeni bir örnek eklerken `samples/` altında klasörünü oluştur, `home/PracticeAppCatalog.kt` içine kaydet, ilgili `*_study_notes.md` dosyasını ekle ve uygulamayı [Uygulama Kataloğu](#-etkileşimli-uygulama-kataloğu)'na ve `docs/screenshots/` altına bir ekran görüntüsü olarak ekle.

---

## 📄 Lisans ve Yazar

### Lisans

Bu proje **MIT Lisansı** ile lisanslanmıştır. Ayrıntılar için [`LICENSE`](LICENSE) dosyasına bak.

### 👨‍💻 Yazar

<div align="center">

**Umut Can Aldırmaz**

[![GitHub](https://img.shields.io/badge/GitHub-UmutCanAldirmaz-181717?style=for-the-badge&logo=github&logoColor=white)](https://github.com/UmutCanAldirmaz)
[![LinkedIn](https://img.shields.io/badge/LinkedIn-Ba%C4%9Flant%C4%B1_kur-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/umutcanaldirmaz/)

<br/>

**⭐ Bu playground bir şey öğrenmene ya da yayınlamana yardımcı olduysa, lütfen bir yıldız vermeyi düşün! ⭐**

<sub>Kotlin, Jetpack Compose ve Material Design 3 ile ❤️ ile geliştirildi.</sub>

</div>
