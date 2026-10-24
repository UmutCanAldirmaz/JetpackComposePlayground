package com.example.jetpackcomposeplayground.samples.sandbox

import androidx.activity.compose.BackHandler
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposeplayground.R

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExerciseScreen(
    onBackClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    BackHandler(onBack = onBackClick)

    var count by remember { mutableIntStateOf(0) }
    var sliderValue by remember { mutableFloatStateOf(0.5f) }
    var isToggled by remember { mutableStateOf(false) }
    var textInput by remember { mutableStateOf("") }

    val boxColor by animateColorAsState(
        targetValue = if (isToggled) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.tertiary,
        label = "boxColor"
    )

    val scaleAnim by animateFloatAsState(
        targetValue = if (isToggled) 1.1f else 1.0f,
        label = "scaleAnim"
    )

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = Color.White,
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.exercise_title)) },
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
                .imePadding()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            // Interactive Experimental Box
            Box(
                modifier = Modifier
                    .size(140.dp)
                    .scale(scaleAnim)
                    .clip(RoundedCornerShape(24.dp))
                    .background(boxColor)
                    .clickable { isToggled = !isToggled },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = if (isToggled) "Aktif 🚀" else "Dokun! 🎨",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleMedium
                )
            }

            Text(
                text = "Kutuyu tıklayarak animasyonu test edin",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

            // Counter Section
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = "Sayaç Deneyi: $count",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Button(onClick = { count-- }) {
                            Text("Azalt (-)")
                        }
                        Button(onClick = { count++ }) {
                            Text("Arttır (+)")
                        }
                    }
                }
            }

            // Slider Section
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "Slider Değeri: ${(sliderValue * 100).toInt()}%",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold
                    )
                    Slider(
                        value = sliderValue,
                        onValueChange = { sliderValue = it }
                    )
                }
            }

            // Text Input Test Section
            OutlinedTextField(
                value = textInput,
                onValueChange = { textInput = it },
                label = { Text("Deneysel Metin Girişi") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            if (textInput.isNotEmpty()) {
                Text(
                    text = "Girdiğiniz: $textInput",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            TemperatureConverterScreen()
        }
    }
}

@Composable
fun TemperatureInput(
    temperature: String,
    onTemperatureChange: (String) -> Unit,
    label: String
) {
    OutlinedTextField(
        value = temperature,
        onValueChange = onTemperatureChange,
        label = { Text(label) },
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun TemperatureConverterScreen() {
    var celcius by remember { mutableStateOf("") }
    var fahrenheit by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Sıcaklık Dönüştürücü",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        TemperatureInput(
            temperature = celcius,
            onTemperatureChange = {
                celcius = it
                fahrenheit = convertToFahrenheit(it)
            },
            label = "Celsius"
        )
        TemperatureInput(
            temperature = fahrenheit,
            onTemperatureChange = {
                fahrenheit = it
                celcius = convertToCelsius(it)
            },
            label = "Fahrenheit"
        )
    }
}

fun convertToCelsius(fahrenheit: String): String {
    val f = fahrenheit.toDoubleOrNull() ?: return ""
    return ((f - 32) * 5 / 9).toString()
}

fun convertToFahrenheit(celsius: String): String {
    val c = celsius.toDoubleOrNull() ?: return ""
    return ((c * 9 / 5) + 32).toString()
}
