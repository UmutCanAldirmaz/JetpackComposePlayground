package com.example.jetpackcomposeplayground.samples.reply

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
import com.example.jetpackcomposeplayground.samples.reply.ui.ReplyApp
import com.example.jetpackcomposeplayground.samples.reply.ui.theme.ReplyTheme

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun ReplySampleScreen(
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

    ReplyTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            ReplyApp(
                windowSize = windowSize,
                modifier = Modifier.fillMaxSize()
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
fun ReplyAppCompactPreview() {
    ReplyTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            ReplyApp(
                windowSize = WindowWidthSizeClass.Compact,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Preview(name = "Medium Screen (Tablet)", widthDp = 700, heightDp = 900, showBackground = true)
@Composable
fun ReplyAppMediumPreview() {
    ReplyTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            ReplyApp(
                windowSize = WindowWidthSizeClass.Medium,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Preview(name = "Expanded Screen (Desktop/Foldable)", widthDp = 1000, heightDp = 900, showBackground = true)
@Composable
fun ReplyAppExpandedPreview() {
    ReplyTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            ReplyApp(
                windowSize = WindowWidthSizeClass.Expanded,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}
