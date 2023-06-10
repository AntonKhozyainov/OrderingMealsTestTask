package ru.khozyainov.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.khozyainov.data.datasource.CategoryRemoteDataSource
import ru.khozyainov.data.mapper.CategoryRemoteMapper
import ru.khozyainov.domain.model.Category
import ru.khozyainov.domain.repository.CategoryRepository
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val categoryRemoteDataSource: CategoryRemoteDataSource,
    private val categoryRemoteMapper: CategoryRemoteMapper,
) : CategoryRepository {

    override suspend fun getCategories(): List<Category> = withContext(Dispatchers.IO) {
        categoryRemoteDataSource.getCategories().map { categoryRemote ->
            categoryRemoteMapper.mapToDomain(remote = categoryRemote)
        }
    }

}