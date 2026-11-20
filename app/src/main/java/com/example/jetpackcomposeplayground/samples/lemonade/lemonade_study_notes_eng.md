# Unit 2: Lemonade App - Study and Technical Notes

## Summary and Concepts

The Lemonade application demonstrates state machine design and dynamic click count handling in Jetpack Compose.

### 1. Multi-Step State Machine
- The app consists of 4 distinct steps:
  1. **Step 1 (Select Lemon)**: Pick a lemon from the lemon tree (`step = 1`).
  2. **Step 2 (Squeeze Lemon)**: Squeeze the lemon. Requires a randomly generated number of squeezes (`requiredSqueezes` between 2 and 4) (`step = 2`).
  3. **Step 3 (Drink Lemonade)**: Drink the lemonade (`step = 3`).
  4. **Step 4 (Restart)**: Tap the empty glass to restart (`step = 4 -> 1`).

### 2. Squeeze Counter and Conditional Logic
- `squeezeCount++` increments squeeze counter on each tap.
- When `squeezeCount >= requiredSqueezes` is met, transitions to the next step (`step = 3`).

### 3. UI Design and Components
- Formatted card design built using `Box`, `clip(RoundedCornerShape)`, `border`, and `clickable` Modifiers.
- `Pair(instructionText, imageRes)` dynamically pairs step instructions with corresponding image resources.
