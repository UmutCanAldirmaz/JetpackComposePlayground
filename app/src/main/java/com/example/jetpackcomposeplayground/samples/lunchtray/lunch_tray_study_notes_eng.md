# Unit 4: Lunch Tray App - Study and Technical Notes

## Summary and Concepts

The Lunch Tray application is a multi-step navigation ordering flow covering Entree, Side Dish, Accompaniment/Drink selections, and Checkout summary.

### 1. Multi-Step Order Navigation
- **`LunchTrayScreenRoute`**: Represents `Start`, `Entree`, `SideDish`, `Accompaniment`, and `Checkout` steps.
- Navigates to the next route (`navController.navigate()`) upon completing each selection step.

### 2. Price and Tax Calculation
- `OrderViewModel`: Reactively updates subtotal (`subtotal`), tax (`tax` - 8%), and total price (`total`) for each selected `MenuItem`.
- `resetOrder()`: Clears cart items on order cancellation or completion.
