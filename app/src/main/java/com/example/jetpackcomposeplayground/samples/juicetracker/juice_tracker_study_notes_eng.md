# Unit 8: Juice Tracker — Android Views, Fragments & Interoperability Study Notes

This document covers architectural design, View-Compose interoperability concepts, and technical issue resolutions in the Juice Tracker app for **Unit 8 (Android Views & Interoperability)**.

---

## 📌 1. How the App Works Step-by-Step (Workflow)

Juice Tracker allows users to save, edit, and delete juice recipes (name, description, color, 1-5 star rating).

The app displays two UI paradigms (XML Views & Jetpack Compose) side-by-side using a **tabbed container (`TabRow`)** on a single screen:

### 🔄 App Architecture and Screen Flow Table

| Tab | Architecture Used | Interoperability Technique | Description |
| :---: | :--- | :--- | :--- |
| **Tab 1: "Views in Compose"** | XML Navigation Graph + Fragment + RecyclerView | **`AndroidViewBinding` & `ComposeView`** | Runs `activity_juice_tracker.xml` inside Compose `Scaffold` via `AndroidViewBinding`. `RecyclerView` items inside `TrackerFragment` are rendered using `ComposeView`. |
| **Tab 2: "Compose with Views"** | 100% Jetpack Compose UI + ModalBottomSheet | **`AndroidView`** | Native Android `RatingBar`, `Spinner`, and `TextView` (AdBanner) components are embedded inside a pure Compose screen via `AndroidView` bridge. |

---

## 🏗️ 2. Modular Package Architecture (Clean Sub-Packaging)

Package structure is separated by responsibilities:

```
com.example.jetpackcomposeplayground.samples.juicetracker/
├── data/                               <-- Room Database, Entity, Dao & Repository
│   ├── Juice.kt                        (@Entity Data Model)
│   ├── JuiceDao.kt                     (Room Data Access Object)
│   ├── JuiceRepository.kt              (Repository Interface)
│   └── RoomJuiceRepository.kt          (Room Implementation)
│
├── views_with_compose/                 <-- Module 1: Adding Compose to View-based App
│   ├── TrackerFragment.kt              (XML Fragment - List Screen)
│   ├── EntryDialogFragment.kt         (XML BottomSheetDialogFragment - Add/Edit)
│   ├── JuiceListAdapter.kt             (ComposeView usage inside RecyclerView ViewHolder)
│   ├── TrackerViewModel.kt             (ViewModel for Fragment)
│   └── EntryViewModel.kt               (ViewModel for Dialog)
│
├── compose_with_views/                 <-- Module 2: Adding Native Views to Compose App
│   ├── JuiceTrackerComposeScreen.kt    (Pure Compose UI Content)
│   ├── JuiceTrackerComposeViewModel.kt (ViewModel for Compose Screen)
│   ├── bottomsheet/
│   │   ├── EntryBottomSheet.kt        (Compose BottomSheetForm)
│   │   ├── RatingInputRow.kt          (AndroidView -> RatingBar)
│   │   └── ColorSpinnerRow.kt          (AndroidView -> Spinner)
│   └── homescreen/
│       └── AdBanner.kt                 (AndroidView -> Native AdBanner View)
│
└── ui/                                 <-- Main Container Screen & ViewModel Factory
    ├── AppViewModelProvider.kt         (ViewModel Factory)
    └── JuiceTrackerScreen.kt           (Main Tabbed Container with Material 3 PrimaryTabRow)
```

---

## 💡 3. Core Interoperability Concepts

### A. `AndroidViewBinding` (Embedding View Binding in Compose)
- **Purpose:** Embed an existing XML layout file (e.g., `FragmentContainerView` and `NavHostFragment` in `activity_juice_tracker.xml`) into a Jetpack Compose screen.
- **Usage:**
```kotlin
AndroidViewBinding(
    factory = ActivityJuiceTrackerBinding::inflate,
    modifier = Modifier.fillMaxSize()
)
```

### B. `ComposeView` (Embedding Compose inside Views / RecyclerView)
- **Purpose:** Render Jetpack Compose UI components inside `RecyclerView.ViewHolder` or traditional XML View layouts.
- **Usage:**
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

### C. `AndroidView` (Embedding Native Android Views in Compose)
- **Purpose:** Use legacy Android View components (`RatingBar`, `Spinner`, `AdView`, etc.) that do not yet have direct Compose equivalents.
- **`factory` Parameter:** Runs once when the View is instantiated.
- **`update` Parameter:** Triggers upon recomposition or state change to update View properties.
- **Example - `RatingBar` Integration:**
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

## 🐞 Encountered Critical Issues and Solutions

### 1. `java.lang.reflect.InvocationTargetException` (Inflation Crash)
- **Root Cause 1:** `MainActivity` extended `ComponentActivity`. Inflating `FragmentContainerView` (`NavHostFragment`) from XML failed to find `FragmentManager`, throwing `UnsupportedOperationException`.
  - **Solution:** Updated `MainActivity` to extend `AppCompatActivity` (and thus `FragmentActivity`).
- **Root Cause 2:** App theme was `android:Theme.Material.Light.NoActionBar` while XML layouts used Material 3 components (`MaterialButton`, `MaterialDivider`, `FloatingActionButton`). Missing `MaterialButton` style threw `IllegalArgumentException` during reflection.
  - **Solution:** Updated theme parent in `themes.xml` to `Theme.Material3.DayNight.NoActionBar`.

### 2. Material 3 Color Hierarchy (Surface Color Separation)
- **Issue:** TopAppBar and List Cards shared the same `surface` color, causing visual blending.
- **Solution:** 
  - `TopAppBar`: `MaterialTheme.colorScheme.surfaceContainer`
  - `ElevatedCard`: `MaterialTheme.colorScheme.surfaceContainerLow`
  - `Scaffold Background`: `MaterialTheme.colorScheme.background`

---

## 🧪 Testing and Verification
- **Gradle Build:** `app:assembleDebug` completed successfully.
- **Compose Previews:** Render tests for `PreviewListItem` and `PreviewAdBanner` passed.
