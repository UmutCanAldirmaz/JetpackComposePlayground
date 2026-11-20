# Unit 3: Affirmations App - Study and Technical Notes

## Summary and Concepts

The Affirmations app demonstrates listing dynamic data using `LazyColumn` and building card components with `Card` in Jetpack Compose.

### 1. Vertically Scrollable List (`LazyColumn`)
- `LazyColumn`: Achieves high-performance scrolling by loading only the items visible on screen into memory (Lazy Loading).
- `items(affirmations)`: Iterates over list elements and binds them to card components.
- `verticalArrangement = Arrangement.spacedBy(16.dp)`: Adds dynamic spacing between cards.

### 2. Image and Text Card Design (`Card`)
- `ContentScale.Crop`: Preserves the aspect ratio of images while making them fill the card width completely.
- `shape = RoundedCornerShape(12.dp)`: Rounds the corners of the card.
- `elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)`: Adds a depth shadow to the cards.
