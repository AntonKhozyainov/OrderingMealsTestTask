package ru.khozyainov.orderingmealstesttask.di

import dagger.Binds
import dagger.Module
import ru.khozyainov.data.repository.CategoryRepositoryImpl
import ru.khozyainov.data.repository.DishRepositoryImpl
import ru.khozyainov.domain.repository.CategoryRepository
import ru.khozyainov.domain.repository.DishRepository

@Module
abstract class DishRepositoryModule {
    @Binds
    abstract fun provides(impl: DishRepositoryImpl): DishRepository
}