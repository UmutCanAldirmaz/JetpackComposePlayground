# Mars Photos App - Study and Learning Notes

## Project Goal
The Mars Photos application was developed for Unit 5 of the Android Basics with Compose course to learn fetching web data via REST API using **Retrofit**, **kotlinx.serialization**, and processing images asynchronously using **Coil**.

## Key Learnings and Techniques
1. **Retrofit and REST API Integration:**
   - Defining `@GET("photos")` HTTP requests via `MarsApiService` interface.
   - Configuring `baseUrl` and deserializer (`ConverterFactory`) via `Retrofit.Builder`.
2. **JSON Parsing with kotlinx.serialization:**
   - Parsing JSON responses into Kotlin data objects (`MarsPhoto`).
   - Mapping JSON field names to Kotlin property names using `@Serializable` and `@SerialName(value = "img_src")`.
3. **Asynchronous Image Loading with Coil:**
   - Loading images via URL on screen, displaying placeholder drawables during loading and error icons upon failure.
4. **UI State Management (Sealed Interface):**
   - Representing all UI states using `MarsUiState` (`Loading`, `Success`, `Error`).
   - Providing a "Retry" action button to the user upon error states.
5. **Repository Pattern and Manual Dependency Injection (DI):**
   - Decoupling data sources from ViewModel via `MarsPhotosRepository` interface and `NetworkMarsPhotosRepository` implementation.
   - Initializing dependencies using `AppContainer` interface and `DefaultAppContainer`.

## Manual DI and Playground Integration
- Standalone `MarsPhotosApplication` class was removed as all samples are hosted within **JetpackComposePlayground**.
- All dependencies are supplied via `marsPhotosContainer` inside central [PlaygroundApplication](file:///C:/Users/Umut/AndroidStudioProjects/JetpackComposePlayground/app/src/main/java/com/example/jetpackcomposeplayground/PlaygroundApplication.kt).
- `MarsViewModel.Factory` casts `APPLICATION_KEY` context to `PlaygroundApplication` to inject `MarsPhotosRepository`.
