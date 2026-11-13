package com.example.jetpackcomposeplayground

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveableStateHolder
import com.example.jetpackcomposeplayground.core.theme.JetpackComposePlaygroundTheme
import com.example.jetpackcomposeplayground.home.AppRoute
import com.example.jetpackcomposeplayground.home.HomeScreen
import com.example.jetpackcomposeplayground.samples.advancedlazylists.AdvancedLazyListsScreen
import com.example.jetpackcomposeplayground.samples.affirmations.AffirmationsScreen
import com.example.jetpackcomposeplayground.samples.artspace.ArtSpaceScreen
import com.example.jetpackcomposeplayground.samples.counter.ui.CounterScreen
import com.example.jetpackcomposeplayground.samples.cupcake.CupcakeScreen
import com.example.jetpackcomposeplayground.samples.lunchtray.LunchTrayScreen
import com.example.jetpackcomposeplayground.samples.reply.ReplySampleScreen
import com.example.jetpackcomposeplayground.samples.sports.SportsSampleScreen
import com.example.jetpackcomposeplayground.samples.dessertclicker.ui.DessertClickerScreen
import com.example.jetpackcomposeplayground.samples.diceroller.DiceRollerScreen
import com.example.jetpackcomposeplayground.samples.dynamiclistanimations.DynamicListAnimationsScreen
import com.example.jetpackcomposeplayground.samples.lazygrid.LazyGridPracticeScreen
import com.example.jetpackcomposeplayground.samples.lazyliststate.LazyListStatePracticeScreen
import com.example.jetpackcomposeplayground.samples.lemonade.LemonadeScreen
import com.example.jetpackcomposeplayground.samples.orderhistory.ui.OrderHistoryScreen
import com.example.jetpackcomposeplayground.samples.racetracker.RaceTrackerApp
import com.example.jetpackcomposeplayground.samples.marsphotos.ui.MarsPhotosApp
import com.example.jetpackcomposeplayground.samples.amphibians.ui.AmphibiansApp
import com.example.jetpackcomposeplayground.samples.sqlbasics.ui.SqlBasicsScreen
import com.example.jetpackcomposeplayground.samples.busschedule.ui.BusScheduleApp
import com.example.jetpackcomposeplayground.samples.inventory.ui.InventoryApp
import com.example.jetpackcomposeplayground.samples.dessertrelease.ui.DessertReleaseApp
import com.example.jetpackcomposeplayground.samples.flightsearch.ui.FlightSearchApp
import com.example.jetpackcomposeplayground.samples.bluromatic.ui.BluromaticApp
import com.example.jetpackcomposeplayground.samples.waterme.ui.WaterMeApp
import com.example.jetpackcomposeplayground.samples.juicetracker.ui.JuiceTrackerScreen
import com.example.jetpackcomposeplayground.samples.sandbox.ExerciseScreen
import com.example.jetpackcomposeplayground.samples.superheroes.SuperHeroesScreen
import com.example.jetpackcomposeplayground.samples.tipcalculator.TipCalculatorScreen
import com.example.jetpackcomposeplayground.samples.unscramble.ui.UnscrambleScreen
import com.example.jetpackcomposeplayground.samples.woof.WoofScreen

private const val TAG = "MainActivityDebugging"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate Called")
        enableEdgeToEdge()
        setContent {
            JetpackComposePlaygroundTheme {
                var currentRoute by remember { mutableStateOf(AppRoute.HOME) }

                // SaveableStateHolder: Her ekranın kendi rememberSaveable durumlarını (örneğin HomeScreen scroll pozisyonunu)
                // rota değişse bile arka planda hafızada tutan genel Compose kapsayıcısıdır.
                // Bu sayede MainActivity içerisinde ekranlara özel tek tek state tutmamıza gerek kalmaz.
                val saveableStateHolder = rememberSaveableStateHolder()

                saveableStateHolder.SaveableStateProvider(key = currentRoute) {
                    when (currentRoute) {
                        AppRoute.HOME -> {
                            HomeScreen(
                                onAppSelected = { route ->
                                    currentRoute = route
                                }
                            )
                        }
                    AppRoute.DICE_ROLLER -> {
                        DiceRollerScreen(
                            onBackClick = {
                                currentRoute = AppRoute.HOME
                            }
                        )
                    }
                    AppRoute.LEMONADE -> {
                        LemonadeScreen(
                            onBackClick = {
                                currentRoute = AppRoute.HOME
                            }
                        )
                    }
                    AppRoute.TIP_CALCULATOR -> {
                        TipCalculatorScreen(
                            onBackClick = {
                                currentRoute = AppRoute.HOME
                            }
                        )
                    }
                    AppRoute.ART_SPACE -> {
                        ArtSpaceScreen(
                            onBackClick = {
                                currentRoute = AppRoute.HOME
                            }
                        )
                    }
                    AppRoute.AFFIRMATIONS -> {
                        AffirmationsScreen(
                            onBackClick = {
                                currentRoute = AppRoute.HOME
                            }
                        )
                    }
                    AppRoute.WOOF -> {
                        WoofScreen(
                            onBackClick = {
                                currentRoute = AppRoute.HOME
                            }
                        )
                    }
                    AppRoute.SUPER_HEROES -> {
                        SuperHeroesScreen(
                            onBackClick = {
                                currentRoute = AppRoute.HOME
                            }
                        )
                    }
                    AppRoute.EXERCISE -> {
                        ExerciseScreen(
                            onBackClick = {
                                currentRoute = AppRoute.HOME
                            }
                        )
                    }
                    AppRoute.COUNTER_EXERCISE -> {
                        CounterScreen(
                            onBackClick = {
                                currentRoute = AppRoute.HOME
                            }
                        )
                    }
                    AppRoute.UNSCRAMBLE -> {
                        UnscrambleScreen(
                            onBackClick = {
                                currentRoute = AppRoute.HOME
                            }
                        )
                    }
                    AppRoute.CUPCAKE -> {
                        CupcakeScreen(
                            onBackClick = {
                                currentRoute = AppRoute.HOME
                            }
                        )
                    }
                    AppRoute.LUNCH_TRAY -> {
                        LunchTrayScreen(
                            onBackClick = {
                                currentRoute = AppRoute.HOME
                            }
                        )
                    }
                    AppRoute.REPLY -> {
                        ReplySampleScreen(
                            onBackClick = {
                                currentRoute = AppRoute.HOME
                            }
                        )
                    }
                    AppRoute.SPORTS -> {
                        SportsSampleScreen(
                            onBackClick = {
                                currentRoute = AppRoute.HOME
                            }
                        )
                    }
                    AppRoute.DESSERT_CLICKER -> {
                        DessertClickerScreen(
                            onBackClick = {
                                currentRoute = AppRoute.HOME
                            }
                        )
                    }
                    AppRoute.LAZY_GRID_PRACTICE -> {
                        LazyGridPracticeScreen(
                            onBackClick = {
                                currentRoute = AppRoute.HOME
                            }
                        )
                    }
                    AppRoute.LAZY_LIST_STATE_PRACTICE -> {
                        LazyListStatePracticeScreen(
                            onBackClick = {
                                currentRoute = AppRoute.HOME
                            }
                        )
                    }
                    AppRoute.ADVANCED_LAZY_LISTS_PRACTICE -> {
                        AdvancedLazyListsScreen(
                            onBackClick = {
                                currentRoute = AppRoute.HOME
                            }
                        )
                    }
                    AppRoute.ORDER_HISTORY -> {
                        OrderHistoryScreen(
                            onBackClick = {
                                currentRoute = AppRoute.HOME
                            }
                        )
                    }
                    AppRoute.DYNAMIC_LIST_ANIMATIONS -> {
                        DynamicListAnimationsScreen(
                            onBackClick = {
                                currentRoute = AppRoute.HOME
                            }
                        )
                    }
                    AppRoute.RACE_TRACKER -> {
                        RaceTrackerApp(
                            onBackClick = {
                                currentRoute = AppRoute.HOME
                            }
                        )
                    }
                    AppRoute.MARS_PHOTOS -> {
                        MarsPhotosApp(
                            onBackClick = {
                                currentRoute = AppRoute.HOME
                            }
                        )
                    }
                    AppRoute.AMPHIBIANS -> {
                        AmphibiansApp(
                            onBackClick = {
                                currentRoute = AppRoute.HOME
                            }
                        )
                    }
                    AppRoute.SQL_BASICS -> {
                        SqlBasicsScreen(
                            onBackClick = {
                                currentRoute = AppRoute.HOME
                            }
                        )
                    }
                    AppRoute.BUS_SCHEDULE -> {
                        BusScheduleApp(
                            onBackClick = {
                                currentRoute = AppRoute.HOME
                            }
                        )
                    }
                    AppRoute.INVENTORY -> {
                        InventoryApp(
                            onBackClick = {
                                currentRoute = AppRoute.HOME
                            }
                        )
                    }
                    AppRoute.DESSERT_RELEASE -> {
                        DessertReleaseApp(
                            onBackClick = {
                                currentRoute = AppRoute.HOME
                            }
                        )
                    }
                    AppRoute.FLIGHT_SEARCH -> {
                        FlightSearchApp(
                            onBackClick = {
                                currentRoute = AppRoute.HOME
                            }
                        )
                    }
                    AppRoute.BLUROMATIC -> {
                        BluromaticApp(
                            onBackClick = {
                                currentRoute = AppRoute.HOME
                            }
                        )
                    }
                    AppRoute.WATER_ME -> {
                        WaterMeApp(
                            onBackClick = {
                                currentRoute = AppRoute.HOME
                            }
                        )
                    }
                    AppRoute.JUICE_TRACKER -> {
                        JuiceTrackerScreen(
                            onBackClick = {
                                currentRoute = AppRoute.HOME
                            }
                        )
                    }
                }
            }
        }
    }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart Called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume Grade/Resume")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart Called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause Called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop Called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy Called")
    }
}
