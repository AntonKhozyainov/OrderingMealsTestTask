package ru.khozyainov.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.khozyainov.domain.model.Dish

interface BasketRepository {
    fun getDishes(): Flow<List<Dish>>
    suspend fun getDishById(id: Int): Dish?
    suspend fun deleteDishById(id: Int)
}