# Blur-O-Matic — WorkManager and Background Work Comprehensive Study Notes

This document is a study guide detailing how to schedule guaranteed background work using Android's **WorkManager** library, build sequential work chains, set constraints, pass data across threads, and reactively display these processes on screen using **Jetpack Compose**.

---

## 📌 1. How the App Works Step-by-Step (Workflow)

The Bluromatic app features a sequential background work chain that applies a blur filter to a selected image and saves the output to the device's media gallery:

### 🔄 App Execution Workflow Table

| Step | Stage / Component | Operation Performed |
| :---: | :--- | :--- |
| **1** | **User Interaction** | User selects a blur level (1, 2, or 3) and taps **"Start"**. |
| **2** | **`BlurViewModel`** | Invokes `applyBlur(blurLevel)` and forwards the request to the Repository layer. |
| **3** | **`WorkManagerRepository`** | Starts the **`IMAGE_MANIPULATION_WORK_NAME`** chain via `WorkManager.beginUniqueWork(...)`. |
| **4** | **Step 1: `CleanupWorker`** | Deletes old temporary `.png` files from the internal app directory (`filesDir`). |
| **5** | **2nd Step: `BlurWorker`** | Applies the chosen blur level to the image if battery constraints (`setRequiresBatteryNotLow`) are met. |
| **6** | **3rd Step: `SaveImageToFileWorker`** | Worker tagged with `TAG_OUTPUT` saves the processed image to device media storage (MediaStore API). |
| **7** | **WorkManager Output** | Returns the permanent image URI inside the `outputData` bundle. |
| **8** | **`BlurViewModel` Flow** | Listens to `outputWorkInfo` and updates UI state to `BlurUiState.Complete(outputUri)`. |
| **9** | **`BluromaticScreen` (UI)** | Displays **"See File"** button; tapping opens the image in the default gallery app. |

**Work Chain Execution Sequence:**
`User Level Selection` ➔ `BlurViewModel.applyBlur()` ➔ `CleanupWorker` ➔ `BlurWorker` ➔ `SaveImageToFileWorker` ➔ `BlurUiState.Complete` ➔ `View Image in Gallery`

1. **Input and Level Selection:**
   - User selects a blur level via radio buttons on screen (Level 1, 2, or 3).
   - Tapping the "Start" button triggers `BlurViewModel.applyBlur(blurLevel)`.

2. **Repository and Work Chain Construction:**
   - `WorkManagerBluromaticRepository` initiates a unique work chain named **`IMAGE_MANIPULATION_WORK_NAME`** using `WorkManager.beginUniqueWork()`.
   - The `ExistingWorkPolicy.REPLACE` policy ensures any existing running work is canceled and replaced with the new request.

3. **Work Chain Steps:**
   - **Step 1 - `CleanupWorker`:** Cleans up temporary `.png` files left from previous operations in internal storage (`filesDir/blur_filter_outputs`).
   - **Step 2 - `BlurWorker`:** 
     - Reads image URI and selected blur level from input data (`inputData`).
     - Subject to `setRequiresBatteryNotLow(true)` constraint; if device battery is low, execution pauses until connected to power.
     - Blurs the image, writes it to a temporary file, and passes temporary file URI to the next step via `workDataOf(KEY_IMAGE_URI to outputUri)`.
   - **Step 3 - `SaveImageToFileWorker`:**
     - Tagged with `TAG_OUTPUT`.
     - Reads temporary image output from the previous step.
     - **Android 10+ (API 29+):** Saves image to device `Pictures/Blur-O-Matic` MediaStore gallery using `CreateImageUriUseCase` and `SaveImageUseCase` adhering to Scoped Storage.
     - **Android 9 & earlier:** Saves image via `MediaStore.Images.Media.insertImage`.
     - Returns permanent gallery URI in `outputData`.

4. **Reactive UI State Updates:**
   - `LiveData<List<WorkInfo>>` emitted by WorkManager is converted to Kotlin Flow in the Repository layer via `asFlow()`.
   - Exposed to UI as `StateFlow<BlurUiState>` in `BlurViewModel` using the `stateIn` operator.
   - While work is running, UI transitions to `BlurUiState.Loading`; showing a "Cancel Work" button and a `CircularProgressIndicator`.
   - Upon completion, UI transitions to `BlurUiState.Complete(outputUri)`, displaying the "See File" button. Tapping it launches an implicit Intent (`ACTION_VIEW`) to view the image.

---

## 🛠️ 2. Key Technical Concepts Taught by This Project

1. **Guaranteed Background Work Management (WorkManager):**
   - Managing tasks guaranteed to complete even if the app closes or the device restarts.
2. **Sequential Work Chains (Work Chaining & WorkContinuation):**
   - Executing dependent tasks sequentially via `beginUniqueWork().then().then().enqueue()`.
3. **Execution Constraints (`Constraints`):**
   - Restricting task execution based on battery status, network connectivity, or storage (`Constraints.Builder`).
4. **Inter-Thread Data Passing (Data Input/Output):**
   - Passing data safely between Workers using `Data.Builder` and `inputData` / `outputData`.
5. **Flow and LiveData Interoperability:**
   - Converting WorkManager's default `LiveData` output to modern Kotlin Coroutine Flow using `asFlow()`.
6. **Rotation-Safe StateFlow (`WhileSubscribed(5_000)`):**
   - Preventing flow from unnecessarily resetting during configuration changes (orientation change) using `SharingStarted.WhileSubscribed(5_000)`.
7. **Scoped Storage and MediaStore Integration:**
   - Saving media files compliant with Scoped Storage guidelines on Android 10+ using `ContentValues` and `ContentResolver`.
8. **Manual Dependency Injection:**
   - Managing Repository instances as dependencies using `AppContainer` and `ViewModelProvider.Factory`.

---

## 📘 3. Detailed Explanation of Technical Concepts and Architecture Components

### A. Core WorkManager Components

#### 1. `WorkManager`
Android Jetpack architectural component for deferrable and guaranteed background jobs. Accessed via `WorkManager.getInstance(context)`.

#### 2. `CoroutineWorker`
Kotlin Coroutines-enabled derivative of `Worker`. `doWork()` is a `suspend` function that executes heavy background work safely on `Dispatchers.IO`.

Returns 3 possible results:
- `Result.success(outputData)`: Work completed successfully (with optional output data).
- `Result.failure()`: Work failed and will not be retried.
- `Result.retry()`: Work failed and should be retried based on backoff constraints.

#### 3. `WorkRequest` and `OneTimeWorkRequestBuilder`
Specifies when and how a Worker should run:
- `OneTimeWorkRequest`: Used for one-off tasks.
- `PeriodicWorkRequest`: Used for repeating tasks at set intervals (minimum interval is 15 minutes).
- `addTag(tag)`: Tags work for grouping or tracking.

#### 4. Data Size Limit (`Data`)
`Data` object stores key-value pairs. **Important Constraint:** Maximum payload size of `Data` is **10 KB**. Therefore, file URI strings are passed instead of raw media bytes (Bitmap/ByteArray).

```kotlin
val outputData = workDataOf(KEY_IMAGE_URI to outputUri.toString())
```

#### 5. Constraints (`Constraints`)
Ensures work runs only when specific device conditions are met:

```kotlin
val constraints = Constraints.Builder()
    .setRequiresBatteryNotLow(true) // Run only if battery is not low
    .setRequiresCharging(true)       // Run only while charging
    .build()
```

#### 6. Unique Work Policies (`ExistingWorkPolicy`)
Behaviors when naming unique work with `beginUniqueWork`:
- `REPLACE`: Cancels active existing work and starts new work.
- `KEEP`: Ignores new work request if active work exists.
- `APPEND` / `APPEND_OR_REVISE`: Chains new work behind existing work.

---

### B. Project Code Class Analysis

#### 1. `WorkManagerBluromaticRepository.kt`
The central hub constructing the background work chain.

```kotlin
override fun applyBlur(blurLevel: Int) {
    // 1. Cleanup Task
    var continuation = workManager.beginUniqueWork(
        IMAGE_MANIPULATION_WORK_NAME,
        ExistingWorkPolicy.REPLACE,
        OneTimeWorkRequest.from(CleanupWorker::class.java)
    )

    // 2. Constraint Definition
    val constraints = Constraints.Builder()
        .setRequiresBatteryNotLow(true)
        .build()

    // 3. Blur Work Request
    val blurBuilder = OneTimeWorkRequestBuilder<BlurWorker>()
    blurBuilder.setInputData(createInputDataForWorkRequest(blurLevel, imageUri))
    blurBuilder.setConstraints(constraints)

    // Append to chain
    continuation = continuation.then(blurBuilder.build())

    // 4. Save Work Request
    val save = OneTimeWorkRequestBuilder<SaveImageToFileWorker>()
        .addTag(TAG_OUTPUT)
        .build()
    continuation = continuation.then(save)

    // Enqueue chain
    continuation.enqueue()
}
```

- **`getWorkInfosByTagLiveData(TAG_OUTPUT).asFlow()`:** Listens to state changes of `SaveImageToFileWorker` specifically using `TAG_OUTPUT`. Converts LiveData to Coroutine Flow using `asFlow()`.

#### 2. `BlurViewModel.kt`
Transforms `WorkInfo` stream from Repository into UI state (`BlurUiState`).

```kotlin
val blurUiState: StateFlow<BlurUiState> = bluromaticRepository.outputWorkInfo
    .map { info ->
        val outputImageUri = info.outputData.getString(KEY_IMAGE_URI)
        when {
            info.state.isFinished && !outputImageUri.isNullOrEmpty() -> {
                BlurUiState.Complete(outputUri = outputImageUri)
            }
            info.state == WorkInfo.State.CANCELLED -> {
                BlurUiState.Default
            }
            else -> BlurUiState.Loading
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = BlurUiState.Default
    )
```

- **`SharingStarted.WhileSubscribed(5_000)`:** Stops listening when screen (Compose) is hidden. Provides a 5-second timeout window to prevent flow cancellation during device orientation changes.

#### 3. UseCase Structure (`CreateImageUriUseCase.kt` & `SaveImageUseCase.kt`)
Modularizes gallery writing according to **Scoped Storage** requirements on Android 10+ (API 29+):
- `CreateImageUriUseCase`: Creates new media entry via `MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY)` (`IS_PENDING = 1`).
- `SaveImageUseCase`: Writes JPEG stream via `ContentResolver.openOutputStream` and marks `IS_PENDING = 0`.

#### 4. `BluromaticScreen.kt`
Compose UI interface rendering dynamic elements based on `blurUiState`:
- **`BlurActions`:**
  - `BlurUiState.Default`: "Start" button active.
  - `BlurUiState.Loading`: "Cancel Work" button & `CircularProgressIndicator` active.
  - `BlurUiState.Complete`: "Start" and "See File" buttons active.

---

## 📝 Summary and Checklist

| Topic | Project Implementation |
| :--- | :--- |
| **Worker Class** | `BlurWorker`, `CleanupWorker`, `SaveImageToFileWorker` |
| **Work Chaining** | `workManager.beginUniqueWork(...).then(...).then(...)` |
| **Constraints** | `Constraints.Builder().setRequiresBatteryNotLow(true)` |
| **Data Transfer** | `workDataOf(KEY_IMAGE_URI to outputUri)` |
| **Work Cancellation** | `workManager.cancelUniqueWork(...)` |
| **Reactive State Tracking** | `getWorkInfosByTagLiveData().asFlow().map { ... }.stateIn(...)` |
| **MediaStore (Scoped Storage)** | `CreateImageUriUseCase` & `SaveImageUseCase` (API 29+) |

This study guide completely covers the architecture, background processes, and modern Kotlin Compose integration of the Blur-O-Matic project.
