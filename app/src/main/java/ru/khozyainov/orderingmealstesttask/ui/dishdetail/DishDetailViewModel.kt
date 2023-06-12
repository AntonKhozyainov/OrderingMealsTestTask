package ru.khozyainov.orderingmealstesttask.ui.dishdetail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import ru.khozyainov.domain.usecase.dish.AddDishToBasketUseCase
import ru.khozyainov.orderingmealstesttask.mapper.DishUiMapper
import ru.khozyainov.orderingmealstesttask.mapper.mapDishUiToDishInBasket
import ru.khozyainov.orderingmealstesttask.model.DishUi
import javax.inject.Inject

class DishDetailViewModel @Inject constructor(
    private val addDishToBasketUseCase: AddDishToBasketUseCase,
    private val dishUiMapper: DishUiMapper
): ViewModel() {

    private val errorHandler = CoroutineExceptionHandler{ _, throwable ->
        Log.e("addDishToBasketError", throwable.message.toString())
    }

    fun addDishToBasket(dish: DishUi){
        viewModelScope.launch(errorHandler) {
            val dishInBasket = dishUiMapper.mapDishUiToDishInBasket(dish = dish)
            addDishToBasketUseCase(dishInBasket)
        }
    }
}