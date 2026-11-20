<div align="center">

# 🎮 JetpackComposePlayground

### The complete Android Kotlin + Jetpack Compose reference: 30+ real-world apps, study notes, and production-grade architecture patterns.

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
  <img src="https://img.shields.io/badge/License-MIT-yellow?style=for-the-badge" alt="License" />
</p>

<p>
  <img src="https://img.shields.io/badge/Architecture-MVVM%20%2B%20UDF-orange?style=flat-square" alt="MVVM + UDF" />
  <img src="https://img.shields.io/badge/Apps-31-success?style=flat-square" alt="31 apps" />
  <img src="https://img.shields.io/badge/Units-8-informational?style=flat-square" alt="8 units" />
  <img src="https://img.shields.io/badge/PRs-welcome-ff69b4?style=flat-square" alt="PRs welcome" />
</p>

<p>
  🇹🇷 <a href="README.md">Türkçe</a> &nbsp;·&nbsp; 🇬🇧 <b>English</b>
</p>

<img src="docs/screenshots/home.png" width="260" alt="JetpackComposePlayground home screen" />

<br/><br/>

**[Overview](#-project-overview) · [Screenshots](#-screenshots) · [Architecture](#-key-architecture--features) · [App Catalog](#-interactive-app-catalog) · [Interop Spotlight](#-view--compose-interoperability-spotlight) · [Getting Started](#-getting-started--installation) · [Testing](#-testing-strategy)**

</div>

---

## 📑 Table of Contents

- [Project Overview](#-project-overview)
- [Screenshots](#-screenshots)
- [Key Architecture & Features](#-key-architecture--features)
- [Tech Stack](#-tech-stack)
- [Interactive App Catalog](#-interactive-app-catalog)
- [View & Compose Interoperability Spotlight](#-view--compose-interoperability-spotlight)
- [Getting Started & Installation](#-getting-started--installation)
- [Project Structure](#-project-structure)
- [Study Notes & Documentation](#-study-notes--documentation)
- [Testing Strategy](#-testing-strategy)
- [Contributing](#-contributing)
- [License & Author](#-license--author)

---

## 🌟 Project Overview

**JetpackComposePlayground** is a single, cohesive Android application that brings the **entire Android Kotlin Compose curriculum (Unit 1 → Unit 8)** together in one place. Instead of 30+ scattered sample projects, you get one codebase where every concept, from a simple `Button` to a chained `WorkManager` pipeline, lives side by side, runs on a real device, and is backed by written study notes.

It is built to be **both a learning lab and a reference you can come back to** when you need to remember *how* something is done properly.

### 🎯 What this project gives developers

| | |
|---|---|
| 🧱 **Progressive learning path** | Eight units that grow from state basics to Room, Retrofit, DataStore, WorkManager, and View interoperability. |
| 🏗️ **Real architecture, not toy code** | MVVM, Unidirectional Data Flow, Repository pattern, and manual DI through an `AppContainer`. |
| 🔌 **Production-style integrations** | Type-safe navigation, offline-first persistence, background work chains, and network layers with proper error states. |
| 🔁 **Legacy ↔ Compose bridges** | Working examples of `ComposeView`, `AndroidView`, and `AndroidViewBinding` for incremental migration. |
| 🧪 **Tested code** | Compose UI tests, navigation tests, and DAO persistence tests. |
| 📝 **Study notes per app** | Each sample ships with a `*_study_notes.md` that explains the *why*, not only the *what*. |
| 🧪 **A sandbox** | The *Exercise Lab* is a free area for experimenting without touching the curated samples. |

> [!NOTE]
> This repository is a **learning and reference project**. Samples are intentionally focused: each one isolates a concept or a pattern so it is easy to read, copy, and adapt.

---

## 📸 Screenshots

**⭐ Featured**

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

**🗂 All samples (by unit)**

<details>
<summary><b>Unit 1 · Basics</b> · 4 apps</summary>
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
<summary><b>Unit 2 · UI & Layouts</b> · 3 apps</summary>
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
<summary><b>Unit 3 · Architecture & State</b> · 3 apps</summary>
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
<summary><b>Unit 4 · Navigation</b> · 4 apps</summary>
<br/>

<div align="center">

<table>
  <tr>
    <td align="center" width="25%"><img src="docs/screenshots/cupcake.png" width="170" alt="Cupcake" /><br/><sub><b>🧁 Cupcake</b></sub></td>
    <td align="center" width="25%"><img src="docs/screenshots/lunchtray.png" width="170" alt="Lunch Tray" /><br/><sub><b>🥗 Lunch Tray</b></sub></td>
    <td align="center" width="25%"><img src="docs/screenshots/reply.png" width="170" alt="Reply" /><br/><sub><b>📧 Reply</b></sub></td>
    <td align="center" width="25%"><img src="docs/screenshots/sports.png" width="170" alt="Sports" /><br/><sub><b>⚽ Sports</b></sub></td>
  </tr>
</table>

</div>

</details>

<details>
<summary><b>Unit 5 · Lists & Animations</b> · 6 apps</summary>
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
<summary><b>Unit 6 · Data & Persistence</b> · 7 apps</summary>
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
<summary><b>Unit 7 · WorkManager</b> · 2 apps</summary>
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
<summary><b>Unit 8 · Views & Interop</b> · 1 app</summary>
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
<summary><b>Sandbox</b> · 1 sample</summary>
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

## 🧠 Key Architecture & Features

### 🧭 High-level data flow

```mermaid
flowchart LR
    subgraph UI["🎨 UI Layer (Jetpack Compose + M3)"]
        S[Composable Screens]
    end
    subgraph VM["🧠 State Holder"]
        V[ViewModel]
    end
    subgraph DATA["💾 Data Layer"]
        R[Repository]
        ROOM[(Room / SQLite)]
        NET[Retrofit + Kotlinx Serialization]
        DS[(Preferences DataStore)]
    end
    WM[⚙️ WorkManager]

    S -- "events ⬆" --> V
    V -- "StateFlow ⬇" --> S
    V --> R
    R --> ROOM
    R --> NET
    R --> DS
    WM --> R
```

### ✨ Feature highlights

| Area | What you will find in the codebase |
|---|---|
| 🧩 **MVVM + UDF** | `ViewModel` owns state, exposes it as `StateFlow`, and the UI sends events up while state flows down. Screens are stateless and previewable. |
| 🌊 **StateFlow / SharedFlow** | `StateFlow` for UI state, `SharedFlow` for one-off events; lifecycle-aware collection in Compose. |
| 🧼 **Clean Architecture + Repository** | Clear separation of UI, state holders, and data sources. The Repository hides whether data comes from Room, the network, or DataStore. |
| 💉 **`AppContainer` DI** | Lightweight, dependency-free manual injection. Easy to understand, easy to test, and a good stepping stone to Hilt. |
| 🗄️ **Room** | DAOs returning `Flow`, asset-based and pre-populated databases, and repository-backed CRUD. |
| ⚙️ **DataStore** | Preferences DataStore for persisted user settings, combined with Room in the Flight Search app. |
| 🌐 **Networking** | Retrofit 2 + Kotlinx Serialization + Coil, with sealed UI states for `Loading / Success / Error`. |
| ⏱️ **WorkManager** | Unique work chains, constraints, and periodic work, with MediaStore for saving results. |
| 🧭 **Navigation** | Navigation Compose, **type-safe routes (2.8+)**, `BackHandler`, and XML nav graphs hosted via `AndroidViewBinding`. |
| 🎨 **Theming** | Material 3 with Light/Dark themes and Dynamic Color. |
| 📐 **Adaptive UI** | Layouts that adapt to window size, and per-sample state retention through `SaveableStateHolder`. |
| ✍️ **Custom drawing & motion** | Custom `Canvas` drawing and custom animations. |

<details>
<summary><b>📌 Example: UDF with <code>ViewModel</code> + <code>StateFlow</code></b></summary>

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
        onGuessChanged = viewModel::onGuessChanged, // events flow up ⬆
    )                                               // state flows down ⬇
}
```

</details>

<details>
<summary><b>📌 Example: Repository + <code>AppContainer</code> DI</b></summary>

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
<summary><b>📌 Example: Room DAO exposing <code>Flow</code></b></summary>

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
<summary><b>📌 Example: Type-safe navigation (Navigation 2.8+)</b></summary>

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
<summary><b>📌 Example: WorkManager unique work chain + constraints</b></summary>

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

## 🧰 Tech Stack

| Category | Technologies |
|---|---|
| 🎨 **UI & Design** | 100% Jetpack Compose · Material Design 3 · Dynamic Theming (Light/Dark) · Custom Canvas · Custom Animations · Adaptive Layouts (`SaveableStateHolder`) |
| 🏗️ **Architecture & State** | MVVM · Unidirectional Data Flow · `StateFlow` · `SharedFlow` · `ViewModel` · Clean Architecture · Repository Pattern · `AppContainer` DI |
| 🧭 **Navigation** | Navigation Compose · Type-Safe Navigation (2.8+) · `BackHandler` · `AndroidViewBinding` interoperability |
| 💾 **Data & Storage** | Room (Asset DBs, Pre-populated DBs, DAO, Flow) · Preferences DataStore |
| 🌐 **Network & Async** | Retrofit 2 · Kotlinx Serialization · Kotlin Coroutines · Coil |
| ⚙️ **Background Work** | WorkManager · Unique Work Chains · Constraints · MediaStore API |
| 🔁 **Interoperability** | `ComposeView` · `AndroidView` · `AndroidViewBinding` |
| 🧪 **Testing** | JUnit 4 · Compose UI Test Rules · `TestNavHostController` · DAO Persistence Tests |
| 🛠️ **Build** | Gradle (Kotlin DSL) · Version Catalog (`libs.versions.toml`) · Min SDK 24 · Target SDK 37 |

---

## 📱 Interactive App Catalog

> [!TIP]
> **30 sample apps across 8 units, plus the Exercise Lab sandbox (31 samples in total).** Each sample has its own folder under `samples/` with source code and a `*_study_notes.md` file.

| 📚 Units | 📱 Apps | 🧩 Focus |
|:---:|:---:|:---|
| 8 | 31 | Basics → Architecture → Navigation → Lists → Data → Background Work → Interop |

<br/>

### ![Unit 1](https://img.shields.io/badge/Unit_1-Basics-4285F4?style=for-the-badge&logo=android&logoColor=white)

> Compose fundamentals: composables, state, and your first interactive UIs.

| | App | What it demonstrates | Key concepts |
|:---:|---|---|---|
| 🎲 | **Dice Roller** | A button-driven dice with image swapping | `remember`, `mutableStateOf`, `Image`, previews |
| 🍋 | **Lemonade** | A tap-through state machine | State-driven UI, `when`, conditional rendering |
| 💸 | **Tip Calculator** | Live tip computation from user input | `TextField`, state hoisting, `NumberFormat` |
| 💬 | **Affirmations** | A scrollable card list | `LazyColumn`, `Card`, data classes, resources |

### ![Unit 2](https://img.shields.io/badge/Unit_2-UI_%26_Layouts-34A853?style=for-the-badge&logo=materialdesign&logoColor=white)

> Layouts, Material theming, and composable polish.

| | App | What it demonstrates | Key concepts |
|:---:|---|---|---|
| 🐶 | **Woof** | A themed pet list with expandable items | Material 3 theming, `animateContentSize`, shapes & typography |
| 🦸 | **Superheroes** | A card list with a top app bar | `Scaffold`, `TopAppBar`, `LazyColumn` |
| 🖼️ | **Art Space** | A simple gallery with navigation buttons | `Column`/`Row`/`Box`, `Surface`, state |

### ![Unit 3](https://img.shields.io/badge/Unit_3-Architecture_%26_State-FBBC04?style=for-the-badge&logo=kotlin&logoColor=black)

> `ViewModel`, state, and lifecycle-aware UI.

| | App | What it demonstrates | Key concepts |
|:---:|---|---|---|
| 🍰 | **Dessert Clicker** | A click-driven revenue and dessert progression game | State, lifecycle, `ViewModel`, share intent |
| ➕ | **Counter Exercise** | A focused state exercise | State hoisting, `rememberSaveable` |
| 🔤 | **Unscramble** | A word-puzzle game | `ViewModel`, `StateFlow`, UDF, `AlertDialog` |

### ![Unit 4](https://img.shields.io/badge/Unit_4-Navigation-EA4335?style=for-the-badge&logo=android&logoColor=white)

> Multi-screen apps and adaptive navigation.

| | App | What it demonstrates | Key concepts |
|:---:|---|---|---|
| 🧁 | **Cupcake** | A multi-step ordering flow | **Type-safe Navigation**, shared `ViewModel`, share intent |
| 🥗 | **Lunch Tray** | A multi-screen food-ordering flow | `NavHost`, back stack control, shared order state |
| 📧 | **Reply** | An adaptive email client | Window size classes, navigation rail/drawer, list-detail |
| ⚽ | **Sports** | An adaptive list/detail app | `BackHandler`, adaptive layouts |

### ![Unit 5](https://img.shields.io/badge/Unit_5-Lists_%26_Animations-9C27B0?style=for-the-badge&logo=jetpackcompose&logoColor=white)

> Lazy layouts, scroll state, motion, and concurrency.

| | App | What it demonstrates | Key concepts |
|:---:|---|---|---|
| 🔲 | **LazyGrid Practice** | Grid-based layouts | `LazyVerticalGrid`, adaptive/fixed cells |
| 📜 | **LazyListState Practice** | Reading and controlling scroll position | `LazyListState`, `derivedStateOf`, scroll-to-top |
| 🧬 | **Advanced Lazy Lists** | Optimized, richly structured lists | Sticky headers, item `key`, `contentType` |
| 🧾 | **Order History** | A history/list use case | Lists, item state |
| ✨ | **Dynamic List Animations** | Animated item add/remove/reorder | `animateItem`, `AnimatedVisibility` |
| 🏁 | **Race Tracker** | Concurrent progress simulation | Coroutines, `LaunchedEffect`, state holders |

### ![Unit 6](https://img.shields.io/badge/Unit_6-Data_%26_Persistence-00ACC1?style=for-the-badge&logo=sqlite&logoColor=white)

> Network, SQLite/Room, and preferences.

| | App | What it demonstrates | Key concepts |
|:---:|---|---|---|
| 🪐 | **Mars Photos** | Fetching and displaying remote images | **Retrofit**, Kotlinx Serialization, **Coil**, sealed UI states |
| 🐸 | **Amphibians** | A network-backed list/detail app | Repository, Retrofit, error handling |
| 🗄️ | **SQL Basics** | Core SQL concepts and queries | SQLite fundamentals, query practice |
| 🚌 | **Bus Schedule** | Reading a pre-populated database | **Room**, asset DB, DAO + `Flow` |
| 📦 | **Inventory** | Full CRUD with offline persistence | **Room**, Repository, `AppContainer` DI |
| 🍨 | **Dessert Release** | Persisting a UI preference | **Preferences DataStore** |
| ✈️ | **Flight Search** | A searchable airports/flights app | **Asset DB + DataStore**, `Flow`, search UX |

### ![Unit 7](https://img.shields.io/badge/Unit_7-WorkManager-FF6D00?style=for-the-badge&logo=android&logoColor=white)

> Reliable background execution.

| | App | What it demonstrates | Key concepts |
|:---:|---|---|---|
| 🌫️ | **Bluromatic** | Image blurring as a work pipeline | **Work chains**, unique work, constraints, `MediaStore` |
| 💧 | **Water Me** | Recurring reminders | **Periodic work**, notifications |

### ![Unit 8](https://img.shields.io/badge/Unit_8-Views_%26_Interop-6750A4?style=for-the-badge&logo=android&logoColor=white)

> Bridging classic Views and Compose in both directions.

| | App | What it demonstrates | Key concepts |
|:---:|---|---|---|
| 🧃 | **Juice Tracker** | **Views in Compose** & **Compose with Views** | `ComposeView`, `AndroidView`, `AndroidViewBinding` |

### ![Sandbox](https://img.shields.io/badge/Sandbox-Experiments-607D8B?style=for-the-badge&logo=beaker&logoColor=white)

| | App | What it demonstrates | Key concepts |
|:---:|---|---|---|
| 🧪 | **Exercise Lab** | A free area for experiments and practice | Anything goes |

---

## 🔁 View & Compose Interoperability Spotlight

Most production apps are not 100% Compose on day one. **Unit 8 (Juice Tracker)** demonstrates how to migrate and mix incrementally, in **both directions**.

```mermaid
flowchart TB
    subgraph XML["🧱 Classic View System"]
        RV[RecyclerView item]
        FRAG[Fragment + XML Nav Graph]
    end
    subgraph COMPOSE["🎨 Jetpack Compose"]
        UI[Composable screen]
        VIEW[Native View widgets]
    end

    RV -- "ComposeView" --> UI
    FRAG -- "AndroidViewBinding" --> UI
    UI -- "AndroidView" --> VIEW
```

| Direction | API | Used for | Where |
|---|---|---|---|
| 🧱 ➜ 🎨 **Compose inside Views** | `ComposeView` | Rendering Compose UI inside each XML `RecyclerView` item | Juice list |
| 🎨 ➜ 🧱 **Views inside Compose** | `AndroidView` | Embedding `RatingBar`, `Spinner`, and `TextView` in a composable | Juice entry form |
| 🎨 ➜ 🧱 **XML navigation inside Compose** | `AndroidViewBinding` | Hosting an XML Navigation Graph and Fragments in a composable | App shell |

### 1️⃣ `ComposeView`: Compose inside a `RecyclerView`

```kotlin
class JuiceViewHolder(
    private val composeView: ComposeView,
) : RecyclerView.ViewHolder(composeView) {

    init {
        // Recommended for RecyclerView: dispose when the view tree lifecycle ends.
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

### 2️⃣ `AndroidView`: a native `RatingBar` inside Compose

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
            // Runs once: create the View.
            RatingBar(context).apply {
                numStars = 5
                stepSize = 1f
            }
        },
        update = { ratingBar ->
            // Runs on every recomposition: sync Compose state into the View.
            ratingBar.rating = rating
            ratingBar.setOnRatingBarChangeListener { _, value, fromUser ->
                if (fromUser) onRatingChanged(value)
            }
        },
    )
}
```

### 3️⃣ `AndroidViewBinding`: an XML Navigation Graph inside Compose

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
> `AndroidViewBinding` requires **View Binding** to be enabled and the `androidx.compose.ui:ui-viewbinding` dependency. When it hosts a `FragmentContainerView`, the host Activity must be a `FragmentActivity` (for example `AppCompatActivity`).

> [!TIP]
> **Rule of thumb:** keep the *source of truth* in a `ViewModel`, and let both worlds (Views and Compose) observe the same state. This avoids two competing states during migration.

---

## 🚀 Getting Started & Installation

### ✅ Prerequisites

| Requirement | Version |
|---|---|
| 🧠 **Android Studio** | Latest stable release (Compose tooling and previews recommended) |
| ☕ **JDK** | 17 or newer (the bundled Android Studio JBR works) |
| 🤖 **Android SDK** | Platform **37** installed (via SDK Manager) |
| 📱 **Device / Emulator** | API **24+** |

### 📥 Installation

**1. Clone the repository**

```bash
git clone https://github.com/UmutCanAldirmaz/JetpackComposePlayground.git
cd JetpackComposePlayground
```

**2. Open in Android Studio**

1. Launch **Android Studio** → **File ▸ Open…**
2. Select the `JetpackComposePlayground` folder.
3. Wait for the **Gradle sync** to finish.

**3. Run the app**

1. Choose an emulator or a connected device (API 24+).
2. Select the `app` run configuration.
3. Press **▶ Run** (`Shift + F10`).

**Or use the command line:**

```bash
# macOS / Linux
./gradlew installDebug

# Windows
gradlew.bat installDebug
```

> [!NOTE]
> The first Gradle sync downloads dependencies and may take a few minutes. Make sure the Android SDK Platform 37 is installed, or the sync will report a missing platform.

> [!WARNING]
> Some samples need an internet connection (for example **Mars Photos** and **Amphibians**). Network-backed samples show an error state when offline, by design.

---

## 📂 Project Structure

All sample apps live under a single `samples/` package, one self-contained folder per app. The shell that launches them lives in `home/`, and the shared Material 3 theme in `core/theme/`.

```text
app/src/
├── main/
│   ├── AndroidManifest.xml
│   ├── assets/                                  # Pre-populated / asset Room databases
│   ├── res/
│   └── kotlin+java/com/example/jetpackcomposeplayground/
│       ├── MainActivity.kt                      # Single-Activity entry point
│       ├── PlaygroundApplication.kt             # Application class (global setup)
│       │
│       ├── core/theme/                          # 🎨 Shared Material 3 theme
│       │   ├── Color.kt
│       │   ├── Theme.kt
│       │   └── Type.kt
│       │
│       ├── home/                                # 🏠 Playground shell & launcher
│       │   ├── AppRoute.kt
│       │   ├── HomeScreen.kt
│       │   ├── PracticeApp.kt
│       │   └── PracticeAppCatalog.kt
│       │
│       └── samples/                             # 📱 One folder per sample (31 in total)
│           ├── advancedlazylists/                   # Unit 5
│           ├── affirmations/                        # Unit 1
│           ├── amphibians/                          # Unit 6
│           ├── artspace/                            # Unit 2
│           ├── bluromatic/                          # Unit 7
│           ├── busschedule/                         # Unit 6
│           ├── counter/ui/                          # Unit 3
│           ├── cupcake/                             # Unit 4
│           ├── dessertclicker/                      # Unit 3
│           ├── dessertrelease/                      # Unit 6
│           ├── diceroller/                          # Unit 1
│           ├── dynamiclistanimations/               # Unit 5
│           ├── flightsearch/                        # Unit 6
│           ├── inventory/                           # Unit 6
│           ├── juicetracker/                        # Unit 8
│           ├── lazygrid/                            # Unit 5
│           ├── lazyliststate/                       # Unit 5
│           ├── lemonade/                            # Unit 1
│           ├── lunchtray/                           # Unit 4
│           ├── marsphotos/                          # Unit 6
│           ├── orderhistory/                        # Unit 5
│           ├── racetracker/                         # Unit 5
│           ├── reply/                               # Unit 4
│           ├── sandbox/                             # Sandbox
│           ├── sports/                              # Unit 4
│           ├── sqlbasics/                           # Unit 6
│           ├── superheroes/                         # Unit 2
│           ├── tipcalculator/                       # Unit 1
│           ├── unscramble/                          # Unit 3
│           ├── waterme/                             # Unit 7
│           └── woof/                                # Unit 2
│
├── androidTest/…/samples/                       # 🤖 Instrumented tests (see Testing Strategy)
└── test/…/samples/                              # 🧮 Local unit tests (see Testing Strategy)
```

| Package | Responsibility |
|---|---|
| 🎨 **`core/theme`** | Material 3 colors, typography, and the app theme (Light/Dark + Dynamic Color). |
| 🏠 **`home`** | The playground shell: app-level routes (`AppRoute`), the launcher (`HomeScreen`), the root composable (`PracticeApp`), and the registry of all samples (`PracticeAppCatalog`). |
| 📱 **`samples`** | The 31 samples (30 apps + the Exercise Lab sandbox). Each folder is self-contained, with its own screens, state holders, data layer, and study notes. |

> [!TIP]
> Because every sample is isolated in its own package, you can read, copy, or delete one without touching the others.

---

## 📝 Study Notes & Documentation

Every sample folder contains a dedicated **`*_study_notes.md`** file, a compact write-up of what that app teaches.

```text
samples/<sample>/
├── …                              # screens, ViewModels, data layer
└── <sample>_study_notes.md        👈 concepts, pitfalls, key takeaways
```

**What a typical study note covers:**

| Section | Content |
|---|---|
| 🎯 **Goal** | What the app is meant to teach |
| 🧠 **Core concepts** | Definitions and mental models |
| 🧩 **Code walkthrough** | The important parts of the implementation, explained |
| ⚠️ **Pitfalls** | Common mistakes and how to avoid them |
| ✅ **Takeaways** | A short checklist to remember |

> [!TIP]
> **Suggested way to study:** read the study note first → run the app → read the source → then rebuild a smaller version of the sample in the **Exercise Lab** from memory.

---

## 🧪 Testing Strategy

Tests live next to the code they verify, mirroring the `samples/` package layout. The project combines **local JVM unit tests** with **instrumented tests** that run on a device or emulator.

### 🗺️ Coverage map

| Sample | 🧮 Unit | 🤖 Instrumented | What is verified |
|---|:---:|:---:|---|
| 🐸 **Amphibians** | ✅ | | `ViewModel` and Repository logic against **fake** API/data sources |
| 🪐 **Mars Photos** | ✅ | | `ViewModel` UI states and network Repository using fakes |
| 🏁 **Race Tracker** | ✅ | | Race participant logic and progress rules |
| 💸 **Tip Calculator** | ✅ | | Pure tip-calculation logic |
| 🔤 **Unscramble** | ✅ | | `GameViewModel` game logic (with `WordsData`) |
| 🧁 **Cupcake** | | ✅ | Compose UI tests for the order screen and **navigation** tests |
| ✈️ **Flight Search** | | ✅ | UI tests, **DAO persistence** tests, and UI + persistence integration |
| 📦 **Inventory** | | ✅ | Room **DAO** tests (`ItemDaoTest`) |
| 🌫️ **Bluromatic** | | ✅ | **WorkManager** worker instrumentation test |

<details>
<summary><b>📁 Test file inventory</b></summary>

```text
androidTest/…/samples/                          # 🤖 Instrumented
├── bluromatic/
│   └── WorkManagerWorkerInstrumentationTest
├── cupcake/test/
│   ├── ComposeRuleExtensions.kt                # shared test helpers
│   ├── CupcakeOrderScreenTest
│   ├── CupcakeScreenNavigationTest
│   └── ScreenAssertions.kt                     # shared assertions
├── flightsearch/
│   ├── FlightSearchDaoPersistenceTest
│   └── test/
│       ├── FlightSearchPersistenceUiTest
│       └── FlightSearchUiTest
├── inventory/
│   └── ItemDaoTest
└── ExampleInstrumentedTest

test/…/samples/                                 # 🧮 Local unit tests
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

### 🧰 Patterns used

| Pattern | Why it matters |
|---|---|
| 🎭 **Fakes instead of mocks** | `Fake*ApiService` and `Fake*Repository` classes keep network tests fast, deterministic, and readable. |
| ⏱️ **`TestDispatcherRule`** | A JUnit rule that swaps `Dispatchers.Main` so coroutine-based `ViewModel`s are testable. |
| 🧩 **Shared test helpers** | `ComposeRuleExtensions.kt` and `ScreenAssertions.kt` remove duplication across Compose tests. |
| 🗄️ **In-memory / persistence tests** | DAO tests verify real Room behavior without touching production data. |

### ▶️ Running the tests

```bash
# 🧮 Local unit tests (JVM, no device needed)
./gradlew testDebugUnitTest

# 🎯 A single unit test class
./gradlew testDebugUnitTest --tests "*TipCalculatorTest"

# 🤖 Instrumented tests (needs a connected device or emulator)
./gradlew connectedDebugAndroidTest

# 🎯 A single instrumented test class
./gradlew connectedDebugAndroidTest \
  -Pandroid.testInstrumentationRunnerArguments.class=com.example.jetpackcomposeplayground.samples.inventory.ItemDaoTest
```

> [!TIP]
> In Android Studio, right-click a test class or a `src/test` / `src/androidTest` directory and choose **Run Tests**.

> [!NOTE]
> The snippets below illustrate the *patterns* used by these tests. For the exact implementations, open the corresponding test files.

<details>
<summary><b>📌 Example: <code>TestDispatcherRule</code> + <code>ViewModel</code> test with fakes</b></summary>

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
<summary><b>📌 Example: Compose UI test</b></summary>

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
<summary><b>📌 Example: Navigation test with <code>TestNavHostController</code></b></summary>

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
<summary><b>📌 Example: DAO persistence test</b></summary>

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

## 🤝 Contributing

Contributions, issues, and ideas are welcome.

1. 🍴 Fork the project
2. 🌿 Create your feature branch: `git checkout -b feature/amazing-sample`
3. 💾 Commit your changes: `git commit -m "Add amazing sample"`
4. 📤 Push to the branch: `git push origin feature/amazing-sample`
5. 🔃 Open a Pull Request

> [!NOTE]
> When adding a new sample, create its folder under `samples/`, register it in `home/PracticeAppCatalog.kt`, include a matching `*_study_notes.md` file, and add the app to the [App Catalog](#-interactive-app-catalog).

---

## 📄 License & Author

### License

This project is licensed under the **MIT License**. See the [`LICENSE`](LICENSE) file for details.

### 👨‍💻 Author

<div align="center">

**Umut Can Aldırmaz**

[![GitHub](https://img.shields.io/badge/GitHub-UmutCanAldirmaz-181717?style=for-the-badge&logo=github&logoColor=white)](https://github.com/UmutCanAldirmaz)
[![LinkedIn](https://img.shields.io/badge/LinkedIn-Connect-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/umutcanaldirmaz/)

<br/>

**⭐ If this playground helped you learn or ship something, please consider giving it a star! ⭐**

<sub>Built with ❤️ using Kotlin, Jetpack Compose, and Material Design 3.</sub>

</div>
