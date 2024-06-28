package com.example.fetch_task.network

import com.example.fetch_task.model.Item
import retrofit2.http.GET

interface ApiService {
    @GET("hiring.json")
    suspend fun getItems(): List<Item>
}