package ru.khozyainov.orderingmealstesttask.di

import dagger.Binds
import dagger.Module
import ru.khozyainov.data.repository.CategoryRepositoryImpl
import ru.khozyainov.domain.repository.CategoryRepository

@Module
abstract class CategoryRepositoryModule {
    @Binds
    abstract fun provides(impl: CategoryRepositoryImpl): CategoryRepository
}