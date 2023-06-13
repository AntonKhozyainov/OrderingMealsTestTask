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
import kotlinx.coroutines.launch
import ru.khozyainov.domain.usecase.basket.DecrementDishCountUseCase
import ru.khozyainov.domain.usecase.basket.GetDishesInBasketCountUseCase
import ru.khozyainov.domain.usecase.basket.IncrementDishCountUseCase
import ru.khozyainov.orderingmealstesttask.mapper.DishUiMapper
import ru.khozyainov.orderingmealstesttask.model.DishUi
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
class BasketViewModel @Inject constructor(
    private val decrementDishCountUseCase: DecrementDishCountUseCase,
    private val incrementDishCountUseCase: IncrementDishCountUseCase,
    private val dishUiMapper: DishUiMapper,
    getDishesInBasketCountUseCase: GetDishesInBasketCountUseCase,

    ) : ViewModel() {

    val uiState = getDishesInBasketCountUseCase().mapLatest { dishes ->
        BasketScreenState.Success(
            dishes = dishes.map { dishUiMapper.mapToUI(model = it) },
            basketSum = dishes.sumOf { it.price * it.count }
        )
    }.flowOn(Dispatchers.IO).catch { throwable ->
        BasketScreenState.Error(throwable = throwable)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000),
        initialValue = BasketScreenState.Loading
    )

    fun incDishBasket(dish: DishUi) {
        viewModelScope.launch {
            incrementDishCountUseCase(dish = dishUiMapper.mapToDomain(ui = dish))
        }
    }

    fun decDishBasket(dish: DishUi) {
        viewModelScope.launch {
            decrementDishCountUseCase(dish = dishUiMapper.mapToDomain(ui = dish))
        }
    }
}