# Unit 3: Lazy List State Practice - Çalışma ve Teknik Notlar

## Özet ve Konseptler

Lazy List State uygulaması, `LazyListState` nesnesi ile liste kaydırma durumlarını kontrol etmeyi ve dinamik layout bilgilerini izlemeyi gösterir.

### 1. Liste Kaydırma Denetimi (`LazyListState`)
- **`rememberLazyListState()`**: Liste kaydırma pozisyonunu saklar.
- **`listState.scrollToItem(50)`**: 50. indeksli öğeye anında atlar.
- **`listState.animateScrollToItem(index)`**: Animasyonlu yumuşak kaydırma gerçekleştirir.

### 2. Görünür Öğeleri İzleme
- **`listState.layoutInfo.visibleItemsInfo.size`**: Ekranda o an aktif çizilen öğe sayısını anlık olarak verir.
