# Amphibians App - Study and Learning Notes

## Project Goal
The Amphibians application is a mini-project developed to reinforce concepts learned in Unit 5 of the Android Basics with Compose course. It fetches data and images about amphibians (frogs, salamanders, etc.) via a REST API and displays them in a list.

## Key Learnings and Techniques
1. **Network Service Configuration:**
   - Making `@GET("amphibians")` requests via `AmphibiansApiService`.
   - Parsing the JSON response into a list of `Amphibian` models using Retrofit + `kotlinx.serialization` integration.
2. **Repository Pattern:**
   - Decoupling data access from the ViewModel using the `AmphibiansRepository` interface and `DefaultAmphibiansRepository` class.
3. **UI State and Asynchronous State Management:**
   - Reflecting the real-time application state on the Jetpack Compose screen using the `AmphibiansUiState` structure (`Loading`, `Success`, `Error`).
4. **Material 3 Card List Design:**
   - Card layout containing amphibian name, type, and description using `LazyColumn`, `Card`, `Text`, and `AsyncImage` components.

## Manual DI and Playground Integration
- The standalone `AmphibiansApplication` class was removed to avoid application-level conflicts in the project.
- All dependencies are managed via `amphibiansContainer` inside the main [PlaygroundApplication](file:///C:/Users/Umut/AndroidStudioProjects/JetpackComposePlayground/app/src/main/java/com/example/jetpackcomposeplayground/PlaygroundApplication.kt) class.
- Repository injection is performed inside `AmphibiansViewModel.Factory` by casting the application context to `PlaygroundApplication`.

## Unit Tests
- `FakeDataSource`: Provides fake `Amphibian` list objects for testing environments.
- `FakeAmphibiansApiService`: Fake Retrofit service mocking network calls.
- `FakeAmphibiansRepository`: Fake repository implementation mocking the repository layer.
- `TestDispatcherRule`: Manages `Dispatchers.Main` for coroutine testing.
- `AmphibiansViewModelTest`: Verifies that calling `getAmphibians()` transitions ViewModel to `AmphibiansUiState.Success` with correct data.
- `NetworkAmphibiansRepositoryTest`: Verifies that `DefaultAmphibiansRepository` returns data received from the network service correctly.
