package com.example.jetpackcomposeplayground.samples.inventory.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.jetpackcomposeplayground.samples.inventory.ui.home.HomeDestination
import com.example.jetpackcomposeplayground.samples.inventory.ui.home.HomeScreen
import com.example.jetpackcomposeplayground.samples.inventory.ui.item.ItemDetailsDestination
import com.example.jetpackcomposeplayground.samples.inventory.ui.item.ItemDetailsScreen
import com.example.jetpackcomposeplayground.samples.inventory.ui.item.ItemEditDestination
import com.example.jetpackcomposeplayground.samples.inventory.ui.item.ItemEditScreen
import com.example.jetpackcomposeplayground.samples.inventory.ui.item.ItemEntryDestination
import com.example.jetpackcomposeplayground.samples.inventory.ui.item.ItemEntryScreen

/**
 * Provides Navigation graph for the application.
 */
@Composable
fun InventoryNavHost(
    navController: NavHostController,
    onBackClick: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = HomeDestination.route,
        modifier = modifier
    ) {
        composable(route = HomeDestination.route) {
            HomeScreen(
                navigateToItemEntry = { navController.navigate(ItemEntryDestination.route) },
                navigateToItemUpdate = {
                    navController.navigate("${ItemDetailsDestination.route}/${it}")
                },
                onBackClick = onBackClick
            )
        }
        composable(route = ItemEntryDestination.route) {
            ItemEntryScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() }
            )
        }
        composable(
            route = ItemDetailsDestination.routeWithArgs,
            arguments = listOf(navArgument(ItemDetailsDestination.itemIdArg) {
                type = NavType.IntType
            })
        ) {
            ItemDetailsScreen(
                navigateToEditItem = { navController.navigate("${ItemEditDestination.route}/$it") },
                navigateBack = { navController.navigateUp() }
            )
        }
        composable(
            route = ItemEditDestination.routeWithArgs,
            arguments = listOf(navArgument(ItemEditDestination.itemIdArg) {
                type = NavType.IntType
            })
        ) {
            ItemEditScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() }
            )
        }
    }
}
