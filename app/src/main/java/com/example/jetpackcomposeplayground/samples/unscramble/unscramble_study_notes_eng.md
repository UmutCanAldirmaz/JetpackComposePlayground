# Unit 4: Unscramble Game App - Study and Technical Notes

## Summary and Concepts

The Unscramble app demonstrates game logic management with ViewModel, word shuffling algorithms, and dialog windows (`AlertDialog`).

### 1. Game Logic and State Management
- **`GameViewModel`**: Holds scrambled word (`currentScrambledWord`), score (`score`), word count (`currentWordCount`), and game completion status (`isGameOver`) inside the `GameUiState` data class.
- **`shuffle()`**: Randomly scrambles characters of the target word. If the output accidentally matches the original word, it reshuffles inside a `while` loop.

### 2. Input Validation and Keyboard Events
- **`KeyboardOptions(imeAction = ImeAction.Done)`**: Triggers `checkUserGuess()` when the IME action button (Done) is pressed on the software keyboard.
- **`isError = isGuessWrong`**: Sets `OutlinedTextField` to error state and updates label text upon an incorrect guess.

### 3. Game Over Dialog
- **`FinalScoreDialog`**: Displays an `AlertDialog` presenting final score after 10 words, providing a "Play Again" option that resets state via `resetGame()`.
