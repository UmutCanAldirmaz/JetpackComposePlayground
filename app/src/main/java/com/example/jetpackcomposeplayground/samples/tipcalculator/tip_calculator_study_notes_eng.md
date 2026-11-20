# Unit 2: Tip Calculator App - Study and Technical Notes

## Summary and Concepts

The Tip Calculator application covers capturing user inputs (Forms & Input), keyboard type configurations, state preservation (`rememberSaveable`), and calculation logic.

### 1. Form and Keyboard Management
- **`OutlinedTextField`**: Receives text inputs for bill amount (`amountInput`), tip percentage (`tipInput`), and person count (`personCountInput`).
- **`KeyboardOptions(keyboardType = KeyboardType.Number)`**: Displays a numeric keyboard to the user.
- **`rememberSaveable`**: Preserves text input states and Switch toggle state across configuration changes (e.g., screen rotation).

### 2. Calculation and Rounding Logic
- **`calculateTip`**: Computes tip per person based on bill amount and tip percentage.
- **`ceil()` & `Switch`**: When the tip rounding toggle is enabled, rounds tip up to the nearest integer using `ceil()`.
- **`NumberFormat.getCurrencyInstance()`**: Formats output according to local currency standards (₺, $, €, etc.).

### 3. Unit Testing Layer
- The `@VisibleForTesting internal fun calculateTip(...)` method can be verified independently via unit tests.
