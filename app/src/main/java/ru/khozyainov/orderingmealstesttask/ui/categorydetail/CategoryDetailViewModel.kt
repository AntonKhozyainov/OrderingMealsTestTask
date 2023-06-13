package ru.khozyainov.orderingmealstesttask.ui.categorydetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.khozyainov.domain.model.Tag
import ru.khozyainov.domain.usecase.dish.GetDishesUseCase
import ru.khozyainov.domain.usecase.dish.GetSelectionListFromDishesUseCase
import ru.khozyainov.orderingmealstesttask.mapper.DishUiMapper
import javax.inject.Inject

class CategoryDetailViewModel @Inject constructor(
    private val getDishesUseCase: GetDishesUseCase,
    private val getSelectionListFromDishesUseCase: GetSelectionListFromDishesUseCase,
    private val dishUiMapper: DishUiMapper
) : ViewModel() {

    private val uiMutableState =
        MutableStateFlow<CategoryDetailScreenState>(CategoryDetailScreenState.Loading)
    val uiState: StateFlow<CategoryDetailScreenState> = uiMutableState

    private val errorHandler = CoroutineExceptionHandler { _, throwable ->
        uiMutableState.value = CategoryDetailScreenState.Error(throwable = throwable)
    }

    init {
        refresh()
    }

    fun refresh() {
        viewModelScope.launch(errorHandler) {
            val dishes = getDishesUseCase()
            val uiDishes = dishes.map { dishUiMapper.mapToUI(model = it) }
            uiMutableState.value = CategoryDetailScreenState.Success(
                dishes = uiDishes, selection = getSelectionListFromDishesUseCase(dishes = dishes)
            )
        }
    }

    fun setSelection(tag: Tag) {
        if (uiMutableState.value !is CategoryDetailScreenState.Success) return
        val currentState = uiMutableState.value as CategoryDetailScreenState.Success
        viewModelScope.launch(errorHandler) {
            uiMutableState.value = CategoryDetailScreenState.Success(
                dishes = getDishesUseCase(tag = tag).map { dishUiMapper.mapToUI(model = it) },
                selection = mapSelectionList(selection = currentState.selection, tag = tag)
            )
        }
    }

    private fun mapSelectionList(selection: List<Tag>, tag: Tag): List<Tag> =
        selection.map { currentTag ->
            if (currentTag == tag) currentTag.copy(isSelected = true)
            else currentTag.copy(isSelected = false)
        }

}