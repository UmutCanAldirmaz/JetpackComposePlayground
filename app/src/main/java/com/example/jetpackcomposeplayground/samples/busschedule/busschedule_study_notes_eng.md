# Unit 6: Bus Schedule App (Pre-populated Room Database) - Study Notes

## Summary and Concepts

1. **Pre-populated Room Database**:
   - `RoomDatabase.Builder.createFromAsset("database/bus_schedule.db")` is used to load a pre-populated SQLite database from the `assets` directory.
   - `BusSchedule`: An `@Entity` representing the `Schedule` table.
   - `BusScheduleDao`: `@Query` methods (`getAll()`, `getByStopName()`) returning bus schedules sorted by arrival time for all routes or a specific stop.

2. **Flow and ViewModel**:
   - `BusScheduleViewModel`: Exposes `Flow<List<BusSchedule>>` data to the Compose UI via `getFullSchedule()` and `getScheduleFor(stopName)`.
   - Collected reactively inside Composables using `collectAsState(emptyList())`.

3. **Navigation**:
   - `FullScheduleScreen` (All stops) and `RouteScheduleScreen` (Arrival times for the selected stop).
