# Unit 4: Dessert Clicker App - Study and Technical Notes

## Summary and Concepts

The Dessert Clicker app demonstrates Android Lifecycle handling, state management with ViewModel, and sharing data to external apps via Intent.

### 1. Android Lifecycle and State Handling
- `DessertClickerViewModel`: Tracks the number of desserts sold (`dessertsSold`) and revenue earned (`revenue`).
- `collectAsStateWithLifecycle()`: Safely listens to `StateFlow` data in Compose in a lifecycle-aware manner.

### 2. Sharing with External Apps (`Intent.ACTION_SEND`)
- `shareSoldDessertsInformation()`: Uses `Intent(Intent.ACTION_SEND)` and `Intent.createChooser()` to let users share sold dessert statistics via messaging or social media apps.
