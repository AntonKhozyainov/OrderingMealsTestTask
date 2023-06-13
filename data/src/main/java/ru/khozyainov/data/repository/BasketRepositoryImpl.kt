package ru.khozyainov.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.khozyainov.data.datasource.DishLocalDataSource
import ru.khozyainov.data.local.mapper.DishEntityMapper
import ru.khozyainov.domain.model.Dish
import ru.khozyainov.domain.repository.BasketRepository
import javax.inject.Inject

class BasketRepositoryImpl @Inject constructor(
    private val dishLocalDataSource: DishLocalDataSource,
    private val dishEntityMapper: DishEntityMapper
) : BasketRepository {

    override suspend fun getDishById(id: Int): Dish? =
        dishLocalDataSource.getDishById(id = id)?.let { dishEntityMapper.mapToDomain(entity = it) }

    override suspend fun deleteDishById(id: Int) = dishLocalDataSource.deleteDishById(id = id)

    override fun getDishes(): Flow<List<Dish>> = dishLocalDataSource.getDishes().map { dishes ->
        dishes.map { dishEntity ->
            dishEntityMapper.mapToDomain(entity = dishEntity)
        }
    }
}