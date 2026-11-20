# Sports App — Adaptive List and Detail Notes

This document contains study notes for the **Sports** app, an adaptive layout exercise from Google's **Android Basics with Compose** course. The app is launched via `SportsSampleScreen` from the playground catalog.

---

## 1. Project Goal and Scope

Sports is a sample app displaying a list of sports and details for the selected sport. The goal is to run the exact same screen on both phone displays and wide windows:

* In narrow windows, the list is displayed first; clicking a card navigates to details.
* In wide windows, the list and detail panes are shown side-by-side.

Unlike the Reply app, Sports does not swap bottom bars, navigation rails, or drawers. It only determines content layout as `ListOnly` or `ListAndDetail`.

---

## 2. Core Requirements and Dependencies

* **Jetpack Compose and Material 3:** `Scaffold`, `TopAppBar`, `Card`, `LazyColumn`.
* **Window Size Classes (`androidx.compose.material3:material3-window-size-class`):** For reading window width as `Compact`, `Medium`, and `Expanded`.
* **ViewModel and StateFlow:** Preserving selected sport and list/detail page state independently of lifecycle changes.
* **Resource Files:** Strings in `res/values/sports_strings.xml`, dimensions in `res/values/sports_dimens.xml`, drawables in `res/drawable/ic_*_square.xml` and `ic_*_banner.xml`.

---

## 3. File Structure

| File | Purpose |
| --- | --- |
| `SportsSampleScreen.kt` | Catalog entry point. Evaluates window size class, launches `SportsTheme` and `SportsApp`. |
| `ui/SportsScreens.kt` | Composables for list, detail, and two-pane layouts. |
| `ui/SportsViewModel.kt` | Manages `SportsUiState` and list/detail page transitions. |
| `model/Sport.kt` | Sport data model. Stores text and image references as resource IDs. |
| `data/LocalSportsDataProvider.kt` | Initial list of sports. |
| `utils/WindowStateUtils.kt` | `SportsContentType`: `ListOnly`, `ListAndDetail`. |
| `ui/theme/` | Sports-specific colors, typography, and `SportsTheme`. |

---

## 4. Step-by-Step Flow

### Step 1: Data Model

`Sport` represents a sport: ID, title, subtitle, player count, Olympic status, list icon, banner image, and detail text. Strings are stored as `@StringRes` and drawables as `@DrawableRes`. Thus, screens use `stringResource` and `painterResource` instead of hardcoded strings.

`LocalSportsDataProvider` populates this model. Default selection is the first item in the list.

### Step 2: State

`SportsUiState` holds three fields:

* `sportsList`: sports to display
* `currentSport`: currently selected sport
* `isShowingListPage`: whether list or detail page is visible in narrow windows

`SportsViewModel` updates state inside `MutableStateFlow`:

* `updateCurrentSport`: writes selected card
* `navigateToDetailPage`: `isShowingListPage = false`
* `navigateToListPage`: `isShowingListPage = true`

The screen observes this flow using `collectAsState()`.

### Step 3: Content Based on Window Width

`SportsSampleScreen` fetches width class via `calculateWindowSizeClass`. Falls back to `Compact` if Activity is unavailable.

`SportsApp` maps width class to content layout:

* **Compact and Medium:** `SportsContentType.ListOnly`
* **Expanded:** `SportsContentType.ListAndDetail`

Under `ListOnly`, if `isShowingListPage` is true, `SportsList` is rendered full-screen; if false, `SportsDetail` is rendered full-screen. Clicking a card updates selection and navigates to details.

Under `ListAndDetail`, `SportsListAndDetail` constructs a `Row`. The list takes `weight(2f)` and detail takes `weight(3f)`. Clicking a card updates `currentSport` without page switching.

### Step 4: Top Bar and Back Navigation

Top bar title displays `sports_detail_header` on narrow detail view and `sports_list_header` otherwise. Back arrow appears only in non-Expanded windows while viewing detail; pressing it returns to list.

System back gesture:

* Returns to list when detail is open in narrow windows (`BackHandler` inside `SportsDetail`).
* Executes catalog `onBackPressed` to return to main catalog when list is visible or window is expanded. `SportsSampleScreen` maintains its own `BackHandler` registration.

### Step 5: List Card and Detail View

Card displays square thumbnail image, title, subtitle, `sports_player_count` plural text, and `sports_olympic_caption` if the sport is Olympic. Corner radius, elevation, and padding values are read from `sports_dimens.xml`.

Detail screen renders a gradient overlay beneath the banner image, overlays title and player count, and displays `sport_detail_text` in a scrollable column.

---

## 5. Key Concepts Learned

1. **Window Size Classes:** Layout selection is based on available window width rather than device name. Two-pane layout opens only in `Expanded` windows; `Medium` remains single-column.
2. **List–Detail Pattern:** Displaying data sequentially on narrow screens and side-by-side on wide screens. Selected item resides in `currentSport` in both layouts.
3. **Unidirectional Data Flow:** Composables do not store state internally. Click events call ViewModel functions, re-rendering UI with fresh `SportsUiState`.
4. **Resource IDs:** Model stores `R.string` and `R.drawable` instead of raw strings or paths. Text/dimension changes are edited in `sports_strings.xml` and `sports_dimens.xml` without modifying Kotlin code.
5. **`findActivity`:** `LocalContext` can be a `ContextWrapper`. A `tailrec` loop unwraps the underlying `Activity` instance for `calculateWindowSizeClass`.
6. **Previews:** `SportsAppCompactPreview` (400dp) previews single-column layout, while `SportsAppExpandedPreview` (1000dp) previews list-detail layout in Android Studio.
