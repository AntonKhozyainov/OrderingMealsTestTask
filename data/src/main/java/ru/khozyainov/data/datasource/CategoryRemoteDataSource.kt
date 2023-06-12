package ru.khozyainov.data.datasource

import ru.khozyainov.data.remote.model.CategoryRemote

interface CategoryRemoteDataSource {
    suspend fun getCategories(): List<CategoryRemote>
}