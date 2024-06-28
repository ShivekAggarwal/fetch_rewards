package com.example.fetch_task.repository


import com.example.fetch_task.model.Item
import com.example.fetch_task.network.ApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ItemRepository {

    suspend fun fetchItems(): Map<Int, List<Item>> {
        return withContext(Dispatchers.IO) {
            try {
                ApiClient.apiService.getItems()
                    .filter { !it.name.isNullOrBlank() }
                    .sortedWith(compareBy<Item> { it.listId }.thenBy { it.name })
                    .groupBy { it.listId }
            } catch (e: Exception) {
                emptyMap()
            }
        }
    }
}
