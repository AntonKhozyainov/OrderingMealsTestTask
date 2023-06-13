package ru.khozyainov.data.datasource

import kotlinx.coroutines.flow.Flow
import ru.khozyainov.data.local.entity.DishEntity

interface DishLocalDataSource {
    suspend fun addDishToBasket(dish: DishEntity)
    suspend fun getDishById(id: Int): DishEntity?
    fun getDishes(): Flow<List<DishEntity>>
    suspend fun deleteDishById(id: Int)
}