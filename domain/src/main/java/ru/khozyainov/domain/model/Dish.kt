package ru.khozyainov.domain.model

data class Dish(
    val id: Int,
    val title: String,
    val price: Int,
    val weight: Int,
    val imageUrl: String,
    val description: String,
    val tags: List<String>
): Model()
