# Unit 4: Cupcake App - Study and Technical Notes

## Summary and Concepts

The Cupcake app demonstrates a multi-screen ordering flow using Jetpack Navigation, the `SharedViewModel` pattern, and Android Share Intent integration.

### 1. Jetpack Navigation & NavHost
- **`NavHost` & `NavHostController`**: Manages transitions between Order Start (`Start`), Flavor Selection (`Flavor`), Pickup Date Selection (`Pickup`), and Order Summary (`Summary`) screens.
- **`currentBackStackEntryAsState()`**: Automatically updates the `CupcakeAppBar` title when the active route changes.

### 2. Shared State (Shared ViewModel)
- `OrderViewModel`: Holds quantity, flavor, pickup date, and calculated total price in common across screens.
- `cancelOrderAndNavigateToStart()`: Resets state upon order cancellation and navigates back to the start screen with `popBackStack(Start.name, inclusive = false)`.

### 3. Order Sharing (`Intent.ACTION_SEND`)
- Shares the order summary with external apps (WhatsApp, Email, etc.) using `Intent.createChooser`.
