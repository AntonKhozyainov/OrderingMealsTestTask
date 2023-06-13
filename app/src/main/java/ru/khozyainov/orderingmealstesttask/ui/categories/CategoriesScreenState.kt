package ru.khozyainov.orderingmealstesttask.ui.categories

import ru.khozyainov.domain.model.Category

sealed class CategoriesScreenState {
    object Loading : CategoriesScreenState()
    data class Error(val throwable: Throwable) : CategoriesScreenState()
    data class Success(val categories: List<Category>) : CategoriesScreenState()
}
