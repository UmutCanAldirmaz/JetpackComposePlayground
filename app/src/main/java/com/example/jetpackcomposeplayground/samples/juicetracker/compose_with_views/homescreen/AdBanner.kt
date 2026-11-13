/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.jetpackcomposeplayground.samples.juicetracker.compose_with_views.homescreen

import android.view.Gravity
import android.widget.TextView
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView

/**
 * A composable which uses [AndroidView] to render a native Android View (e.g. AdView or Banner TextView).
 * Demonstrates View Interoperability in Jetpack Compose.
 */
@Composable
fun AdBanner(modifier: Modifier = Modifier) {
    val backgroundColor = MaterialTheme.colorScheme.secondaryContainer.toArgb()
    val textColor = MaterialTheme.colorScheme.onSecondaryContainer.toArgb()

    AndroidView(
        modifier = modifier.fillMaxWidth(),
        factory = { context ->
            TextView(context).apply {
                text = "📢 [AndroidView Interoperability]: Ad Banner View"
                gravity = Gravity.CENTER
                textSize = 14f
                setPadding(16, 24, 16, 24)
            }
        },
        update = { textView ->
            textView.setBackgroundColor(backgroundColor)
            textView.setTextColor(textColor)
        }
    )
}

@Preview
@Composable
fun PreviewAdBanner() {
    MaterialTheme {
        AdBanner()
    }
}
