# Unit 4: Lifecycle Counter Exercise - Study and Technical Notes

## Summary and Concepts

The Lifecycle Counter application demonstrates Android Lifecycle-aware `StateFlow` subscriptions and Recomposition tracking.

### 1. Lifecycle-Aware State Stream
- **`collectAsStateWithLifecycle()`**: Pauses `StateFlow` collection when the application goes into the background (reaches the `STOPPED` lifecycle state). This avoids unnecessary CPU consumption and memory leaks in the background.

### 2. Side Effects and Recomposition Tracking
- **`SideEffect { recompositionCount++ }`**: Triggers a side effect on every recomposition to measure how many times the UI has re-rendered.
- **`LaunchedEffect(count)`**: Writes a debug log to the Logcat console whenever the counter value changes.
