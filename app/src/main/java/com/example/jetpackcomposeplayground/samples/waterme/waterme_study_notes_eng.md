# Unit 7: Water Me! App - WorkManager & Notification Study Notes

This technical study document details building delayed background schedules using **WorkManager**, passing payload data to Workers via `Data` objects, posting system notifications via `NotificationManager`, and integrating this infrastructure with **Jetpack Compose**.

---

## 📌 1. How the App Works Step-by-Step (Workflow)

The Water Me! app enables users to set plant watering reminders and receive system notifications via WorkManager when duration expires:

### 🔄 App Execution Workflow Table

| Step | Stage / Component | Operation Performed |
| :---: | :--- | :--- |
| **1** | **Plant Selection** | User taps a plant from the list to set a watering reminder. |
| **2** | **Reminder Dialog** | `ReminderDialogContent` opens; user chooses duration (5 seconds, 1 minute, 2 minutes, 3 minutes). |
| **3** | **`WaterViewModel`** | `scheduleReminder(reminder)` is called, forwarding request to Repository layer. |
| **4** | **`WorkManagerWaterRepository`** | Creates delayed work request (`setInitialDelay`) using `OneTimeWorkRequestBuilder<WaterReminderWorker>()`. |
| **5** | **Unique Work Queue** | Queues work via `workManager.enqueueUniqueWork(...)` using `ExistingWorkPolicy.REPLACE`. |
| **6** | **`WaterReminderWorker`** | When timer expires, `doWork()` triggers in background, reading plant name from `inputData`. |
| **7** | **System Notification** | Calls `makePlantReminderNotification()` to deliver high-priority watering notification to device. |
| **8** | **Notification Tap** | Tapping notification safely re-opens `MainActivity` via `PendingIntent`. |

---

## 🛠️ 2. Architecture and Core Concepts

### 1. Delayed Scheduling with WorkManager (`setInitialDelay`)
- **`OneTimeWorkRequestBuilder`**: Constructs one-off background tasks.
- **`setInitialDelay(duration, unit)`**: Defers execution by specified delay (e.g. 5 seconds or 1 minute).
- **`setInputData(data)`**: Uses `Data.Builder().putString(nameKey, plantName).build()` to pass payload to Worker.
- **`ExistingWorkPolicy.REPLACE`**: Replaces and cancels existing active work when a new reminder is scheduled for the same plant and duration.

### 2. `WaterReminderWorker` (`CoroutineWorker`)
- Executes asynchronous background task inside `doWork()`.
- Retrieves plant name passed during scheduling via `inputData.getString(nameKey)`.
- Returns `Result.success()` upon successful execution.

### 3. NotificationChannel and Posting Notifications
- **Android 8.0+ (API 26+) Compatibility**: Configures `NotificationChannel` with `IMPORTANCE_HIGH`.
- **`NotificationCompat.Builder`**: Configures icon, title, text, vibration settings, and tap intent.
- **`PendingIntent` Security**: Applies `PendingIntent.FLAG_IMMUTABLE` flag compliant with Android 12+ (API 31+) security rules.

### 4. Manual Dependency Injection (AppContainer)
- Initializes `WorkManagerWaterRepository` through `AppContainer` interface and `DefaultAppContainer` class.
- Passes `PlaygroundApplication` context reactively to ViewModel via `WaterViewModel.Factory`.
