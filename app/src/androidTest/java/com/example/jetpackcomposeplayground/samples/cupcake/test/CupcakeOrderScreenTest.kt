package com.example.jetpackcomposeplayground.samples.cupcake.test

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.jetpackcomposeplayground.R
import com.example.jetpackcomposeplayground.samples.cupcake.data.DataSource
import com.example.jetpackcomposeplayground.samples.cupcake.data.OrderUiState
import com.example.jetpackcomposeplayground.samples.cupcake.ui.OrderSummaryScreen
import com.example.jetpackcomposeplayground.samples.cupcake.ui.SelectOptionScreen
import com.example.jetpackcomposeplayground.samples.cupcake.ui.StartOrderScreen
import org.junit.Rule
import org.junit.Test

/**
 * Cupcake uygulamasına ait bireysel ekranların (Start, SelectOption, Summary) içerik ve
 * UI durumlarını (buton aktifliği vb.) test eden sınıf.
 */
class CupcakeOrderScreenTest {

    // Testlerin çalışacağı boş bir aktivite ortamı (ComponentActivity) sağlar.
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    // Testler için örnek sahte sipariş verisi (OrderUiState)
    private val fakeOrderUiState = OrderUiState(
        quantity = 6,
        flavor = "Vanilla",
        date = "Wed Jul 21",
        price = "$100",
        pickupOptions = listOf()
    )

    /**
     * Başlangıç ekranına (StartOrderScreen) miktar seçenekleri verildiğinde,
     * tüm seçeneklerin ekranda doğru şekilde göründüğünü doğrular.
     */
    @Test
    fun startOrderScreen_verifyContent() {
        composeTestRule.setContent {
            StartOrderScreen(
                quantityOptions = DataSource.quantityOptions,
                onNextButtonClicked = {}
            )
        }

        // Tüm miktar seçeneklerinin (1, 6, 12 adet) ekranda görüntülendiğini kontrol et
        DataSource.quantityOptions.forEach {
            composeTestRule.onNodeWithStringId(it.first).assertIsDisplayed()
        }
    }

    /**
     * Seçim ekranına (SelectOptionScreen) aroma listesi ve ara toplam verildiğinde;
     * aromaların ve fiyatın göründüğünü, henüz seçim yapılmadığı için İleri butonunun devre dışı olduğunu doğrular.
     */
    @Test
    fun selectOptionScreen_verifyContent() {
        val flavors = listOf("Vanilla", "Chocolate", "Hazelnut", "Cookie", "Mango")
        val subtotal = "$100"

        composeTestRule.setContent {
            SelectOptionScreen(subtotal = subtotal, options = flavors)
        }

        // Tüm aroma seçeneklerinin ekranda listelendiğini kontrol et
        flavors.forEach { flavor ->
            composeTestRule.onNodeWithText(flavor).assertIsDisplayed()
        }

        // Ara toplam fiyatının doğru gösterildiğini kontrol et
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(
                R.string.subtotal_price,
                subtotal
            )
        ).assertIsDisplayed()

        // Henüz bir şey seçilmediği için İleri (Next) butonunun devre dışı (disabled) olduğunu doğrula
        composeTestRule.onNodeWithStringId(R.string.next).assertIsNotEnabled()
    }

    /**
     * Seçim ekranında herhangi bir aroma seçildiğinde,
     * İleri butonunun etkin (enabled) hale geldiğini doğrular.
     */
    @Test
    fun selectOptionScreen_optionSelected_NextButtonEnabled() {
        val flavours = listOf("Vanilla", "Chocolate", "Hazelnut", "Cookie", "Mango")
        val subTotal = "$100"

        composeTestRule.setContent {
            SelectOptionScreen(subtotal = subTotal, options = flavours)
        }

        // Listeden bir aromaya tıkla
        composeTestRule.onNodeWithText("Vanilla").performClick()

        // Aroma seçildiği için İleri butonunun etkinleştiğini doğrula
        composeTestRule.onNodeWithStringId(R.string.next).assertIsEnabled()
    }

    /**
     * Özet ekranına (OrderSummaryScreen) sipariş bilgileri (OrderUiState) verildiğinde;
     * seçilen aromanın, tarihin ve fiyatın ekranda doğru yansıtıldığını doğrular.
     */
    @Test
    fun summaryScreen_verifyContentDisplay() {
        composeTestRule.setContent {
            OrderSummaryScreen(
                orderUiState = fakeOrderUiState,
                onCancelButtonClicked = {},
                onSendButtonClicked = { _, _ -> },
            )
        }

        // Aroma, tarih ve fiyat bilgilerinin ekranda doğru görüntülendiğini kontrol et
        composeTestRule.onNodeWithText(fakeOrderUiState.flavor).assertIsDisplayed()
        composeTestRule.onNodeWithText(fakeOrderUiState.date).assertIsDisplayed()
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(
                R.string.subtotal_price,
                fakeOrderUiState.price
            )
        ).assertIsDisplayed()
    }
}
