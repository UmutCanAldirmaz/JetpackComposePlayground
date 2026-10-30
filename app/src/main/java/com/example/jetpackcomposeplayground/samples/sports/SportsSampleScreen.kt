package com.example.jetpackcomposeplayground.samples.sports

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpackcomposeplayground.samples.sports.ui.SportsApp
import com.example.jetpackcomposeplayground.samples.sports.ui.theme.SportsTheme

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun SportsSampleScreen(
    onBackClick: () -> Unit = {}
) {
    BackHandler(onBack = onBackClick)
    val context = LocalContext.current
    val activity = context.findActivity()
    val windowSize = if (activity != null) {
        calculateWindowSizeClass(activity).widthSizeClass
    } else {
        WindowWidthSizeClass.Compact
    }

    SportsTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            SportsApp(
                windowSize = windowSize,
                onBackPressed = onBackClick
            )
        }
    }
}

tailrec fun Context.findActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}

@Preview(name = "Compact Screen (Phone)", widthDp = 400, heightDp = 900, showBackground = true)
@Composable
fun SportsAppCompactPreview() {
    SportsTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            SportsApp(
                windowSize = WindowWidthSizeClass.Compact,
                onBackPressed = {}
            )
        }
    }
}

@Preview(name = "Expanded Screen (Tablet)", widthDp = 1000, heightDp = 900, showBackground = true)
@Composable
fun SportsAppExpandedPreview() {
    SportsTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            SportsApp(
                windowSize = WindowWidthSizeClass.Expanded,
                onBackPressed = {}
            )
        }
    }
}
