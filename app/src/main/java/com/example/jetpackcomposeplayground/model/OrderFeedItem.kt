package com.example.jetpackcomposeplayground.model

sealed interface OrderFeedItem {
    val id: String

    data class SummaryCard(
        override val id: String = "summary_card_id",
        val totalOrders: Int,
        val totalSpent: Double
    ) : OrderFeedItem

    data class ActiveOrder(
        override val id: String,
        val orderNumber: String,
        val status: String,
        val deliveryDate: String
    ) : OrderFeedItem

    data class PromotionBanner(
        override val id: String,
        val title: String,
        val discountRate: String
    ) : OrderFeedItem
}

val sampleOrderFeed = listOf(
    OrderFeedItem.SummaryCard(
        totalOrders = 24,
        totalSpent = 3450.50
    ),
    OrderFeedItem.PromotionBanner(
        id = "promo_1",
        title = "Bahar İndirimi Fırsatı!",
        discountRate = "%20 İndirim"
    ),
    OrderFeedItem.ActiveOrder(
        id = "order_101",
        orderNumber = "#ORD-2024-101",
        status = "Kargoda 🚚",
        deliveryDate = "15 Mayıs Perşembe"
    ),
    OrderFeedItem.ActiveOrder(
        id = "order_102",
        orderNumber = "#ORD-2024-102",
        status = "Hazırlanıyor 📦",
        deliveryDate = "17 Mayıs Cumartesi"
    ),
    OrderFeedItem.PromotionBanner(
        id = "promo_2",
        title = "Teknoloji Ürünlerinde Fırsat",
        discountRate = "500 ₺ Kupon"
    ),
    OrderFeedItem.ActiveOrder(
        id = "order_103",
        orderNumber = "#ORD-2024-103",
        status = "Teslim Edildi ✅",
        deliveryDate = "10 Mayıs Pazartesi"
    )
)
