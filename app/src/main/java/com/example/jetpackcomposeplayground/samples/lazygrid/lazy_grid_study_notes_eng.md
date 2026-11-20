# Unit 3: Lazy Grid Practice - Study and Technical Notes

## Summary and Concepts

The Lazy Grid application demonstrates grid layouts, flexible column configurations (`GridCells.Adaptive`, `GridCells.Fixed`), and item span handling (`GridItemSpan`) using `LazyVerticalGrid` and `LazyHorizontalGrid`.

### 1. Vertical Grid (`LazyVerticalGrid`)
- **`GridCells.Adaptive(minSize = 100.dp)`**: Dynamically calculates column count based on available screen width.
- **`GridItemSpan(maxLineSpan)`**: Configures items matching multiples of 5 (5, 10, 15, etc.) to span across the entire row width.

### 2. Horizontal Grid (`LazyHorizontalGrid`)
- **`GridCells.Fixed(3)`**: Horizontally scrollable profile card grid consisting of 3 fixed rows.
