package ru.khozyainov.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.khozyainov.data.local.entity.DishEntity
import ru.khozyainov.data.local.entity.DishEntityContract.Columns.ID
import ru.khozyainov.data.local.entity.DishEntityContract.TABLE_NAME

@Dao
interface DishDao {

    @Query("SELECT * FROM $TABLE_NAME")
    fun select(): Flow<List<DishEntity>>

    @Query("SELECT * FROM $TABLE_NAME WHERE $ID = :id")
    suspend fun selectByID(id: Int): DishEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(dish: DishEntity)

    @Query("DELETE FROM $TABLE_NAME WHERE $ID = :id")
    suspend fun deleteById(id: Int)
}