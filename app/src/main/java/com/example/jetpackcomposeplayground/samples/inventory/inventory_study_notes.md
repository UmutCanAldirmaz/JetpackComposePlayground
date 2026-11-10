# Inventory App (Android Basics with Compose & Room) - Teknik Notlar ve Geliştirme Rehberi

Bu doküman, **Inventory (Envanter)** uygulamasının mimarisini, Room veritabanı entegrasyonunu, Jetpack Compose UI bileşenlerini, geliştirme sürecinde karşılaşılan teknik sorunları ve çözümlerini adım adım açıklamaktadır.

---

## 1. Uygulama Mimarisi (Architecture)

Uygulama, Google tarafından önerilen **Modern Android Uygulama Mimarisi (MAD)** prensiplerine uygun olarak 3 ana katmanda kurgulanmıştır:

```
[ UI Katmanı (Jetpack Compose) ]
             ▲
             │ (StateFlow / UI State)
             ▼
[ ViewModel Katmanı (State / Logic) ]
             ▲
             │ (ItemsRepository Interface)
             ▼
[ Veri Katmanı (OfflineItemsRepository + Room DAO) ]
             ▲
             │ (SQLite Queries)
             ▼
[ Room Veritabanı (InventoryDatabase + SQLite) ]
```

### Bağımlılık Enjeksiyonu (Manual Dependency Injection)
* **`AppContainer` & `AppDataContainer`**: Uygulama seviyesinde veritabanı örneğini (`InventoryDatabase`) ve veri deposunu (`OfflineItemsRepository`) tekil nesne (Singleton) olarak yönetir.
* **`AppViewModelProvider`**: `ViewModelProvider.Factory` kullanarak tüm ViewModel sınıflarına (`ItemEntryViewModel`, `ItemDetailsViewModel`, `ItemEditViewModel`, `HomeViewModel`) ihtiyaç duydukları `SavedStateHandle` ve `ItemsRepository` bağımlılıklarını güvenli bir şekilde sağlar.

---

## 2. Veri Katmanı (Data Layer & Room)

### `Item` Nesnesi (Entity)
Veritabanındaki `items` tablosunu temsil eden veri sınıfı:
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
Veritabanı sorgularının tanımlandığı arayüz.

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

## 3. Önemli Teknik Dersler ve Kritik Noktalar

### ⚠️ Critical Lesson: Room 2.8.x ve Nullability (`Flow<Item?>`) Mekanizması

#### Problem: `IllegalStateException: The query result was empty, but expected a single row to return a NON-NULL object of type Item`
Detay ekranında bir ürün silindiğinde veya veritabanında bulunmayan bir ID sorgulandığında uygulama çöküyordu.

#### Teknik Sebebi:
1. Room **2.6.x** sürümünden **2.8.x** sürümüne geçildiğinde KSP derleyicisi (codegen) Kotlin Nullability kurallarını katı bir şekilde uygulamaya başladı.
2. `getItem(id: Int)` metodu `Flow<Item>` (null olamaz) şeklinde tanımlandığında, Room derleyicisi `ItemDao_Impl.kt` içine *"Sorgu 0 satır döndürürse hata fırlat"* mantığı üretir.
3. Bir ürün silindiğinde Room veritabanı değişimini algılayıp `getItem` sorgusunu otomatik tekrar çalıştırır. Sorgu 0 satır döndürdüğü için Room anında `IllegalStateException` fırlatır.

#### Doğru Çözüm:
`ItemDao.kt` içerisindeki dönüş tipi `Flow<Item?>` (nullable) yapılmalıdır:
```kotlin
@Query("SELECT * from items WHERE id = :id")
fun getItem(id: Int): Flow<Item?>
```
* **Sonuç**: Ürün silindiğinde Room hata fırlatmak yerine `null` yayınlar.
* ViewModel katmanındaki `itemsRepository.getItemStream(itemId).filterNotNull()` operatörü bu `null` yayınını sessizce süzerek akışı güvenle tamamlar ve çökme engellenir.

---

### 🛡️ Girdi Doğrulama ve Negatif Stok / Fiyat Engelleme

Form ekranlarında (Ekleme ve Düzenleme) kullanıcının negatif stok miktarı veya negatif fiyat girmesini engellemek için 2 katmanlı doğrulama uygulanmıştır:

1. **ViewModel Katmanı (`validateInput`)**:
   ```kotlin
   private fun validateInput(uiState: ItemDetails = itemUiState.itemDetails): Boolean {
       return with(uiState) {
           val priceVal = price.toDoubleOrNull()
           val qtyVal = quantity.toIntOrNull()
           name.isNotBlank() && priceVal != null && priceVal >= 0.0 && qtyVal != null && qtyVal >= 0
       }
   }
   ```
   * Stok miktarının (`qtyVal >= 0`) ve fiyatın (`priceVal >= 0.0`) pozitif veya 0 olduğu doğrulanır. Negatif bir değer girildiğinde Kaydet butonu devre dışı kalır (`enabled = false`).

2. **UI Katmanı (`OutlinedTextField` Girdi Süzgeci)**:
   ```kotlin
   onValueChange = { input ->
       if (input.isEmpty() || input.all { it.isDigit() }) {
           onValueChange(itemDetails.copy(quantity = input))
       }
   }
   ```
   * Kullanıcının klavyeden stok alanına eksi (`-`) veya sayı harici karakter girmesi tamamen engellenmiştir.

---

## 4. ViewModel ve Ekran Mantıkları

### 1. `ItemEntryViewModel` (Ürün Ekleme)
* Kullanıcının girdiği Ad, Fiyat ve Stok miktarlarını `ItemUiState` içerisinde tutar.
* `validateInput()` ile girdi alanlarının boş olmadığını kontrol eder.
* `saveItem()` ile ürünü veritabanına ekler.

### 2. `ItemDetailsViewModel` (Ürün Detayı, Satma ve Silme)
* `getItemStream(itemId)` ile seçili ürünün canlı verisini `StateFlow` olarak yayınlar:
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
* **`SharingStarted.WhileSubscribed(5_000L)`**: Ekran döndürme gibi durumlarda abonelik kesildikten sonra 5 saniye boyunca veri akışını canlı tutarak gereksiz veritabanı sorgularını engeller.
* `reduceQuantityByOne()`: Stok miktarını 1 azaltarak günceller.
* `deleteItem()`: Ürünü veritabanından siler.

### 3. `ItemEditViewModel` (Ürün Düzenleme)
* `SavedStateHandle` üzerinden `itemIdArg` bağımsız değişkenini alır.
* `init` bloğunda ürünün mevcut verilerini veritabanından çeker ve form alanlarına doldurur:
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
* `updateUiState()`: Form alanı her değiştiğinde geçerlilik kontrolü yapar.
* `updateItem()`: Güncellenmiş ürün bilgilerini veritabanına kaydeder.

---

## 5. UI ve Navigasyon Yapısı (`InventoryNavGraph`)

Navigasyon 4 temel rota üzerinden yönetilir:

1. **`HomeDestination` (`"home"`)**:
   - Tüm ürünlerin alfabetik listelendiği ana ekran (`HomeScreen`).
   - Yeni ürün ekleme ekranına geçiş FAB butonu barındırır.
2. **`ItemEntryDestination` (`"item_entry"`)**:
   - Yeni ürün ekleme formu (`ItemEntryScreen`).
3. **`ItemDetailsDestination` (`"item_details/{itemId}"`)**:
   - Ürün detayları, Sat, Sil ve Düzenle butonlarını barındıran ekran (`ItemDetailsScreen`).
4. **`ItemEditDestination` (`"item_edit/{itemId}"`)**:
   - Var olan ürünü düzenleme formu (`ItemEditScreen`).

---

## 6. Birim Testleri (`ItemDaoTest`)

Bellek içi (in-memory) veritabanı kullanılarak veritabanı operasyonları test edilir:

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
    // Ekleme, Güncelleme, Silme ve Getirme test senaryoları...
}
```

---

## 7. Özet / Checklist

- [x] Room Entity, DAO ve Database yapısı kuruldu.
- [x] Repository katmanı ile soyutlama yapıldı.
- [x] `ItemDao` içinde `getItem(id: Int): Flow<Item?>` dönüş tipi nullable yapılarak Room 2.8.x crash problemi çözüldü.
- [x] `ItemEditViewModel` ve `ItemEditScreen` tam fonksiyonel hale getirildi.
- [x] `AppViewModelProvider` ile bağımlılıklar sağlandı.
- [x] Uygulama derleme ve çalıştırma testleri başarıyla doğrulandı.
