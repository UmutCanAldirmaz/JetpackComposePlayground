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
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidViewBinding
import com.example.jetpackcomposeplayground.R
import com.example.jetpackcomposeplayground.databinding.ActivityJuiceTrackerBinding
import com.example.jetpackcomposeplayground.samples.juicetracker.compose_with_views.JuiceTrackerComposeScreenContent

/**
 * Juice Tracker Main Interoperability Screen.
 * Combines both View Interoperability techniques in a single unified interface with Tabs:
 * 1. "Views with Compose": XML Navigation Graph & Fragments with ComposeView inside RecyclerView.
 * 2. "Compose with Views": Pure Compose UI with AndroidView (RatingBar, Spinner, AdBanner).
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JuiceTrackerScreen(
    onBackClick: () -> Unit = {}
) {
    BackHandler {
        onBackClick()
    }

    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val tabs = listOf("Views in Compose", "Compose with Views")

    Scaffold(
        topBar = {
            Column {
                TopAppBar(
                    title = {
                        Text(
                            text = stringResource(R.string.juice_tracker_title),
                            fontWeight = FontWeight.Bold
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = onBackClick) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = stringResource(R.string.back_button)
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = androidx.compose.ui.graphics.Color(0xFFD84315),
                        titleContentColor = androidx.compose.ui.graphics.Color.White,
                        navigationIconContentColor = androidx.compose.ui.graphics.Color.White
                    )
                )
                PrimaryTabRow(
                    selectedTabIndex = selectedTabIndex,
                    containerColor = androidx.compose.ui.graphics.Color(0xFFBF360C),
                    contentColor = androidx.compose.ui.graphics.Color.White
                ) {
                    tabs.forEachIndexed { index, title ->
                        Tab(
                            selected = selectedTabIndex == index,
                            onClick = { selectedTabIndex = index },
                            selectedContentColor = androidx.compose.ui.graphics.Color.White,
                            unselectedContentColor = androidx.compose.ui.graphics.Color(0xFFFFCCBC),
                            text = {
                                Text(
                                    text = title,
                                    fontWeight = if (selectedTabIndex == index) FontWeight.Bold else FontWeight.Normal
                                )
                            }
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (selectedTabIndex) {
                0 -> {
                    // Option 1: Views with Compose (AndroidViewBinding hosting XML Navigation Graph & Fragments)
                    AndroidViewBinding(
                        factory = ActivityJuiceTrackerBinding::inflate,
                        modifier = Modifier.fillMaxSize()
                    )
                }
                1 -> {
                    // Option 2: Compose with Views (Pure Compose UI embedding AndroidView RatingBar/Spinner/AdBanner)
                    JuiceTrackerComposeScreenContent()
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewJuiceTrackerScreen() {
    MaterialTheme {
        JuiceTrackerScreen()
    }
}
