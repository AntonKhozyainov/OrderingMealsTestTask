package ru.khozyainov.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.khozyainov.domain.model.DishInBasket

interface BasketRepository {
    suspend fun addDishToBasket(dish: DishInBasket)
    fun getDishesInBasket(): Flow<List<DishInBasket>>
}