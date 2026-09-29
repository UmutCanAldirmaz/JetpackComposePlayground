package com.example.jetpackcomposeplayground.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposeplayground.R
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LemonadeScreen(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    BackHandler(onBack = onBackClick)

    var step by remember { mutableStateOf(1) }
    var squeezeCount by remember { mutableStateOf(0) }
    var requiredSqueezes by remember { mutableStateOf(Random.nextInt(2, 5)) }

    val (instructionText, imageRes) = when (step) {
        1 -> Pair(stringResource(R.string.lemon_select_text), R.drawable.lemon_tree)
        2 -> Pair(stringResource(R.string.lemon_squeeze_text, squeezeCount, requiredSqueezes), R.drawable.lemon_squeeze)
        3 -> Pair(stringResource(R.string.lemon_drink_text), R.drawable.lemon_drink)
        else -> Pair(stringResource(R.string.lemon_restart_text), R.drawable.lemon_restart)
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.lemonade_title)) },
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
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(32.dp))
                    .border(2.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.5f), RoundedCornerShape(32.dp))
                    .background(MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.3f))
                    .clickable {
                        when (step) {
                            1 -> {
                                step = 2
                                squeezeCount = 0
                                requiredSqueezes = Random.nextInt(2, 5)
                            }
                            2 -> {
                                squeezeCount++
                                if (squeezeCount >= requiredSqueezes) {
                                    step = 3
                                }
                            }
                            3 -> {
                                step = 4
                            }
                            4 -> {
                                step = 1
                            }
                        }
                    }
                    .padding(24.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = imageRes),
                    contentDescription = instructionText,
                    modifier = Modifier.size(160.dp)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = instructionText,
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}
