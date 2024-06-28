package com.example.fetch_task.ui


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fetch_task.model.Item
import com.example.fetch_task.repository.ItemRepository
import kotlinx.coroutines.launch

class ItemViewModel : ViewModel() {
    private val repository = ItemRepository()
    private val _groupedItems = MutableLiveData<Map<Int, List<Item>>>()
    val groupedItems: LiveData<Map<Int, List<Item>>> get() = _groupedItems

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _error = MutableLiveData<String?>()
    val error: MutableLiveData<String?> get() = _error

    init {
        fetchItems()
    }

     fun fetchItems() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val items = repository.fetchItems()
                _groupedItems.postValue(items)
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }
}
