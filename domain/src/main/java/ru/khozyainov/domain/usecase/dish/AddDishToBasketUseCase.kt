package ru.khozyainov.domain.usecase.dish

import ru.khozyainov.domain.model.Dish
import ru.khozyainov.domain.repository.BasketRepository
import ru.khozyainov.domain.repository.DishRepository
import javax.inject.Inject

class AddDishToBasketUseCase @Inject constructor(
    private val basketRepository: BasketRepository, private val dishRepository: DishRepository
) {
    suspend operator fun invoke(dish: Dish) {
        val dishInBasket = basketRepository.getDishById(id = dish.id)

        val dishToAdd = dishInBasket?.copy(count = dishInBasket.count + 1) ?: dish.copy(count = 1)

        dishRepository.addDishToBasket(dish = dishToAdd)

    }
}