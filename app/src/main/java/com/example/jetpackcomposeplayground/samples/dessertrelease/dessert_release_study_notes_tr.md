# Unit 6: Dessert Release App - DataStore & Preferences Notları

## Özet ve Konseptler

Dessert Release uygulaması, kullanıcı tercihlerinin (örneğin düzen modunun: Grid vs. Linear) `androidx.datastore:datastore-preferences` kullanılarak cihaz hafızasında güvenli ve asenkron (Flow/Coroutines) olarak saklanmasını öğretir.

### Key Points
1. **DataStore Preferences**: SharedPreferences yerine modern, Kotlin Coroutines ve Flow altyapısı sunan key-value saklama kütüphanesidir.
2. **UserPreferencesRepository**: Tercihlerin yazılması (`edit { ... }`) ve okunması (`data: Flow<UserPreferences>`) repository katmanında kapsüllenir.
3. **ViewModel Integration**: StateFlow ve `UserPreferencesRepository` ile UI state reaktif olarak beslenir.
