package com.example.jetpackcomposeplayground.samples.flightsearch.test

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.example.jetpackcomposeplayground.samples.flightsearch.ui.FlightSearchApp
import org.junit.Rule
import org.junit.Test

/**
 * Flight Search ekranının UI akışını ve favori ekleme/listeleme
 * adımlarını ekranda doğrulayan enstrümante UI test sınıfı.
 */
class FlightSearchUiTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun flightSearch_searchAndFavoriteRoute_verifyOnScreen() {
        composeTestRule.setContent {
            FlightSearchApp(onBackClick = {})
        }

        composeTestRule.waitForIdle()

        // 1. Arama çubuğunu bul ve "OPO" yaz
        composeTestRule.onNodeWithText("Havalimanı adı veya IATA kodu girin")
            .assertIsDisplayed()
            .performTextInput("OPO")

        composeTestRule.waitForIdle()

        // 2. Arama önerileri arasında "Francisco Sá Carneiro Airport" var mı kontrol et ve tıkla
        composeTestRule.onNodeWithText("Francisco Sá Carneiro Airport")
            .assertIsDisplayed()
            .performClick()

        composeTestRule.waitForIdle()

        // 3. "OPO Kalkışlı Uçuşlar" başlığının ekranda olduğunu doğrula
        composeTestRule.onNodeWithText("OPO Kalkışlı Uçuşlar")
            .assertIsDisplayed()

        // 4. Uçuş listesindeki ilk favori butonuna (yıldız) tıkla
        composeTestRule.onAllNodes(hasContentDescription("Favorite"))[0]
            .performClick()

        composeTestRule.waitForIdle()

        // 5. Arama kutusundaki temizleme (Clear) butonuna basarak aramayı sıfırla
        composeTestRule.onNodeWithContentDescription("Clear")
            .performClick()

        composeTestRule.waitForIdle()

        // 6. "Favori Rotalar" başlığının göründüğünü doğrula
        composeTestRule.onNodeWithText("Favori Rotalar")
            .assertIsDisplayed()

        // 7. Eklenen favori rotanın (Francisco Sá Carneiro Airport) ekranda listelendiğini doğrula
        composeTestRule.onNodeWithText("Francisco Sá Carneiro Airport")
            .assertIsDisplayed()
    }
}
