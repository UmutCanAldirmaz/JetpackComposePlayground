# Unit 3: SuperHeroes App - Study and Technical Notes

## Summary and Concepts

The SuperHeroes app demonstrates horizontal list layout (`Row`), image cropping, and Material Design 3 typography placements.

### 1. Horizontal Layout and Weight (`Row` & `weight`)
- `Column(modifier = Modifier.weight(1f))`: Enables superhero name and description to occupy remaining horizontal space.
- `Image` component is aligned on the right with a fixed `72.dp` size.

### 2. Card and Shape Design
- `Card(shape = RoundedCornerShape(16.dp))`: Modern card design with rounded corners.
- `clip(RoundedCornerShape(8.dp))`: Rounds corners of superhero images.
