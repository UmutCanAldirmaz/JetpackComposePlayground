# Unit 2: Art Space App - Study and Technical Notes

## Summary and Concepts

The Art Space app demonstrates an art gallery showcase layout, card designs, and list navigation state management.

### 1. Data Structure and Artwork Model
- The `data class Artwork(val titleRes: Int, val artistRes: Int, val imageRes: Int)` data class defines artwork title, artist, and image resource references.

### 2. Gallery Navigation Logic
- `var currentIndex by remember { mutableStateOf(0) }` tracks the index of the currently displayed artwork.
- **Previous Button**: Cycles to `if (currentIndex > 0) currentIndex - 1 else artworks.size - 1`.
- **Next Button**: Cycles to `if (currentIndex < artworks.size - 1) currentIndex + 1 else 0`.

### 3. UI Design and Layout
- `Card` and `Surface` components are styled with elevation shadows (`elevation`) and borders (`BorderStroke`) to achieve a framed museum exhibit appearance.
- `ContentScale.Fit` fits images within the card without distorting their aspect ratio.
