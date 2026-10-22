package com.example.jetpackcomposeplayground.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CounterViewModel : ViewModel() {
    private val _count = MutableStateFlow(0)
    val count: StateFlow<Int> = _count.asStateFlow()


    init {
        viewModelScope.launch {
            while (true) {
                delay(1000L)
                _count.value++
                android.util.Log.d("CounterViewModel", "(VM) Sayaç değeri güncellendi:${_count.value}")
            }
        }
    }
}