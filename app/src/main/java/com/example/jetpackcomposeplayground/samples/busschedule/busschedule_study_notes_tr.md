# Unit 6: Bus Schedule App (Pre-populated Room Database) - Çalışma Notları

## Özet ve Konseptler

1. **Önceden Doldurulmuş Veritabanı (Pre-populated Room Database)**:
   - `RoomDatabase.Builder.createFromAsset("database/bus_schedule.db")` kullanılarak hazırdan `assets` klasöründe yer alan SQLite veritabanı yüklenmiştir.
   - `BusSchedule`: `Schedule` tablosunu temsil eden `@Entity`.
   - `BusScheduleDao`: Tüm rotayı veya belirli durak adına göre zaman sıralı otobüs tarifelerini getiren `@Query` metotları (`getAll()`, `getByStopName()`).

2. **Flow ve ViewModel**:
   - `BusScheduleViewModel`: `getFullSchedule()` ve `getScheduleFor(stopName)` ile `Flow<List<BusSchedule>>` verisini Compose UI tarafına aktarır.
   - `collectAsState(emptyList())` ile Composable içinde reaktif olarak dinlenir.

3. **Navigasyon**:
   - `FullScheduleScreen` (Tüm duraklar) ve `RouteScheduleScreen` (Seçilen durağa ait varış saatleri).
