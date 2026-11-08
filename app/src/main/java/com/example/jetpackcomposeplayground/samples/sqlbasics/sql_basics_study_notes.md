# Unit 6: SQL Basics & Room Database Study Notes

## Özet ve Konseptler

1. **SQL & Room Database Entegrasyonu**:
   - `RoomDatabase` soyut sınıfı ve `@Database` anotasyonu.
   - Önceden oluşturulmuş SQLite veritabanı dosyasını (`Email.db`) `assets/database/` klasöründen `.createFromAsset("database/Email.db")` metodu ile yükleme.

2. **Entity ve DAO Nesneleri**:
   - `@Entity(tableName = "email")`: Veritabanı tablosunu temsil eden Kotlin veri sınıfı.
   - `@Dao`: SQL sorgularını tanımlayan arabirim (`EmailDao`). `@Query("SELECT * FROM email")` ile SQLite sorgusu çalıştırma.

3. **Asenkron Veri Erişimi**:
   - DAO metodlarının `suspend` keyword'ü ile asenkron tanımlanması.
   - Compose içinde `withContext(Dispatchers.IO)` kullanarak UI iş parçacığını (Main Thread) engellemeden veritabanı işlemlerini gerçekleştirme.
