# Unit 2: Dice Roller App - Study and Technical Notes

## Summary and Concepts

The Dice Roller app demonstrates fundamental state management principles and interactive button click handling in Jetpack Compose.

### 1. State Management
- **`remember { mutableStateOf(1) }`**: Retains the dice roll result (`diceResult`) in memory across Compose Recomposition cycles.
- **`Random.nextInt(1, 7)`**: Generates a random integer between 1 and 6 when the button is tapped and updates the state.

### 2. Image Resources and Conditional Rendering
- The `when (diceResult)` block determines the corresponding `R.drawable.dice_x` image resource based on the dice result.
- `Image(painter = painterResource(id = imageResource))` updates the dice image dynamically on screen.

### 3. User Interface Layout
- Page layout is structured using `Scaffold` and `TopAppBar`.
- `Column`, `Arrangement.Center`, and `Alignment.CenterHorizontally` center the dice image and button on screen.
- System back button is intercepted using `BackHandler` to return to the main app catalog.
