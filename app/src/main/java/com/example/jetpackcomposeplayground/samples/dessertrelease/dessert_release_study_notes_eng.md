# Unit 6: Dessert Release App - DataStore & Preferences Notes

## Summary and Concepts

The Dessert Release app teaches how to store user preferences (such as layout mode: Grid vs. Linear) securely and asynchronously (Flow/Coroutines) in persistent storage using `androidx.datastore:datastore-preferences`.

### Key Points
1. **DataStore Preferences**: A key-value storage library built on Kotlin Coroutines and Flow, serving as a modern replacement for SharedPreferences.
2. **UserPreferencesRepository**: Encapsulates writing preferences (`edit { ... }`) and reading preferences (`data: Flow<UserPreferences>`) in the repository layer.
3. **ViewModel Integration**: UI state is reactively supplied using StateFlow and `UserPreferencesRepository`.
