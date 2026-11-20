# Unit 3: Lazy List State Practice - Study and Technical Notes

## Summary and Concepts

The Lazy List State application demonstrates controlling list scroll positions and observing dynamic layout metrics using `LazyListState`.

### 1. List Scroll Control (`LazyListState`)
- **`rememberLazyListState()`**: Preserves list scroll state.
- **`listState.scrollToItem(50)`**: Jumps directly to item at index 50.
- **`listState.animateScrollToItem(index)`**: Performs smooth animated scrolling to the target index.

### 2. Monitoring Visible Items
- **`listState.layoutInfo.visibleItemsInfo.size`**: Returns real-time count of items currently rendered on screen.
