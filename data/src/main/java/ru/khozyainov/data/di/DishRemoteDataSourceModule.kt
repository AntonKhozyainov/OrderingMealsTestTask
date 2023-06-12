package ru.khozyainov.data.di

import dagger.Binds
import dagger.Module
import ru.khozyainov.data.datasource.DishRemoteDataSource
import ru.khozyainov.data.datasource.DishRemoteDataSourceImpl

@Module
abstract class DishRemoteDataSourceModule {
    @Binds
    abstract fun provides(impl: DishRemoteDataSourceImpl): DishRemoteDataSource
}