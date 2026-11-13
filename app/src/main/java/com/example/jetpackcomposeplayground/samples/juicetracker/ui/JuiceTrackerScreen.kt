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

package com.example.jetpackcomposeplayground.samples.juicetracker.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.viewinterop.AndroidViewBinding
import com.example.jetpackcomposeplayground.R
import com.example.jetpackcomposeplayground.databinding.ActivityJuiceTrackerBinding

/**
 * Bu Composable ekranı, Jetpack Compose ile Android XML Views & Fragment yapısını
 * birlikte çalıştıran (Compose-View Interoperability) ana ekran bileşenidir.
 * [AndroidViewBinding] kullanarak [ActivityJuiceTrackerBinding] üzerinden XML Navigation Graph'ı Compose içinde çalıştırır.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JuiceTrackerScreen(
    onBackClick: () -> Unit = {}
) {
    BackHandler {
        onBackClick()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.juice_tracker_title)) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back_button)
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        AndroidViewBinding(
            factory = ActivityJuiceTrackerBinding::inflate,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        )
    }
}
