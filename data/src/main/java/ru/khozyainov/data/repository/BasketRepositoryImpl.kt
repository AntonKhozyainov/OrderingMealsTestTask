package ru.khozyainov.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.khozyainov.data.datasource.DishLocalDataSource
import ru.khozyainov.data.local.entity.DishEntity
import ru.khozyainov.data.local.mapper.DishEntityMapper
import ru.khozyainov.domain.model.DishInBasket
import ru.khozyainov.domain.repository.BasketRepository
import javax.inject.Inject

class BasketRepositoryImpl @Inject constructor(
    private val dishLocalDataSource: DishLocalDataSource,
    private val dishEntityMapper: DishEntityMapper
): BasketRepository {

    override suspend fun addDishToBasket(dish: DishInBasket) {
        dishLocalDataSource.addDishToBasket(
            dish = getDishEntityForAdd(dish = dishEntityMapper.mapToEntity(model = dish))
        )
    }

    override fun getDishesInBasket(): Flow<List<DishInBasket>> =
        dishLocalDataSource.getDishes().map { dishes ->
            dishes.map { dishEntity ->
                dishEntityMapper.mapToDomain(entity = dishEntity)
            }
        }


    private suspend fun getDishEntityForAdd(dish: DishEntity): DishEntity{
        val dishInBasket = dishLocalDataSource.getDishById(id = dish.id)
        return dishInBasket?.copy(count = dishInBasket.count + 1) ?: dish

    }
}