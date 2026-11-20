# Reply App — Development and Adaptive Layout Notes

This document contains study and learning notes for the **Reply** sample app developed under Unit 4 of Google's **Android Basics with Compose** course.

---

## 1. Project Goal and Scope
Reply is a sample email client app for managing emails. The main objective of this project is to build a **Responsive / Adaptive** UI architecture that dynamically adjusts to **different screen sizes (phones, tablets, foldables, and desktop)** instead of relying on rigid, single-layout screens.

---

## 2. Core Requirements and Dependencies
* **Jetpack Compose & Material 3:** Modern, declarative UI components and Material Design 3 color/typography systems.
* **Window Size Classes (`androidx.compose.material3.windowsizeclass`):** Library used to categorize screen width as Compact, Medium, or Expanded.
* **Architecture Components (`ViewModel`, `StateFlow`, `State`):** Managing application state (`ReplyUiState`) in alignment with the Android lifecycle and synchronizing it with the UI.

---

## 3. Step-by-Step Development Stages

### Step 1: Data Models and Providers (Data Layer)
* **`Account.kt` & `Email.kt`**: Core data classes representing user profiles and email messages.
* **`MailboxType.kt`**: An `enum` class specifying email mailbox tabs (`Inbox`, `Sent`, `Drafts`, `Spam`).
* **`LocalAccountsDataProvider.kt` & `LocalEmailsDataProvider.kt`**: Static data sources providing initial data for UI previews and testing.

### Step 2: State Management and Business Logic (ViewModel & UI State)
* **`ReplyUiState.kt`**: State class tracking current mailbox (`currentMailbox`), selected email (`currentSelectedEmail`), and homepage visibility (`isShowingHomepage`).
* **`ReplyViewModel.kt`**: ViewModel handling user interactions (tab switching, email selection, navigating back) and exposing state changes to UI via `StateFlow`.

### Step 3: Adaptive Navigation Architecture (Adaptive Navigation)
* **`WindowStateUtils.kt`**: 
  - `ReplyNavigationType`: `BOTTOM_NAVIGATION`, `NAVIGATION_RAIL`, `PERMANENT_NAVIGATION_DRAWER`
  - `ReplyContentType`: `LIST_ONLY`, `LIST_AND_DETAIL`
* **`ReplyApp.kt`**: Evaluates device `WindowWidthSizeClass` to determine appropriate navigation type and content layout:
  - **Compact (< 600dp - Phone):** `BOTTOM_NAVIGATION` + `LIST_ONLY`
  - **Medium (600dp - 840dp - Tablet):** `NAVIGATION_RAIL` + `LIST_ONLY`
  - **Expanded (> 840dp - Wide Screen):** `PERMANENT_NAVIGATION_DRAWER` + `LIST_AND_DETAIL`

### Step 4: Dynamic Content and Two-Pane Layout
* **`ReplyHomeScreen.kt` & `ReplyHomeContent.kt`**:
  - On phones and tablets, list and detail screens are displayed full-screen sequentially (`LIST_ONLY`).
  - On wide screens, **`ReplyListAndDetailContent`** takes effect; placing email list on the left and selected email details (`ReplyDetailsScreen`) on the right in a side-by-side two-column layout (`Row` + `weight`).

---

## 4. Key Concepts Learned and Best Practices

1. **Window Size Classes:**
   Designing UI based on available window width rather than physical hardware dimensions (Compact, Medium, Expanded).
2. **State Hoisting & Unidirectional Data Flow:**
   Managing state in the `ViewModel` while rendering UI components (`Stateless Composables`) strictly according to state emissions.
3. **Responsive / Adaptive UI Design:**
   Delivering excellent user experience across phones, tablets, and desktops using a single unified codebase.
4. **Context & Activity Resolution (`findActivity`):**
   Techniques for safely accessing `Activity` and window metrics inside Jetpack Compose by traversing `ContextWrapper` layers.
