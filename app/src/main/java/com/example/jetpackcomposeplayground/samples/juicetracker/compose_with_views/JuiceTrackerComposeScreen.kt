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

package com.example.jetpackcomposeplayground.samples.juicetracker.compose_with_views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.jetpackcomposeplayground.R
import com.example.jetpackcomposeplayground.samples.juicetracker.data.Juice
import com.example.jetpackcomposeplayground.samples.juicetracker.ui.AppViewModelProvider
import com.example.jetpackcomposeplayground.samples.juicetracker.views_with_compose.ListItem
import com.example.jetpackcomposeplayground.samples.juicetracker.compose_with_views.bottomsheet.EntryBottomSheet
import com.example.jetpackcomposeplayground.samples.juicetracker.compose_with_views.homescreen.AdBanner
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JuiceTrackerComposeScreenContent(
    modifier: Modifier = Modifier,
    juiceTrackerViewModel: JuiceTrackerComposeViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberStandardBottomSheetState(
            initialValue = SheetValue.Hidden,
            skipHiddenState = false,
        )
    )

    val scope = rememberCoroutineScope()
    val trackerState by juiceTrackerViewModel.juiceListStream.collectAsState(emptyList())

    EntryBottomSheet(
        juiceTrackerViewModel = juiceTrackerViewModel,
        sheetScaffoldState = bottomSheetScaffoldState,
        modifier = modifier,
        onCancel = {
            scope.launch {
                bottomSheetScaffoldState.bottomSheetState.hide()
            }
        },
        onSubmit = {
            juiceTrackerViewModel.saveJuice()
            scope.launch {
                bottomSheetScaffoldState.bottomSheetState.hide()
            }
        }
    ) {
        Scaffold(
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        juiceTrackerViewModel.resetCurrentJuice()
                        scope.launch { bottomSheetScaffoldState.bottomSheetState.expand() }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = stringResource(R.string.add_juice)
                    )
                }
            }
        ) { contentPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(contentPadding)
            ) {
                // [AndroidView Interoperability]: Ad Banner View embedded in Compose
                AdBanner(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = dimensionResource(R.dimen.padding_medium),
                            bottom = dimensionResource(R.dimen.padding_small)
                        )
                )

                JuiceTrackerComposeList(
                    juices = trackerState,
                    onDelete = { juice -> juiceTrackerViewModel.deleteJuice(juice) },
                    onUpdate = { juice ->
                        juiceTrackerViewModel.updateCurrentJuice(juice)
                        scope.launch {
                            bottomSheetScaffoldState.bottomSheetState.expand()
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun JuiceTrackerComposeList(
    juices: List<Juice>,
    onDelete: (Juice) -> Unit,
    onUpdate: (Juice) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(vertical = dimensionResource(R.dimen.padding_small))
    ) {
        items(items = juices) { juice ->
            ListItem(
                input = juice,
                onDelete = onDelete,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onUpdate(juice) }
                    .padding(
                        vertical = dimensionResource(R.dimen.padding_small),
                        horizontal = dimensionResource(R.dimen.padding_medium)
                    )
            )
        }
    }
}
