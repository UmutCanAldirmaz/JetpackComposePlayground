package com.example.jetpackcomposeplayground

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.*
import com.example.jetpackcomposeplayground.model.AppRoute
import com.example.jetpackcomposeplayground.ui.screens.AdvancedLazyListsScreen
import com.example.jetpackcomposeplayground.ui.screens.AffirmationsScreen
import com.example.jetpackcomposeplayground.ui.screens.ArtSpaceScreen
import com.example.jetpackcomposeplayground.ui.screens.CounterScreen
import com.example.jetpackcomposeplayground.ui.screens.DessertClickerScreen
import com.example.jetpackcomposeplayground.ui.screens.DiceRollerScreen
import com.example.jetpackcomposeplayground.ui.screens.ExerciseScreen
import com.example.jetpackcomposeplayground.ui.screens.HomeScreen
import com.example.jetpackcomposeplayground.ui.screens.LazyGridPracticeScreen
import com.example.jetpackcomposeplayground.ui.screens.LazyListStatePracticeScreen
import com.example.jetpackcomposeplayground.ui.screens.LemonadeScreen
import com.example.jetpackcomposeplayground.ui.screens.SuperHeroesScreen
import com.example.jetpackcomposeplayground.ui.screens.TipCalculatorScreen
import com.example.jetpackcomposeplayground.ui.screens.WoofScreen
import com.example.jetpackcomposeplayground.ui.theme.JetpackComposePlaygroundTheme

private const val TAG = "MainActivityDebugging"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate Called")
        enableEdgeToEdge()
        setContent {
            JetpackComposePlaygroundTheme {
                var currentRoute by remember { mutableStateOf(AppRoute.HOME) }

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
