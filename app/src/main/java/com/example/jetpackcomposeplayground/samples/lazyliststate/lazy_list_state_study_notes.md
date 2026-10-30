# Lazy List State Practice - Çalışma ve Öğrenme Notları

## Proje Amacı
`LazyListState` kullanarak liste kaydırma (scroll) pozisyonunu takip etmek ve programatik kaydırma işlemleri gerçekleştirmek.

## Temel Öğrenilenler
- `rememberLazyListState()` ile scroll durumunu hafızada tutma.
- Kullanıcı aşağı kaydırdığında "Yukarı Çık" (Scroll to Top) butonunu gösterme (`derivedStateOf`).
- `coroutineScope.launch { listState.animateScrollToItem(0) }` ile akıcı kaydırma.
