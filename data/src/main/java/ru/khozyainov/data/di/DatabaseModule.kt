package ru.khozyainov.data.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.khozyainov.data.local.AppDataBase
import ru.khozyainov.data.local.dao.DishDao
import javax.inject.Singleton

@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun providesDatabase(context: Context): AppDataBase = Room.databaseBuilder(
        context, AppDataBase::class.java, AppDataBase.DB_NAME
    ).build()

    @Provides
    fun providesDishDao(db: AppDataBase): DishDao = db.dishDao()

}