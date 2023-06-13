package ru.khozyainov.orderingmealstesttask.ui.basket

import ru.khozyainov.orderingmealstesttask.model.DishUi

sealed class BasketScreenState {
    object Loading : BasketScreenState()
    data class Error(val throwable: Throwable) : BasketScreenState()
    data class Success(
        val dishes: List<DishUi>, val basketSum: Int = 0
    ) : BasketScreenState()
}
