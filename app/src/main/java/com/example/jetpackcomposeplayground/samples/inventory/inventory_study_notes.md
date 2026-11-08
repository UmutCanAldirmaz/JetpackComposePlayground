# Unit 6: Inventory App (Persist Data with Room - Starter) Study Notes

## Özet ve Konseptler

1. **Room & Repository Altyapısı**:
   - `Item`: Envanterdeki ürünleri temsil eden veri sınıfı (Starter kodunda henüz `@Entity` anotasyonu eklenmemiş durumdadır).
   - `ItemsRepository`: Veri katmanı soyutlaması için kullanılan arabirim.
   - `OfflineItemsRepository`: `ItemsRepository` arayüzünün uygulama içi gerçekleştirmesi.
   - `AppContainer` & `AppDataContainer`: Bağımlılık enjeksiyonu (Manual Dependency Injection) ve repository erişimi.

2. **Ekran Yapıları & Navigasyon**:
   - `InventoryNavHost`: `NavHostController` ile `HomeScreen`, `ItemEntryScreen`, `ItemDetailsScreen` ve `ItemEditScreen` ekranları arası yönlendirme.
   - `AppViewModelProvider`: Her bir ViewModel için `SavedStateHandle` ve bağımlılık fabrikaları (`ViewModelFactory`).
