package ru.khozyainov.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.khozyainov.data.local.AppDataBase.Companion.DB_VERSION
import ru.khozyainov.data.local.dao.DishDao
import ru.khozyainov.data.local.entity.DishEntity

@Database(
    entities = [DishEntity::class], version = DB_VERSION
)
abstract class AppDataBase : RoomDatabase() {

    abstract fun dishDao(): DishDao

    companion object {
        const val DB_VERSION = 1
        const val DB_NAME = "app_database"
    }
}