# Unit 3: Woof App - Study and Technical Notes

## Summary and Concepts

The Woof app demonstrates Material Design 3 styling, expandable card animations (`animateContentSize`, `animateColorAsState`), and Dark Theme compatibility.

### 1. Smooth Animations
- **`animateContentSize()`**: Smooths card expansion/collapse movements using a physics-based `spring` animation spec when tapped.
- **`animateColorAsState()`**: Gradually transitions card background color between `surfaceVariant` and `tertiaryContainer` as cards expand.

### 2. Layered UI Components
- **`DogItemButton`**: Indicates and triggers expansion state using `ExpandMore` and `ExpandLess` icons.
- **`CircleShape` & `ContentScale.Crop`**: Crops dog profile pictures into circular shapes.

### 3. Material Design 3 Theming
- Light and Dark mode support tested via `@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)`.
