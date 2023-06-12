package ru.khozyainov.orderingmealstesttask.di

import dagger.Binds
import dagger.Module
import ru.khozyainov.data.repository.BasketRepositoryImpl
import ru.khozyainov.domain.repository.BasketRepository

@Module
abstract class BasketRepositoryModule {
    @Binds
    abstract fun provides(impl: BasketRepositoryImpl): BasketRepository
}