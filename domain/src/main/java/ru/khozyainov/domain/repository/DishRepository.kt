package ru.khozyainov.domain.repository

import ru.khozyainov.domain.model.Dish
import ru.khozyainov.domain.model.Tag

interface DishRepository {
    suspend fun getDishes(tag: Tag?): List<Dish>
    suspend fun addDishToBasket(dish: Dish)
}