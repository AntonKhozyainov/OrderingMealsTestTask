package ru.khozyainov.domain.model

data class DishInBasket(
    val id: Int,
    val title: String,
    val price: Int,
    val weight: Int,
    val imageUrl: String,
    val count: Int
): Model()
