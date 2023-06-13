package ru.khozyainov.orderingmealstesttask.ui.categories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.khozyainov.domain.usecase.category.GetCategoriesUseCase
import javax.inject.Inject

class CategoriesViewModel @Inject constructor(
    private val cetCategoriesUseCase: GetCategoriesUseCase
) : ViewModel() {

    private val uiMutableState =
        MutableStateFlow<CategoriesScreenState>(CategoriesScreenState.Loading)
    val uiState: StateFlow<CategoriesScreenState> = uiMutableState

    private var currentJob: Job? = null

    private val errorHandler = CoroutineExceptionHandler { _, throwable ->
        uiMutableState.value = CategoriesScreenState.Error(throwable = throwable)
    }

    init {
        refresh()
    }

    fun refresh() {
        currentJob = viewModelScope.launch(errorHandler) {
            uiMutableState.value =
                CategoriesScreenState.Success(categories = cetCategoriesUseCase())
        }
    }

    override fun onCleared() {
        currentJob?.cancel()
        super.onCleared()
    }
}