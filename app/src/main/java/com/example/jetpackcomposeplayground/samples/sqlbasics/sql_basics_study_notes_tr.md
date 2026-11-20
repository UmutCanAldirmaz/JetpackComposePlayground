# Unit 6: SQL Basics & Room Database - Çalışma ve Teknik Notlar

## Özet ve Konseptler

SQL Basics uygulaması, Room kütüphanesini, hazırdan veritabanı yüklemeyi (`createFromAsset`) ve SQLite sorgularını gösterir.

### 1. Hazır SQLite Veritabanı Yükleme (`createFromAsset`)
- `assets/database/Email.db` klasöründe yer alan önceden oluşturulmuş SQLite veritabanı `Room.databaseBuilder(...).createFromAsset("database/Email.db")` metoduyla Room veritabanına dönüştürülür.

### 2. Entity ve DAO Yapısı
- **`@Entity(tableName = "email")`**: E-posta verilerini temsil eden sınıf.
- **`@Dao` & `@Query`**: `SELECT * FROM email` gibi ham SQL sorgularını çalıştıran DAO metotları.

### 3. Dispatchers.IO ve Asenkron Veri
- Veritabanı okuma işlemleri `withContext(Dispatchers.IO)` bloğu içerisinde yürütülerek ana UI iş parçacığının (Main Thread) kilitlenmesi önlenir.
