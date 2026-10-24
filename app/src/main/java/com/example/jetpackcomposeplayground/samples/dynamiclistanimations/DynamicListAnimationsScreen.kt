package com.example.jetpackcomposeplayground.samples.dynamiclistanimations

import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Shuffle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposeplayground.R

private data class CartItem(val id: Int, val name: String, val price: String)
private data class CharacterItem(val id: Int, val name: String, val color: Color)

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DynamicListAnimationsScreen(
    onBackClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    BackHandler(onBack = onBackClick)

    // Fruit List State for Reordering Demo
    var fruits by remember {
        mutableStateOf(
            listOf(
                Pair(1, "🍎 Elma"),
                Pair(2, "🍐 Armut"),
                Pair(3, "🍌 Muz"),
                Pair(4, "🍓 Çilek")
            )
        )
    }

    // Exercise 1: Shopping Cart State (5 items)
    var cartItems by remember {
        mutableStateOf(
            listOf(
                CartItem(1, "Kablosuz Kulaklık", "1.200 ₺"),
                CartItem(2, "Mekanik Klavye", "2.500 ₺"),
                CartItem(3, "Akıllı Saat", "3.100 ₺"),
                CartItem(4, "Oyuncu Mouse", "850 ₺"),
                CartItem(5, "USB-C Hub", "450 ₺")
            )
        )
    }

    // Exercise 2: Character Grid State (6 items)
    var characters by remember {
        mutableStateOf(
            listOf(
                CharacterItem(1, "Iron Man 🔴", Color(0xFFFFEBEE)),
                CharacterItem(2, "Thor ⚡", Color(0xFFFFF8E1)),
                CharacterItem(3, "Hulk 🟢", Color(0xFFE8F5E9)),
                CharacterItem(4, "Captain 🛡️", Color(0xFFE3F2FD)),
                CharacterItem(5, "Spidey 🕷️", Color(0xFFF3E5F5)),
                CharacterItem(6, "Panther 🐾", Color(0xFFE0F2F1))
            )
        )
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = Color.White,
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.dynamic_animations_title)) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back_button)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            // Section 1 & 3: Reordering & Removal Demo (Fruit List)
            Text(
                text = "1. Sıralama ve Yer Değiştirme (AnimateItem)",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(
                    onClick = { fruits = fruits.reversed() },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Ters Çevir")
                }
                Button(
                    onClick = {
                        if (fruits.isNotEmpty()) {
                            fruits = fruits.drop(1)
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error),
                    modifier = Modifier.weight(1f)
                ) {
                    Text("İlkini Sil")
                }
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(
                    items = fruits,
                    key = { fruit -> fruit.first }
                ) { fruit ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .animateItem(),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFE0F7FA)),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(
                            text = fruit.second,
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
            }

            HorizontalDivider()

            // Exercise 1: Shopping Cart Item Removal with fadeOutSpec (400ms tween)
            Text(
                text = "2. Sepetten Eleman Kaldırma (fadeOutSpec = 400ms)",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )

            Text(
                text = "Çöp kutusuna bastığınızda eleman fadeOutSpec (400ms tween) ile yavaşça silinir, altındaki elemanlar yumuşakça yukarı kayar.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            if (cartItems.isEmpty()) {
                Button(
                    onClick = {
                        cartItems = listOf(
                            CartItem(1, "Kablosuz Kulaklık", "1.200 ₺"),
                            CartItem(2, "Mekanik Klavye", "2.500 ₺"),
                            CartItem(3, "Akıllı Saat", "3.100 ₺"),
                            CartItem(4, "Oyuncu Mouse", "850 ₺"),
                            CartItem(5, "USB-C Hub", "450 ₺")
                        )
                    },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Icon(Icons.Default.Refresh, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Sepeti Sıfırla")
                }
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(
                    items = cartItems,
                    key = { item -> item.id }
                ) { item ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .animateItem(
                                fadeInSpec = tween<Float>(durationMillis = 250),
                                fadeOutSpec = tween<Float>(durationMillis = 400),
                                placementSpec = spring(
                                    dampingRatio = Spring.DampingRatioMediumBouncy,
                                    stiffness = Spring.StiffnessLow
                                )
                            ),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text(
                                    text = item.name,
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = item.price,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            }
                            IconButton(
                                onClick = {
                                    cartItems = cartItems.filter { it.id != item.id }
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = "Sil",
                                    tint = MaterialTheme.colorScheme.error
                                )
                            }
                        }
                    }
                }
            }

            HorizontalDivider()

            // Exercise 2: Character Sorting Grid (LazyVerticalGrid with .shuffled())
            Text(
                text = "3. Karakter Sıralama Izgarası (Grid Shuffling)",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )

            Button(
                onClick = { characters = characters.shuffled() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(Icons.Default.Shuffle, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Karakterleri Karıştır (shuffled)")
            }

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(
                    items = characters,
                    key = { char -> char.id }
                ) { char ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(80.dp)
                            .animateItem(
                                placementSpec = spring(
                                    dampingRatio = Spring.DampingRatioMediumBouncy,
                                    stiffness = Spring.StiffnessLow
                                )
                            ),
                        colors = CardDefaults.cardColors(containerColor = char.color),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = char.name,
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF1C1B1F)
                            )
                        }
                    }
                }
            }
        }
    }
}
