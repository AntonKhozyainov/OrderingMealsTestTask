package ru.khozyainov.orderingmealstesttask.ui.basket

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import ru.khozyainov.domain.usecase.basket.GetDishesInBasketCountUseCase
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
class BasketViewModel @Inject constructor(
    getDishesInBasketCountUseCase: GetDishesInBasketCountUseCase,
): ViewModel() {

//    private val uiMutableState = MutableStateFlow<BasketScreenState>(BasketScreenState.Loading)
//    val uiState: StateFlow<BasketScreenState> = uiMutableState

    val uiState = getDishesInBasketCountUseCase()
        .mapLatest {
            BasketScreenState.Success(dishes = it)
        }
        .flowOn(Dispatchers.IO)
        .catch { throwable ->
            BasketScreenState.Error(throwable = throwable)
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000),
            initialValue = BasketScreenState.Loading
        )
}