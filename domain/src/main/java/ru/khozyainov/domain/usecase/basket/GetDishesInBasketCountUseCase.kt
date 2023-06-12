package ru.khozyainov.domain.usecase.basket

import kotlinx.coroutines.flow.Flow
import ru.khozyainov.domain.model.DishInBasket
import ru.khozyainov.domain.repository.BasketRepository
import javax.inject.Inject

class GetDishesInBasketCountUseCase @Inject constructor(
    private val basketRepository: BasketRepository
){
    operator fun invoke(): Flow<List<DishInBasket>> = basketRepository.getDishesInBasket()
}