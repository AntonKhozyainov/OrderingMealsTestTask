package ru.khozyainov.domain.usecase.basket

import ru.khozyainov.domain.model.Dish
import ru.khozyainov.domain.repository.BasketRepository
import ru.khozyainov.domain.repository.DishRepository
import javax.inject.Inject

class DecrementDishCountUseCase @Inject constructor(
    private val basketRepository: BasketRepository, private val dishRepository: DishRepository
) {
    suspend operator fun invoke(dish: Dish) {
        if (dish.count <= 1) basketRepository.deleteDishById(id = dish.id)
        else dishRepository.addDishToBasket(dish = dish.copy(count = dish.count - 1))
    }
}