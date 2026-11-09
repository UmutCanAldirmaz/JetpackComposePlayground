package com.example.jetpackcomposeplayground.samples.inventory.ui.navigation

/**
 * Uygulamadaki ekran navigasyon hedeflerini (Destination) tanımlayan arayüz.
 */
interface NavigationDestination {
    /**
     * Ekranın benzersiz rotası (yönlendirme yolu).
     */
    val route: String

    /**
     * Üst çubukta (TopAppBar) gösterilecek ekran başlığının string kaynak ID'si.
     */
    val titleRes: Int
}
