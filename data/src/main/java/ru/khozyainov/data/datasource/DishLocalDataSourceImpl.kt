package ru.khozyainov.data.datasource

import kotlinx.coroutines.flow.Flow
import ru.khozyainov.data.local.dao.DishDao
import ru.khozyainov.data.local.entity.DishEntity
import javax.inject.Inject

class DishLocalDataSourceImpl @Inject constructor(
    private val dishDao: DishDao
): DishLocalDataSource {
    override suspend fun addDishToBasket(dish: DishEntity) = dishDao.insert(dish = dish)

    override suspend fun getDishById(id: Int): DishEntity? = dishDao.selectByID(id = id)

    override fun getDishes(): Flow<List<DishEntity>>  = dishDao.select()
}