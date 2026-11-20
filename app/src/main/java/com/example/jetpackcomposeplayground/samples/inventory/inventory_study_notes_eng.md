# Inventory App (Android Basics with Compose & Room) - Technical Notes and Development Guide

This document outlines the architecture, Room database integration, Jetpack Compose UI components, and solutions to technical challenges encountered in the **Inventory** app.

---

## 1. App Architecture

Built following Google's **Modern Android Architecture (MAD)** guidelines across 3 primary layers:

```
[ UI Layer (Jetpack Compose) ]
             ▲
             │ (StateFlow / UI State)
             ▼
[ ViewModel Layer (State / Logic) ]
             ▲
             │ (ItemsRepository Interface)
             ▼
[ Data Layer (OfflineItemsRepository + Room DAO) ]
             ▲
             │ (SQLite Queries)
             ▼
[ Room Database (InventoryDatabase + SQLite) ]
```

### Manual Dependency Injection
* **`AppContainer` & `AppDataContainer`**: Manages application-level singletons for database (`InventoryDatabase`) and repository (`OfflineItemsRepository`).
* **`AppViewModelProvider`**: Uses `ViewModelProvider.Factory` to supply required `SavedStateHandle` and `ItemsRepository` dependencies safely to all ViewModel classes (`ItemEntryViewModel`, `ItemDetailsViewModel`, `ItemEditViewModel`, `HomeViewModel`).

---

## 2. Data Layer (Room)

### `Item` Entity
Data class representing the `items` database table:
```kotlin
@Entity(tableName = "items")
data class Item(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val price: Double,
    val quantity: Int
)
```

### `ItemDao` (Data Access Object)
Interface defining database queries:

```kotlin
@Dao
interface ItemDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: Item)

    @Update
    suspend fun update(item: Item)

    @Delete
    suspend fun delete(item: Item)

    @Query("SELECT * from items WHERE id = :id")
    fun getItem(id: Int): Flow<Item?>

    @Query("SELECT * from items ORDER BY name ASC")
    fun getAllItems(): Flow<List<Item>>
}
```

---

## 3. Important Technical Lessons and Key Takeaways

### ⚠️ Critical Lesson: Room 2.8.x and Nullability (`Flow<Item?>`) Mechanism

#### Problem: `IllegalStateException: The query result was empty, but expected a single row to return a NON-NULL object of type Item`
The app crashed when deleting an item from details screen or querying a non-existent ID.

#### Technical Cause:
1. Upgrading Room from **2.6.x** to **2.8.x** made KSP codegen strictly enforce Kotlin Nullability rules.
2. Defining `getItem(id: Int)` as `Flow<Item>` (non-null) caused Room codegen in `ItemDao_Impl.kt` to generate *"throw error if query returns 0 rows"*.
3. When an item is deleted, Room detects DB change and re-runs `getItem`. Because query returns 0 rows, Room throws `IllegalStateException`.

#### Solution:
Update return type in `ItemDao.kt` to `Flow<Item?>` (nullable):
```kotlin
@Query("SELECT * from items WHERE id = :id")
fun getItem(id: Int): Flow<Item?>
```
* **Result**: Room emits `null` instead of crashing upon item deletion.
* The `.filterNotNull()` operator on `itemsRepository.getItemStream(itemId)` in ViewModel silently filters out `null`, safely completing the stream and preventing crashes.

---

### 🛡️ Input Validation and Preventing Negative Stock / Price

Two-tier validation prevents negative stock or price entry in form screens:

1. **ViewModel Layer (`validateInput`)**:
   ```kotlin
   private fun validateInput(uiState: ItemDetails = itemUiState.itemDetails): Boolean {
       return with(uiState) {
           val priceVal = price.toDoubleOrNull()
           val qtyVal = quantity.toIntOrNull()
           name.isNotBlank() && priceVal != null && priceVal >= 0.0 && qtyVal != null && qtyVal >= 0
       }
   }
   ```
   * Verifies stock quantity (`qtyVal >= 0`) and price (`priceVal >= 0.0`) are positive or zero. Disables Save button (`enabled = false`) if negative values are typed.

2. **UI Layer (`OutlinedTextField` Input Filter)**:
   ```kotlin
   onValueChange = { input ->
       if (input.isEmpty() || input.all { it.isDigit() }) {
           onValueChange(itemDetails.copy(quantity = input))
       }
   }
   ```
   * Prevents entering minus signs (`-`) or non-numeric characters in the quantity field.

---

## 4. ViewModel and Screen Logic

### 1. `ItemEntryViewModel` (Item Creation)
* Tracks user-entered Name, Price, and Quantity inside `ItemUiState`.
* Checks form field validity via `validateInput()`.
* Adds item to database via `saveItem()`.

### 2. `ItemDetailsViewModel` (Details, Sell & Delete)
* Emits live selected item data as `StateFlow` via `getItemStream(itemId)`:
  ```kotlin
  val uiState: StateFlow<ItemDetailsUiState> =
      itemsRepository.getItemStream(itemId)
          .filterNotNull()
          .map { ItemDetailsUiState(outOfStock = it.quantity <= 0, itemDetails = it.toItemDetails()) }
          .stateIn(
              scope = viewModelScope,
              started = SharingStarted.WhileSubscribed(5_000L),
              initialValue = ItemDetailsUiState()
          )
  ```
* **`SharingStarted.WhileSubscribed(5_000L)`**: Keeps data stream active for 5 seconds after unsubscription during configuration changes, preventing unnecessary DB queries.
* `reduceQuantityByOne()`: Decrements item stock by 1.
* `deleteItem()`: Removes item from database.

### 3. `ItemEditViewModel` (Item Editing)
* Obtains `itemIdArg` argument via `SavedStateHandle`.
* Fetches item data from DB in `init` block and populates form fields:
  ```kotlin
  init {
      viewModelScope.launch {
          itemUiState = itemsRepository.getItemStream(itemId)
              .filterNotNull()
              .first()
              .toItemUiState(isEntryValid = true)
      }
  }
  ```
* `updateUiState()`: Validates form on every change.
* `updateItem()`: Saves updated item back to DB.

---

## 5. UI and Navigation Structure (`InventoryNavGraph`)

4 main navigation routes:

1. **`HomeDestination` (`"home"`)**:
   - Main screen listing all items alphabetically (`HomeScreen`). FAB navigates to creation form.
2. **`ItemEntryDestination` (`"item_entry"`)**:
   - Form for adding new items (`ItemEntryScreen`).
3. **`ItemDetailsDestination` (`"item_details/{itemId}"`)**:
   - Displays details with Sell, Delete, and Edit actions (`ItemDetailsScreen`).
4. **`ItemEditDestination` (`"item_edit/{itemId}"`)**:
   - Form for editing existing item (`ItemEditScreen`).

---

## 6. Unit Tests (`ItemDaoTest`)

Database operations are tested using an in-memory database:

```kotlin
@RunWith(AndroidJUnit4::class)
class ItemDaoTest {
    private lateinit var itemDao: ItemDao
    private lateinit var inventoryDatabase: InventoryDatabase

    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        inventoryDatabase = Room.inMemoryDatabaseBuilder(context, InventoryDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        itemDao = inventoryDatabase.itemDao()
    }
}
```

---

## 7. Summary / Checklist

- [x] Configured Room Entity, DAO, and Database architecture.
- [x] Created repository layer abstraction.
- [x] Made `getItem(id: Int): Flow<Item?>` return type nullable to resolve Room 2.8.x crash.
- [x] Fully implemented `ItemEditViewModel` and `ItemEditScreen`.
- [x] Wired dependencies via `AppViewModelProvider`.
- [x] Verified app build and execution.
