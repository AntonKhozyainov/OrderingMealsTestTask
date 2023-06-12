package ru.khozyainov.data.datasource

import ru.khozyainov.data.remote.model.DishRemote

interface DishRemoteDataSource {
    suspend fun getDishes(): List<DishRemote>
}