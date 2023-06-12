package ru.khozyainov.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.khozyainov.data.datasource.DishRemoteDataSource
import ru.khozyainov.data.remote.mapper.DishRemoteMapper
import ru.khozyainov.domain.model.Dish
import ru.khozyainov.domain.model.Tag
import ru.khozyainov.domain.repository.DishRepository
import javax.inject.Inject

class DishRepositoryImpl @Inject constructor(
    private val dishRemoteDataSource: DishRemoteDataSource,
    private val dishRemoteMapper: DishRemoteMapper
): DishRepository {
    override suspend fun getDishes(tag: Tag?): List<Dish>  = withContext(Dispatchers.IO) {
        dishRemoteDataSource.getDishes().map { dishRemote ->
            dishRemoteMapper.mapToDomain(remote = dishRemote)
        }.filter { dish ->
            if (tag == null) true
            else dish.tags.contains(tag.name)
        }
    }

//    override suspend fun addDishToBasket(dish: Dish) {
//        TODO("Not yet implemented")
//    }
}