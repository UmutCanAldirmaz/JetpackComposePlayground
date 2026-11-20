# Unit 6: Flight Search App - Room, DataStore & DI Notes

## 📌 Summary and Technical Concepts

The Flight Search application is a comprehensive Unit 6 project combining a pre-populated Room SQLite database (from asset DB), Preferences DataStore, and Manual Dependency Injection (AppContainer) patterns.

### 1. Pre-populated Room Database
- **Asset DB:** `Room.databaseBuilder(...).createFromAsset("database/flight_search.db")` loads pre-filled airport data from `assets/database/`.
- **Airport Entity (`airport`):** Contains `id`, `name`, `iata_code`, and `passengers` columns.
- **Favorite Entity (`favorite`):** Stores routes saved by the user (`id`, `departure_code`, `destination_code`).

### 2. Preferences DataStore
- Asynchronously stores the user's latest search text or clicked airport IATA code in the `flight_search_preferences` DataStore file under key `search_text`.
- Automatically restores the previous search state when the app re-launches.

### 3. Manual DI and Container Pattern (Clean Architecture)
- **`FlightSearchAppContainer` (Interface):** Abstracts repository dependencies.
- **`DefaultFlightSearchAppContainer`:** Lazily initializes Database and DataStore instances using `by lazy`.
- **`PlaygroundApplication` Integration:** The `Application` class initializes only `flightSearchContainer` rather than holding DB instances directly (Single Responsibility Principle).

### 4. System Back Gesture Handling (Predictive Back / BackHandler)
- Integrated via `androidx.activity.compose.BackHandler`:
  - If a search query or selected airport is present: System back resets search and returns to **Favorite Routes**.
  - If search is empty: System back navigates to the main app catalog (`HomeScreen`).

---

## 🐞 Encountered Issues and Technical Solutions

### 1. `collectLatest` Coroutine Cancellation Loop
- **Issue:** During `FlightSearchViewModel` initialization, DataStore stream was collected using `collectLatest`. Initial data collection triggered `selectAirport`, which wrote back to DataStore. When DataStore emitted a new value, `collectLatest` immediately canceled the previous block. This interrupted the Coroutine fetching flight data from Room, leaving the UI blank on launch.
- **Solution:** Replaced `collectLatest` with `.first()` inside the `init` block to read DataStore once upon startup. Passed `saveToDataStore = false` during initial load to break the cycle completely.

### 2. Room Schema Mismatch & Destructive Migration
- **Issue:** SQL type for `passengers` column in original SQLite asset database was defined as `INT`. Room Kotlin `@Entity` defined `passengers: Int`, expecting `INTEGER` type schema. Room detected schema mismatch on startup and dropped tables (`DROP TABLE`) to copy raw asset DB because `fallbackToDestructiveMigration(dropAllTables = true)` was enabled. This erased all user-saved favorites.
- **Solution:** Updated `passengers` column type in asset DB table to `INTEGER`. Removed `fallbackToDestructiveMigration` from `FlightSearchDatabase` to ensure data persists 100% on disk.

---

## 🧪 Instrumented Test Strategy (`androidTest`)

- **`FlightSearchDaoPersistenceTest.kt`**: Verifies saved favorite route persists after database is closed (`db.close()`) and re-opened from disk (**PASSED** ✅).
- **`FlightSearchPersistenceUiTest.kt`**: End-to-end UI test covering search, airport selection, favoriting flight, clearing search, and verifying favorite display (**PASSED** ✅).
