package ru.khozyainov.data.datasource

import ru.khozyainov.data.api.CategoryApi
import ru.khozyainov.data.model.CategoryRemote
import javax.inject.Inject

class CategoryRemoteDataSourceImpl @Inject constructor(
    private val categoryApi: CategoryApi
): CategoryRemoteDataSource {
    override suspend fun getCategories(): List<CategoryRemote> = categoryApi.getCategories().categories
}