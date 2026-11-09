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
 * Inventory uygulamasının ekranlar arası navigasyon grafiğini ([NavHost]) tanımlayan Composable bileşen.
 *
 * @param navController Navigasyon işlemlerini yöneten denetleyici.
 * @param onBackClick Ana ekrandan geriye basıldığında örnek kataloğuna dönmek için çağrılan lambda.
 * @param modifier Düzen düzenleyici parametresi.
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
        // 1. Ana Ekran Rrotası (HomeScreen)
        composable(route = HomeDestination.route) {
            HomeScreen(
                navigateToItemEntry = { navController.navigate(ItemEntryDestination.route) },
                navigateToItemUpdate = { id ->
                    navController.navigate("${ItemDetailsDestination.route}/$id")
                },
                onBackClick = onBackClick
            )
        }

        // 2. Ürün Ekleme Ekranı Rrotası (ItemEntryScreen)
        composable(route = ItemEntryDestination.route) {
            ItemEntryScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() }
            )
        }

        // 3. Ürün Detay Ekranı Rrotası (ItemDetailsScreen) - Parametre: itemId
        composable(
            route = ItemDetailsDestination.routeWithArgs,
            arguments = listOf(navArgument(ItemDetailsDestination.itemIdArg) {
                type = NavType.IntType
            })
        ) {
            ItemDetailsScreen(
                navigateToEditItem = { id -> navController.navigate("${ItemEditDestination.route}/$id") },
                navigateBack = { navController.navigateUp() }
            )
        }

        // 4. Ürün Düzenleme Ekranı Rrotası (ItemEditScreen) - Parametre: itemId
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
