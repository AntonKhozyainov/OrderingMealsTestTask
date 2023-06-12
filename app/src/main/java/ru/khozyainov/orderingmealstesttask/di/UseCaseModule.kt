package ru.khozyainov.orderingmealstesttask.di

import dagger.Module
import dagger.Provides
import ru.khozyainov.domain.usecase.dish.GetSelectionListFromDishesUseCase
import javax.inject.Singleton

@Module
object UseCaseModule {

    @Provides
    @Singleton
    fun providesGetSelectionListFromDishesUseCase(): GetSelectionListFromDishesUseCase =
        GetSelectionListFromDishesUseCase()
}