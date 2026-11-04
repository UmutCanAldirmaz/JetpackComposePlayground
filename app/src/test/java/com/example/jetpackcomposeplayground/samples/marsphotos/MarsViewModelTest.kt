package com.example.jetpackcomposeplayground.samples.marsphotos

import com.example.jetpackcomposeplayground.samples.marsphotos.fake.FakeDataSource
import com.example.jetpackcomposeplayground.samples.marsphotos.fake.FakeNetworkMarsPhotosRepository
import com.example.jetpackcomposeplayground.samples.marsphotos.rules.TestDispatcherRule
import com.example.jetpackcomposeplayground.samples.marsphotos.ui.MarsUiState
import com.example.jetpackcomposeplayground.samples.marsphotos.ui.MarsViewModel
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class MarsViewModelTest {

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @Test
    fun marsViewModel_getMarsPhotos_verifyMarsUiStateSuccess() = runTest {
        val marsViewModel = MarsViewModel(
            marsPhotosRepository = FakeNetworkMarsPhotosRepository()
        )
        assertEquals(
            MarsUiState.Success(FakeDataSource.photosList),
            marsViewModel.marsUiState
        )
    }
}
