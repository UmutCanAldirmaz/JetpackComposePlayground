# Reply Uygulaması - Geliştirme ve Uyarlanabilir Düzen (Adaptive Layout) Notları

Bu doküman, Google **Android Basics with Compose** kursunun Unit 4 modülü kapsamında geliştirilen **Reply** örnek uygulaması için hazırlanan çalışma ve öğrenme notlarını içermektedir.

---

## 1. Projenin Amacı ve Kapsamı
Reply, kullanıcıların e-postalarını yönetebileceği örnek bir e-posta istemcisi uygulamasıdır. Bu projedeki temel amaç; sabit ve tek tip ekran tasarımları yerine, **farklı ekran boyutlarına (telefon, tablet, katlanabilir cihazlar ve masaüstü)** dinamik olarak uyum sağlayan **Responsive / Adaptive (Uyarlanabilir)** bir kullanıcı arayüzü mimarisi kurmaktır.

---

## 2. Temel Gereksinimler ve Bağımlılıklar
* **Jetpack Compose & Material 3:** Modern, deklaratif UI bileşenleri ve Material Design 3 renk/tipografi sistemleri.
* **Window Size Classes (`androidx.compose.material3.windowsizeclass`):** Ekran genişliğini Compact, Medium ve Expanded olarak sınıflandırmak için kullanılan kütüphane.
* **Architecture Components (`ViewModel`, `StateFlow`, `State`):** Uygulama durumunun (`ReplyUiState`) yaşam döngüsüne uygun olarak yönetilmesi ve UI ile senkronize edilmesi.

---

## 3. Adım Adım Geliştirme Aşamaları

### Adım 1: Veri Modelleri ve Sağlayıcılar (Data Layer)
* **`Account.kt` & `Email.kt`**: Kullanıcı profillerini ve e-posta mesajlarını temsil eden temel veri sınıfları.
* **`MailboxType.kt`**: E-posta kutusu sekmelerini (`Inbox`, `Sent`, `Drafts`, `Spam`) belirten `enum` sınıfı.
* **`LocalAccountsDataProvider.kt` & `LocalEmailsDataProvider.kt`**: UI testleri ve görünüm için başlangıç verilerini sağlayan statik veri kaynakları.

### Adım 2: Durum Yönetimi ve İş Mantığı (ViewModel & UI State)
* **`ReplyUiState.kt`**: Hangi mail kutusunda olduğumuzu (`currentMailbox`), hangi e-postanın seçildiğini (`currentSelectedEmail`) ve ana sayfada mı yoksa detay ekranında mı olduğumuzu (`isShowingHomepage`) tutan durum sınıfı.
* **`ReplyViewModel.kt`**: Kullanıcı etkileşimlerini (sekme değiştirme, e-posta seçme, geri dönme) işleyen ve state değişikliklerini `StateFlow` aracılığıyla UI katmanına ileten ViewModel sınıfı.

### Adım 3: Uyarlanabilir Navigasyon Mimarisi (Adaptive Navigation)
* **`WindowStateUtils.kt`**: 
  - `ReplyNavigationType`: `BOTTOM_NAVIGATION`, `NAVIGATION_RAIL`, `PERMANENT_NAVIGATION_DRAWER`
  - `ReplyContentType`: `LIST_ONLY`, `LIST_AND_DETAIL`
* **`ReplyApp.kt`**: Cihazın `WindowWidthSizeClass` değerini hesaplayarak navigasyon tipine ve içerik tipine karar verir:
  - **Compact (< 600dp - Telefon):** `BOTTOM_NAVIGATION` + `LIST_ONLY`
  - **Medium (600dp - 840dp - Tablet):** `NAVIGATION_RAIL` + `LIST_ONLY`
  - **Expanded (> 840dp - Geniş Ekran):** `PERMANENT_NAVIGATION_DRAWER` + `LIST_AND_DETAIL`

### Adım 4: Dinamik İçerik ve İki Bölmeli Düzen (Two-Pane Layout)
* **`ReplyHomeScreen.kt` & `ReplyHomeContent.kt`**:
  - Telefon ve tabletlerde liste ve detay ekranları ayrı ayrı tam ekran gösterilir (`LIST_ONLY`).
  - Geniş ekranlarda ise **`ReplyListAndDetailContent`** devreye girer; sol tarafta e-posta listesi, sağ tarafta ise seçilen e-postanın detayı (`ReplyDetailsScreen`) yan yana iki sütun (`Row` + `weight`) olarak sergilenir.

---

## 4. Öğrenilen Temel Kavramlar ve En İyi Uygulamalar

1. **Window Size Classes (Pencere Boyutu Sınıfları):**
   Cihazın sadece fiziksel ekran boyutuna değil, kullanılabilir pencere genişliğine göre tasarım yapmanın önemi (Compact, Medium, Expanded).
2. **State Hoisting & Unidirectional Data Flow (Tek Yönlü Veri Akışı):**
   Durumun `ViewModel` içinde yönetilmesi ve UI bileşenlerinin (`Stateless Composables`) bu duruma göre render edilmesi.
3. **Responsive / Adaptive UI Tasarımı:**
   Aynı kod tabanını değiştirerek hem küçük ekranlı telefonlarda hem de büyük ekranlı tablet/masaüstü cihazlarda mükemmel deneyim sunma becerisi.
4. **Context ve Activity Çözümleme (`findActivity`):**
   Jetpack Compose içerisinde `LocalContext` sarmalayıcılarını (`ContextWrapper`) aşarak güvenli bir şekilde `Activity` ve pencere metriklerine erişim sağlama teknikleri.
