package ru.khozyainov.orderingmealstesttask.ui.categorydetail

import ru.khozyainov.domain.model.Tag
import ru.khozyainov.orderingmealstesttask.model.DishUi

sealed class CategoryDetailScreenState {
    object Loading : CategoryDetailScreenState()
    data class Error(val throwable: Throwable) : CategoryDetailScreenState()
    data class Success(
        val dishes: List<DishUi>,
        val selection: List<Tag>
    ) : CategoryDetailScreenState()
}
