package com.example.jetpackcomposeplayground.samples.flightsearch.test

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasScrollAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToNode
import androidx.compose.ui.test.performTextInput
import com.example.jetpackcomposeplayground.MainActivity
import org.junit.Rule
import org.junit.Test

/**
 * MainActivity içerisinden Flight Search uygulamasına geçip
 * arama, favorilere ekleme ve ekranda favori kontrolü yapan UI testi.
 */
class FlightSearchPersistenceUiTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun testFlightSearch_favoriteWorkflow() {
        composeTestRule.waitForIdle()

        // 1. Ana ekrandaki LazyColumn içinde Flight Search kartını bulup kaydır ve tıkla
        composeTestRule.onNode(hasScrollAction())
            .performScrollToNode(hasText("Flight Search"))

        composeTestRule.onNode(hasText("Flight Search"))
            .performClick()

        composeTestRule.waitForIdle()

        // 2. Arama kutusuna "OPO" yaz
        composeTestRule.onNodeWithText("Havalimanı adı veya IATA kodu girin")
            .assertIsDisplayed()
            .performTextInput("OPO")

        composeTestRule.waitForIdle()

        // 3. Arama önerilerindeki havalimanına tıkla
        composeTestRule.onNode(hasText("Francisco Sá Carneiro Airport"))
            .assertIsDisplayed()
            .performClick()

        composeTestRule.waitForIdle()

        // 4. "OPO Kalkışlı Uçuşlar" başlığını doğrula
        composeTestRule.onNodeWithText("OPO Kalkışlı Uçuşlar")
            .assertIsDisplayed()

        // 5. İlk uçuşun favori butonuna tıkla
        composeTestRule.onAllNodes(hasContentDescription("Favorite"))[0]
            .performClick()

        composeTestRule.waitForIdle()

        // 6. Arama kutusunu temizle
        composeTestRule.onNodeWithContentDescription("Clear")
            .performClick()

        composeTestRule.waitForIdle()

        // 7. "Favori Rotalar" başlığının ve favori rotanın göründüğünü doğrula
        composeTestRule.onNodeWithText("Favori Rotalar")
            .assertIsDisplayed()

        composeTestRule.onNodeWithText("Francisco Sá Carneiro Airport")
            .assertIsDisplayed()
    }
}
