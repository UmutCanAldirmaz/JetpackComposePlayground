package com.example.jetpackcomposeplayground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.*
import com.example.jetpackcomposeplayground.model.AppRoute
import com.example.jetpackcomposeplayground.ui.screens.AffirmationsScreen
import com.example.jetpackcomposeplayground.ui.screens.ArtSpaceScreen
import com.example.jetpackcomposeplayground.ui.screens.DiceRollerScreen
import com.example.jetpackcomposeplayground.ui.screens.HomeScreen
import com.example.jetpackcomposeplayground.ui.screens.LemonadeScreen
import com.example.jetpackcomposeplayground.ui.screens.SuperHeroesScreen
import com.example.jetpackcomposeplayground.ui.screens.TipCalculatorScreen
import com.example.jetpackcomposeplayground.ui.screens.WoofScreen
import com.example.jetpackcomposeplayground.ui.theme.JetpackComposePlaygroundTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
                }
            }
        }
    }
}
