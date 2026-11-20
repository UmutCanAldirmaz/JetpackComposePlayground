# Unit 5: Race Tracker App - Study and Technical Notes

## Summary and Concepts

The Race Tracker app demonstrates concurrent race simulation using Kotlin Coroutines, `coroutineScope`, `launch`, `delay`, and coroutine cancellation/pausing.

### 1. Concurrent Coroutine Execution (`coroutineScope` & `launch`)
- **`coroutineScope` & `launch`**: Runs race steps for both participants (`playerOne`, `playerTwo`) as parallel coroutines.
- **`delay(progressDelayMillis)`**: Simulates asynchronous race progress by adding delays between steps.

### 2. Pause and Cancellation Mechanism
- When `raceInProgress` is `false` or the screen closes, the `LaunchedEffect` coroutine scope is automatically paused or canceled.

### 3. UI Progress Indicators
- `LinearProgressIndicator(progress = { progressFactor })`: Reactively updates progress bars.
