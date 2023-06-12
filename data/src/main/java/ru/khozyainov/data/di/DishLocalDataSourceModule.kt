package ru.khozyainov.data.di

import dagger.Binds
import dagger.Module
import ru.khozyainov.data.datasource.DishLocalDataSource
import ru.khozyainov.data.datasource.DishLocalDataSourceImpl

@Module
abstract class DishLocalDataSourceModule {
    @Binds
    abstract fun provides(impl: DishLocalDataSourceImpl): DishLocalDataSource
}