package ru.khozyainov.orderingmealstesttask.ui.basket

import ru.khozyainov.domain.model.DishInBasket

sealed class BasketScreenState{
    object Loading: BasketScreenState()
    data class Error(val throwable: Throwable): BasketScreenState()
    data class Success(val dishes: List<DishInBasket>): BasketScreenState()
}
