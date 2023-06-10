package ru.khozyainov.data.datasource

import ru.khozyainov.data.model.CategoryRemote

interface CategoryRemoteDataSource {
    suspend fun getCategories(): List<CategoryRemote>
}