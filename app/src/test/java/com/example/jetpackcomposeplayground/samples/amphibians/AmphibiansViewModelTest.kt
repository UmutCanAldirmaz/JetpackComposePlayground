package com.example.jetpackcomposeplayground.samples.amphibians

import com.example.jetpackcomposeplayground.samples.amphibians.fake.FakeAmphibiansRepository
import com.example.jetpackcomposeplayground.samples.amphibians.fake.FakeDataSource
import com.example.jetpackcomposeplayground.samples.amphibians.rules.TestDispatcherRule
import com.example.jetpackcomposeplayground.samples.amphibians.ui.AmphibiansUiState
import com.example.jetpackcomposeplayground.samples.amphibians.ui.AmphibiansViewModel
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class AmphibiansViewModelTest {

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @Test
    fun amphibiansViewModel_getAmphibians_verifyAmphibiansUiStateSuccess() = runTest {
        val amphibiansViewModel = AmphibiansViewModel(
            amphibiansRepository = FakeAmphibiansRepository()
        )
        assertEquals(
            AmphibiansUiState.Success(FakeDataSource.amphibiansList),
            amphibiansViewModel.amphibiansUiState
        )
    }
}
