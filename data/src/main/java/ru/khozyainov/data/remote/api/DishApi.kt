package ru.khozyainov.data.remote.api

import retrofit2.http.GET
import ru.khozyainov.data.remote.model.DishesRemote

interface DishApi {
    @GET("c7a508f2-a904-498a-8539-09d96785446e")
    suspend fun getDishes(): DishesRemote
}