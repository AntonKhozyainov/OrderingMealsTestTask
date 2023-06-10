package ru.khozyainov.domain.repository

import ru.khozyainov.domain.model.Category

interface CategoryRepository {
    suspend fun getCategories(): List<Category>
}