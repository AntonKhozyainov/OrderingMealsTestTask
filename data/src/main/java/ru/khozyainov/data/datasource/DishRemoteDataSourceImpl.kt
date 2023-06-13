package ru.khozyainov.data.datasource

import ru.khozyainov.data.remote.api.DishApi
import ru.khozyainov.data.remote.model.DishRemote
import javax.inject.Inject

class DishRemoteDataSourceImpl @Inject constructor(
    private val dishApi: DishApi
) : DishRemoteDataSource {
    override suspend fun getDishes(): List<DishRemote> = dishApi.getDishes().dishes
}