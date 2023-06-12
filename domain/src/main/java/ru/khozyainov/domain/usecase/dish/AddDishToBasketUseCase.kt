package ru.khozyainov.domain.usecase.dish

import ru.khozyainov.domain.model.DishInBasket
import ru.khozyainov.domain.repository.BasketRepository
import javax.inject.Inject

class AddDishToBasketUseCase @Inject constructor(
    private val basketRepository: BasketRepository
) {
    suspend operator fun invoke(dish: DishInBasket) = basketRepository.addDishToBasket(dish = dish)
}