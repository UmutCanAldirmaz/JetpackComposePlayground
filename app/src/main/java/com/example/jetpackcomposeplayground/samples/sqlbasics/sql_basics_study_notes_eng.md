# Unit 6: SQL Basics & Room Database - Study and Technical Notes

## Summary and Concepts

The SQL Basics application demonstrates the Room library, loading pre-populated databases (`createFromAsset`), and running SQLite queries.

### 1. Loading Pre-populated SQLite Database (`createFromAsset`)
- The pre-built SQLite database located at `assets/database/Email.db` is loaded into Room using `Room.databaseBuilder(...).createFromAsset("database/Email.db")`.

### 2. Entity and DAO Structure
- **`@Entity(tableName = "email")`**: Class representing email data records.
- **`@Dao` & `@Query`**: DAO methods executing raw SQL queries such as `SELECT * FROM email`.

### 3. Dispatchers.IO and Asynchronous Data
- Database read operations are executed inside a `withContext(Dispatchers.IO)` block to prevent blocking the main UI thread.
