# Unit 4: Lifecycle Counter Exercise - Çalışma ve Teknik Notlar

## Özet ve Konseptler

Lifecycle Counter uygulaması, Android Yaşam Döngüsü (Lifecycle) duyarlı `StateFlow` aboneliğini ve Recomposition izlemeyi gösterir.

### 1. Yaşam Döngüsüne Duyarlı State Akışı
- **`collectAsStateWithLifecycle()`**: Uygulama arka plana atıldığında (Lifecycle `STOPPED` durumuna geçtiğinde) `StateFlow` dinlemesini durdurur. Bu sayede arka planda gereksiz CPU tüketimi ve bellek sızıntıları önlenir.

### 2. Yan Etki ve Recomposition Takibi
- **`SideEffect { recompositionCount++ }`**: Her Recomposition gerçekleştiğinde yan etki (Side Effect) tetiklenerek arayüzün kaç defa yeniden çizildiği ölçülür.
- **`LaunchedEffect(count)`**: Sayaç değeri güncellendiğinde Logcat konsoluna debug günlüğü yazar.
