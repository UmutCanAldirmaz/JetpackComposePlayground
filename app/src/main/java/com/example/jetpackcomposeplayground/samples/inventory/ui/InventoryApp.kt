package com.example.jetpackcomposeplayground.samples.inventory.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.jetpackcomposeplayground.R
import com.example.jetpackcomposeplayground.samples.inventory.ui.navigation.InventoryNavHost

/**
 * Inventory uygulamasının en üst seviyedeki ana Composable kapsayıcısı.
 *
 * @param onBackClick Örnek kataloğuna dönmek için kullanılan lambda.
 * @param navController Navigasyon denetleyicisi.
 */
@Composable
fun InventoryApp(
    onBackClick: () -> Unit = {},
    navController: NavHostController = rememberNavController()
) {
    InventoryNavHost(
        navController = navController,
        onBackClick = onBackClick
    )
}

/**
 * Ortalanmış başlık ve isteğe bağlı geri gezinme oku barındıran üst çubuk (TopAppBar) bileşeni.
 *
 * @param title Başlık metni.
 * @param canNavigateBack Geri navigasyon okunun gösterilip gösterilmeyeceği.
 * @param modifier Düzenleyici.
 * @param scrollBehavior Kaydırma davranışı.
 * @param navigateUp Geri butonuna tıklandığında çalışacak lambda.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InventoryTopAppBar(
    title: String,
    canNavigateBack: Boolean,
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    navigateUp: () -> Unit = {}
) {
    CenterAlignedTopAppBar(
        title = { Text(title) },
        modifier = modifier,
        scrollBehavior = scrollBehavior,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}
