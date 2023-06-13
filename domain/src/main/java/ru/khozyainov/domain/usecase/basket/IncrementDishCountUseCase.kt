package ru.khozyainov.domain.usecase.basket

import ru.khozyainov.domain.model.Dish
import ru.khozyainov.domain.repository.DishRepository
import javax.inject.Inject

class IncrementDishCountUseCase @Inject constructor(
    private val dishRepository: DishRepository
) {
    suspend operator fun invoke(dish: Dish) =
        dishRepository.addDishToBasket(dish = dish.copy(count = dish.count + 1))
}