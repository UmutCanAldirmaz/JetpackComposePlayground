package com.example.jetpackcomposeplayground.samples.cupcake.test

import android.icu.util.Calendar
import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.example.jetpackcomposeplayground.R
import com.example.jetpackcomposeplayground.samples.cupcake.CupcakeScreen
import com.example.jetpackcomposeplayground.samples.cupcake.CupcakeScreenRoute
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.Locale

/**
 * Cupcake uygulamasının ekranlar arası navigasyon (NavHost, geri tuşu, iptal, ileri geçişleri)
 * akışını test eden sınıf.
 */
class CupcakeScreenNavigationTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    // Navigasyon testleri için test amaçlı Controller
    private lateinit var navController: TestNavHostController

    // Her test çalıştırılmadan önce Cupcake uygulamasını test NavController ile başlat
    @Before
    fun setupCupcakeNavHost() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current).apply {
                navigatorProvider.addNavigator(ComposeNavigator())
            }
            CupcakeScreen(navController = navController)
        }
    }

    /**
     * Uygulama ilk açıldığında başlangıç rotasının "Start" olduğunu doğrula.
     */
    @Test
    fun cupcakeNavHost_verifyStartDestination() {
        navController.assertCurrentRouteName(CupcakeScreenRoute.Start.name)
    }

    /**
     * Başlangıç ekranında geri (Up) butonunun görünmediğini doğrula.
     */
    @Test
    fun cupcakeNavHost_verifyBackNavigationNotShownOnStartOrderScreen() {
        val backText = composeTestRule.activity.getString(R.string.back_button)
        composeTestRule.onNodeWithContentDescription(backText).assertDoesNotExist()
    }

    /**
     * Başlangıç ekranında miktar seçildiğinde Aroma (Flavor) ekranına geçildiğini doğrula.
     */
    @Test
    fun cupcakeNavHost_clickOneCupcake_navigatesToSelectFlavorScreen() {
        composeTestRule.onNodeWithStringId(R.string.one_cupcake)
            .performClick()
        navController.assertCurrentRouteName(CupcakeScreenRoute.Flavor.name)
    }

    /**
     * Aroma ekranında ileri butonuna basıldığında Teslimat Tarihi (Pickup) ekranına geçildiğini doğrula.
     */
    @Test
    fun cupcakeNavHost_clickNextOnFlavorScreen_navigatesToPickupScreen() {
        navigateToFlavorScreen()
        composeTestRule.onNodeWithStringId(R.string.next)
            .performClick()
        navController.assertCurrentRouteName(CupcakeScreenRoute.Pickup.name)
    }

    /**
     * Aroma ekranındayken üst bardaki geri butonuna basıldığında Başlangıç ekranına dönüldüğünü doğrula.
     */
    @Test
    fun cupcakeNavHost_clickBackOnFlavorScreen_navigatesToStartOrderScreen() {
        navigateToFlavorScreen()
        performNavigateUp()
        navController.assertCurrentRouteName(CupcakeScreenRoute.Start.name)
    }

    /**
     * Aroma ekranındayken İptal butonuna basıldığında Başlangıç ekranına dönüldüğünü doğrula.
     */
    @Test
    fun cupcakeNavHost_clickCancelOnFlavorScreen_navigatesToStartOrderScreen() {
        navigateToFlavorScreen()
        composeTestRule.onNodeWithStringId(R.string.cancel)
            .performClick()
        navController.assertCurrentRouteName(CupcakeScreenRoute.Start.name)
    }

    /**
     * Teslimat tarihi seçilip ileri basıldığında Sipariş Özeti (Summary) ekranına geçildiğini doğrula.
     */
    @Test
    fun cupcakeNavHost_clickNextOnPickupScreen_navigatesToSummaryScreen() {
        navigateToPickupScreen()
        composeTestRule.onNodeWithText(getFormattedDate())
            .performClick()
        composeTestRule.onNodeWithStringId(R.string.next)
            .performClick()
        navController.assertCurrentRouteName(CupcakeScreenRoute.Summary.name)
    }

    /**
     * Teslimat tarihi ekranındayken geri butonuna basıldığında Aroma ekranına dönüldüğünü doğrula.
     */
    @Test
    fun cupcakeNavHost_clickBackOnPickupScreen_navigatesToFlavorScreen() {
        navigateToPickupScreen()
        performNavigateUp()
        navController.assertCurrentRouteName(CupcakeScreenRoute.Flavor.name)
    }

    /**
     * Teslimat tarihi ekranındayken İptal butonuna basıldığında Başlangıç ekranına dönüldüğünü doğrula.
     */
    @Test
    fun cupcakeNavHost_clickCancelOnPickupScreen_navigatesToStartOrderScreen() {
        navigateToPickupScreen()
        composeTestRule.onNodeWithStringId(R.string.cancel)
            .performClick()
        navController.assertCurrentRouteName(CupcakeScreenRoute.Start.name)
    }

    /**
     * Özet ekranındayken İptal butonuna basıldığında siparişin sıfırlanıp Başlangıç ekranına dönüldüğünü doğrula.
     */
    @Test
    fun cupcakeNavHost_clickCancelOnSummaryScreen_navigatesToStartOrderScreen() {
        navigateToSummaryScreen()
        composeTestRule.onNodeWithStringId(R.string.cancel)
            .performClick()
        navController.assertCurrentRouteName(CupcakeScreenRoute.Start.name)
    }

    // --- Yardımcı Navigasyon Fonksiyonları (Test Helper Methods) ---

    private fun navigateToFlavorScreen() {
        composeTestRule.onNodeWithStringId(R.string.one_cupcake)
            .performClick()
        composeTestRule.onNodeWithStringId(R.string.chocolate)
            .performClick()
    }

    private fun navigateToPickupScreen() {
        navigateToFlavorScreen()
        composeTestRule.onNodeWithStringId(R.string.next)
            .performClick()
    }

    private fun navigateToSummaryScreen() {
        navigateToPickupScreen()
        composeTestRule.onNodeWithText(getFormattedDate())
            .performClick()
        composeTestRule.onNodeWithStringId(R.string.next)
            .performClick()
    }

    private fun performNavigateUp() {
        val backText = composeTestRule.activity.getString(R.string.back_button)
        composeTestRule.onNodeWithContentDescription(backText).performClick()
    }

    private fun getFormattedDate(): String {
        val calendar = Calendar.getInstance()
        calendar.add(java.util.Calendar.DATE, 1)
        val formatter = SimpleDateFormat("E MMM d", Locale.getDefault())
        return formatter.format(calendar.time)
    }
}
