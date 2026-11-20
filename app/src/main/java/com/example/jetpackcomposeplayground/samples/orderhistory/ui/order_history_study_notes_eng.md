# Unit 3: Order History App - Study and Technical Notes

## Summary and Concepts

The Order History application demonstrates a high-performance multi-view-type list architecture using polymorphic list items (`sealed interface OrderFeedItem`), along with `key` and `contentType` parameters in Compose.

### 1. Sealed Interface and Polymorphism
- `OrderFeedItem`: Encapsulates `SummaryCard`, `ActiveOrder`, and `PromotionBanner` data models.
- The `when (item)` block renders distinct UI cards corresponding to each data model.

### 2. LazyColumn Performance Optimization
- **`key = { item -> item.id }`**: Identifies unique item keys to reduce recomposition overhead.
- **`contentType = { item -> item::class.java.name }`**: Groups similar card types to optimize view recycling during scrolling.
