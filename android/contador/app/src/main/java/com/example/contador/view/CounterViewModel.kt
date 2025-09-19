package com.example.teste.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.teste.manager.CounterDataStoreManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CounterViewModel(private val dataStoreManager: CounterDataStoreManager) : ViewModel() {
    private val _clickCount = MutableStateFlow(0)

    val clickCount = _clickCount.asStateFlow()

    init {
        viewModelScope.launch {
            dataStoreManager.counterFlow.collect { count ->
                _clickCount.value = count
            }
        }
    }

    fun incrementCounter() {
        viewModelScope.launch {
            dataStoreManager.incrementCounter()
        }
    }
}

class CounterViewModelFactory(private val dataStoreManager: CounterDataStoreManager) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CounterViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CounterViewModel(dataStoreManager) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}