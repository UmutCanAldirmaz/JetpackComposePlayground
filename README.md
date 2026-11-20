<div align="center">
🎮 JetpackComposePlayground
The complete Android Kotlin + Jetpack Compose reference: 30+ real-world apps, study notes, and production-grade architecture patterns.
<p> <img src="https://img.shields.io/badge/Platform-Android-3DDC84?style=for-the-badge&logo=android&logoColor=white" alt="Android" /> <img src="https://img.shields.io/badge/Kotlin-2.x-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white" alt="Kotlin" /> <img src="https://img.shields.io/badge/Jetpack%20Compose-100%25-4285F4?style=for-the-badge&logo=jetpackcompose&logoColor=white" alt="Jetpack Compose" /> <img src="https://img.shields.io/badge/Material%20Design-3-6750A4?style=for-the-badge&logo=materialdesign&logoColor=white" alt="Material 3" /> </p> <p> <img src="https://img.shields.io/badge/Gradle-Kotlin%20DSL-02303A?style=for-the-badge&logo=gradle&logoColor=white" alt="Gradle" /> <img src="https://img.shields.io/badge/Min%20SDK-24-brightgreen?style=for-the-badge" alt="Min SDK 24" /> <img src="https://img.shields.io/badge/Target%20SDK-37-blue?style=for-the-badge" alt="Target SDK 37" /> <img src="https://img.shields.io/badge/License-MIT-yellow?style=for-the-badge" alt="License" /> </p> <p> <img src="https://img.shields.io/badge/Architecture-MVVM%20%2B%20UDF-orange?style=flat-square" alt="MVVM + UDF" /> <img src="https://img.shields.io/badge/Apps-31-success?style=flat-square" alt="31 apps" /> <img src="https://img.shields.io/badge/Units-8-informational?style=flat-square" alt="8 units" /> <img src="https://img.shields.io/badge/PRs-welcome-ff69b4?style=flat-square" alt="PRs welcome" /> </p>

Overview · Architecture · App Catalog · Interop Spotlight · Getting Started · Testing

</div>
📑 Table of Contents
Project Overview
Key Architecture & Features
Tech Stack
Interactive App Catalog
View & Compose Interoperability Spotlight
Getting Started & Installation
Project Structure
Study Notes & Documentation
Testing Strategy
Contributing
License & Author
🌟 Project Overview

JetpackComposePlayground is a single, cohesive Android application that brings the entire Android Kotlin Compose curriculum (Unit 1 → Unit 8) together in one place. Instead of 30+ scattered sample projects, you get one codebase where every concept, from a simple Button to a chained WorkManager pipeline, lives side by side, runs on a real device, and is backed by written study notes.

It is built to be both a learning lab and a reference you can come back to when you need to remember how something is done properly.

🎯 What this project gives developers
	
🧱 Progressive learning path	Eight units that grow from state basics to Room, Retrofit, DataStore, WorkManager, and View interoperability.
🏗️ Real architecture, not toy code	MVVM, Unidirectional Data Flow, Repository pattern, and manual DI through an AppContainer.
🔌 Production-style integrations	Type-safe navigation, offline-first persistence, background work chains, and network layers with proper error states.
🔁 Legacy ↔ Compose bridges	Working examples of ComposeView, AndroidView, and AndroidViewBinding for incremental migration.
🧪 Tested code	Compose UI tests, navigation tests, and DAO persistence tests.
📝 Study notes per app	Each sample ships with a *_study_notes.md that explains the why, not only the what.
🧪 A sandbox	The Exercise Lab is a free area for experimenting without touching the curated samples.

[!NOTE] This repository is a learning and reference project. Samples are intentionally focused: each one isolates a concept or a pattern so it is easy to read, copy, and adapt.

🏛️ Key Architecture & Features
🧭 High-level data flow
💾 Data Layer
🧠 State Holder
🎨 UI Layer (Jetpack Compose + M3)
events ⬆
StateFlow ⬇
Composable Screens
ViewModel
Repository
Room / SQLite
Retrofit + KotlinxSerialization
Preferences DataStore
⚙️ WorkManager
✨ Feature highlights
Area	What you will find in the codebase
🧩 MVVM + UDF	ViewModel owns state, exposes it as StateFlow, and the UI sends events up while state flows down. Screens are stateless and previewable.
🌊 StateFlow / SharedFlow	StateFlow for UI state, SharedFlow for one-off events; lifecycle-aware collection in Compose.
🧼 Clean Architecture + Repository	Clear separation of UI, state holders, and data sources. The Repository hides whether data comes from Room, the network, or DataStore.
💉 AppContainer DI	Lightweight, dependency-free manual injection. Easy to understand, easy to test, and a good stepping stone to Hilt.
🗄️ Room	DAOs returning Flow, asset-based and pre-populated databases, and repository-backed CRUD.
⚙️ DataStore	Preferences DataStore for persisted user settings, combined with Room in the Flight Search app.
🌐 Networking	Retrofit 2 + Kotlinx Serialization + Coil, with sealed UI states for Loading / Success / Error.
⏱️ WorkManager	Unique work chains, constraints, and periodic work, with MediaStore for saving results.
🧭 Navigation	Navigation Compose, type-safe routes (2.8+), BackHandler, and XML nav graphs hosted via AndroidViewBinding.
🎨 Theming	Material 3 with Light/Dark themes and Dynamic Color.
📐 Adaptive UI	Layouts that adapt to window size, and per-sample state retention through SaveableStateHolder.
✍️ Custom drawing & motion	Custom Canvas drawing and custom animations.
<details> <summary><b>📌 Example: UDF with <code>ViewModel</code> + <code>StateFlow</code></b></summary>
kotlin
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
</details> <details> <summary><b>📌 Example: Repository + <code>AppContainer</code> DI</b></summary>
kotlin
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
</details> <details> <summary><b>📌 Example: Room DAO exposing <code>Flow</code></b></summary>
kotlin
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
</details> <details> <summary><b>📌 Example: Type-safe navigation (Navigation 2.8+)</b></summary>
kotlin
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
</details> <details> <summary><b>📌 Example: WorkManager unique work chain + constraints</b></summary>
kotlin
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
</details>
🧰 Tech Stack
Category	Technologies
🎨 UI & Design	100% Jetpack Compose · Material Design 3 · Dynamic Theming (Light/Dark) · Custom Canvas · Custom Animations · Adaptive Layouts (SaveableStateHolder)
🏗️ Architecture & State	MVVM · Unidirectional Data Flow · StateFlow · SharedFlow · ViewModel · Clean Architecture · Repository Pattern · AppContainer DI
🧭 Navigation	Navigation Compose · Type-Safe Navigation (2.8+) · BackHandler · AndroidViewBinding interoperability
💾 Data & Storage	Room (Asset DBs, Pre-populated DBs, DAO, Flow) · Preferences DataStore
🌐 Network & Async	Retrofit 2 · Kotlinx Serialization · Kotlin Coroutines · Coil
⚙️ Background Work	WorkManager · Unique Work Chains · Constraints · MediaStore API
🔁 Interoperability	ComposeView · AndroidView · AndroidViewBinding
🧪 Testing	JUnit 4 · Compose UI Test Rules · TestNavHostController · DAO Persistence Tests
🛠️ Build	Gradle (Kotlin DSL) · Version Catalog (libs.versions.toml) · Min SDK 24 · Target SDK 37
📱 Interactive App Catalog

[!TIP] 31 sample apps across 8 units + a sandbox. Each app has its own folder with source code and a *_study_notes.md file.

📚 Units	📱 Apps	🧩 Focus
8	31	Basics → Architecture → Navigation → Lists → Data → Background Work → Interop
<br/>
Görseli göster

Compose fundamentals: composables, state, and your first interactive UIs.

	App	What it demonstrates	Key concepts
🎲	Dice Roller	A button-driven dice with image swapping	remember, mutableStateOf, Image, previews
🍋	Lemonade	A tap-through state machine	State-driven UI, when, conditional rendering
💸	Tip Calculator	Live tip computation from user input	TextField, state hoisting, NumberFormat
💬	Affirmations	A scrollable card list	LazyColumn, Card, data classes, resources
Görseli göster

Layouts, Material theming, and composable polish.

	App	What it demonstrates	Key concepts
🐶	Woof	A themed pet list with expandable items	Material 3 theming, animateContentSize, shapes & typography
🦸	Superheroes	A card list with a top app bar	Scaffold, TopAppBar, LazyColumn
🖼️	Art Space	A simple gallery with navigation buttons	Column/Row/Box, Surface, state
Görseli göster

ViewModel, state, and lifecycle-aware UI.

	App	What it demonstrates	Key concepts
🍰	Dessert Clicker	A click-driven revenue and dessert progression game	State, lifecycle, ViewModel, share intent
➕	Counter Exercise	A focused state exercise	State hoisting, rememberSaveable
🔤	Unscramble	A word-puzzle game	ViewModel, StateFlow, UDF, AlertDialog
Görseli göster

Multi-screen apps and adaptive navigation.

	App	What it demonstrates	Key concepts
🧁	Cupcake	A multi-step ordering flow	Type-safe Navigation, shared ViewModel, share intent
🥗	Lunch Tray	A multi-screen food-ordering flow	NavHost, back stack control, shared order state
📧	Reply	An adaptive email client	Window size classes, navigation rail/drawer, list-detail
⚽	Sports	An adaptive list/detail app	BackHandler, adaptive layouts
Görseli göster

Lazy layouts, scroll state, motion, and concurrency.

	App	What it demonstrates	Key concepts
🔲	LazyGrid Practice	Grid-based layouts	LazyVerticalGrid, adaptive/fixed cells
📜	LazyListState Practice	Reading and controlling scroll position	LazyListState, derivedStateOf, scroll-to-top
🧬	Advanced Lazy Lists	Optimized, richly structured lists	Sticky headers, item key, contentType
🧾	Order History	A history/list use case	Lists, item state
✨	Dynamic List Animations	Animated item add/remove/reorder	animateItem, AnimatedVisibility
🏁	Race Tracker	Concurrent progress simulation	Coroutines, LaunchedEffect, state holders
Görseli göster

Network, SQLite/Room, and preferences.

	App	What it demonstrates	Key concepts
🪐	Mars Photos	Fetching and displaying remote images	Retrofit, Kotlinx Serialization, Coil, sealed UI states
🐸	Amphibians	A network-backed list/detail app	Repository, Retrofit, error handling
🗄️	SQL Basics	Core SQL concepts and queries	SQLite fundamentals, query practice
🚌	Bus Schedule	Reading a pre-populated database	Room, asset DB, DAO + Flow
📦	Inventory	Full CRUD with offline persistence	Room, Repository, AppContainer DI
🍨	Dessert Release	Persisting a UI preference	Preferences DataStore
✈️	Flight Search	A searchable airports/flights app	Asset DB + DataStore, Flow, search UX
Görseli göster

Reliable background execution.

	App	What it demonstrates	Key concepts
🌫️	Bluromatic	Image blurring as a work pipeline	Work chains, unique work, constraints, MediaStore
💧	Water Me	Recurring reminders	Periodic work, notifications
Görseli göster

Bridging classic Views and Compose in both directions.

	App	What it demonstrates	Key concepts
🧃	Juice Tracker	Views in Compose & Compose with Views	ComposeView, AndroidView, AndroidViewBinding
Görseli göster
	App	What it demonstrates	Key concepts
🧪	Exercise Lab	A free area for experiments and practice	Anything goes
🔁 View & Compose Interoperability Spotlight

Most production apps are not 100% Compose on day one. Unit 8 (Juice Tracker) demonstrates how to migrate and mix incrementally, in both directions.

🎨 Jetpack Compose
🧱 Classic View System
ComposeView
AndroidViewBinding
AndroidView
RecyclerView item
Fragment + XML Nav Graph
Composable screen
Native View widgets
Direction	API	Used for	Where
🧱 ➜ 🎨 Compose inside Views	ComposeView	Rendering Compose UI inside each XML RecyclerView item	Juice list
🎨 ➜ 🧱 Views inside Compose	AndroidView	Embedding RatingBar, Spinner, and TextView in a composable	Juice entry form
🎨 ➜ 🧱 XML navigation inside Compose	AndroidViewBinding	Hosting an XML Navigation Graph and Fragments in a composable	App shell
1️⃣ ComposeView: Compose inside a RecyclerView
kotlin
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
2️⃣ AndroidView: a native RatingBar inside Compose
kotlin
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
3️⃣ AndroidViewBinding: an XML Navigation Graph inside Compose
xml
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
kotlin
@Composable
fun LegacyNavHost(modifier: Modifier = Modifier) {
    AndroidViewBinding(
        factory = FragmentContainerHostBinding::inflate,
        modifier = modifier,
    )
}

[!IMPORTANT] AndroidViewBinding requires View Binding to be enabled and the androidx.compose.ui:ui-viewbinding dependency. When it hosts a FragmentContainerView, the host Activity must be a FragmentActivity (for example AppCompatActivity).

[!TIP] Rule of thumb: keep the source of truth in a ViewModel, and let both worlds (Views and Compose) observe the same state. This avoids two competing states during migration.

🚀 Getting Started & Installation
✅ Prerequisites
Requirement	Version
🧠 Android Studio	Latest stable release (Compose tooling and previews recommended)
☕ JDK	17 or newer (the bundled Android Studio JBR works)
🤖 Android SDK	Platform 37 installed (via SDK Manager)
📱 Device / Emulator	API 24+
📥 Installation

1. Clone the repository

bash
git clone https://github.com/<your-username>/JetpackComposePlayground.git
cd JetpackComposePlayground

2. Open in Android Studio

Launch Android Studio → File ▸ Open…
Select the JetpackComposePlayground folder.
Wait for the Gradle sync to finish.

3. Run the app

Choose an emulator or a connected device (API 24+).
Select the app run configuration.
Press ▶ Run (Shift + F10).

Or use the command line:

bash
# macOS / Linux
./gradlew installDebug

# Windows
gradlew.bat installDebug

[!NOTE] The first Gradle sync downloads dependencies and may take a few minutes. Make sure the Android SDK Platform 37 is installed, or the sync will report a missing platform.

[!WARNING] Some samples need an internet connection (for example Mars Photos and Amphibians). Network-backed samples show an error state when offline, by design.

🗂️ Project Structure

[!NOTE] The tree below is an illustrative overview. Adjust the folder names to match your repository.

text
JetpackComposePlayground/
├── app/
│   └── src/
│       ├── main/
│       │   ├── java/…/playground/
│       │   │   ├── unit1_basics/            # Dice Roller, Lemonade, Tip Calculator, Affirmations
│       │   │   ├── unit2_ui_layouts/        # Woof, Superheroes, Art Space
│       │   │   ├── unit3_architecture/      # Dessert Clicker, Counter, Unscramble
│       │   │   ├── unit4_navigation/        # Cupcake, Lunch Tray, Reply, Sports
│       │   │   ├── unit5_lists_animations/  # Lazy*, Order History, Race Tracker …
│       │   │   ├── unit6_data/              # Mars Photos, Amphibians, Inventory, Flight Search …
│       │   │   ├── unit7_workmanager/       # Bluromatic, Water Me
│       │   │   ├── unit8_interop/           # Juice Tracker
│       │   │   ├── sandbox/                 # Exercise Lab
│       │   │   └── ui/theme/                # Material 3 theme (Color, Type, Shape)
│       │   ├── assets/                      # Pre-populated / asset Room databases
│       │   └── res/
│       ├── test/                            # Local JVM unit tests
│       └── androidTest/                     # Instrumented tests (Compose UI, DAO, Navigation)
├── gradle/
│   └── libs.versions.toml                   # Version catalog
└── README.md
📝 Study Notes & Documentation

Every sample folder contains a dedicated *_study_notes.md file, a compact write-up of what that app teaches.

text
<sample-folder>/
├── <SampleName>Screen.kt
├── <SampleName>ViewModel.kt
└── <sample_name>_study_notes.md   👈 concepts, pitfalls, key takeaways

What a typical study note covers:

Section	Content
🎯 Goal	What the app is meant to teach
🧠 Core concepts	Definitions and mental models
🧩 Code walkthrough	The important parts of the implementation, explained
⚠️ Pitfalls	Common mistakes and how to avoid them
✅ Takeaways	A short checklist to remember

[!TIP] Suggested way to study: read the study note first → run the app → read the source → then rebuild a smaller version of the sample in the Exercise Lab from memory.

🧪 Testing Strategy

The project covers three complementary layers of testing.

Layer	Tooling	What is verified
🧮 Unit tests	JUnit 4	ViewModel logic, state transitions, and pure Kotlin
🎨 UI tests	Compose UI Test Rules	Composable rendering, semantics, and user interactions
🧭 Navigation tests	TestNavHostController	Routes, back stack, and destination changes
🗄️ Persistence tests	Room in-memory DB + DAO tests	Inserts, queries, updates, deletes, and Flow emissions
▶️ Running the tests
bash
# 🧮 Local unit tests (JVM, no device needed)
./gradlew testDebugUnitTest

# 🎨🧭🗄️ Instrumented tests (needs a connected device or emulator)
./gradlew connectedDebugAndroidTest

# 🎯 Run a single test class
./gradlew connectedDebugAndroidTest \
  -Pandroid.testInstrumentationRunnerArguments.class=<fully.qualified.TestClassName>

[!TIP] In Android Studio, right-click a test class or a src/test / src/androidTest directory and choose Run Tests.

<details> <summary><b>📌 Example: Compose UI test</b></summary>
kotlin
class StartScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun startScreen_displaysOrderButtons() {
        composeTestRule.setContent {
            StartOrderScreen(onNext = {})
        }

        composeTestRule.onNodeWithText("Six Cupcakes").assertIsDisplayed()
    }
}
</details> <details> <summary><b>📌 Example: Navigation test with <code>TestNavHostController</code></b></summary>
kotlin
class NavigationTest {

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
    fun navHost_verifyStartDestination() {
        val route = navController.currentBackStackEntry?.destination?.route
        assertTrue(route?.contains("Start") == true)
    }
}
</details> <details> <summary><b>📌 Example: DAO persistence test</b></summary>
kotlin
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
</details>
🤝 Contributing

Contributions, issues, and ideas are welcome.

🍴 Fork the project
🌿 Create your feature branch: git checkout -b feature/amazing-sample
💾 Commit your changes: git commit -m "Add amazing sample"
📤 Push to the branch: git push origin feature/amazing-sample
🔃 Open a Pull Request

[!NOTE] When adding a new sample, please include a matching *_study_notes.md file and add the app to the App Catalog.

📄 License & Author
License

This project is licensed under the MIT License. See the LICENSE file for details.

👨‍💻 Author
<div align="center">

[Your Name]

GitHub LinkedIn

<br/>

⭐ If this playground helped you learn or ship something, please consider giving it a star! ⭐

<sub>Built with ❤️ using Kotlin, Jetpack Compose, and Material Design 3.</sub>

</div>
