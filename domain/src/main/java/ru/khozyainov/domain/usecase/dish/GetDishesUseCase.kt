package ru.khozyainov.domain.usecase.dish

import ru.khozyainov.domain.model.Dish
import ru.khozyainov.domain.model.Tag
import ru.khozyainov.domain.repository.DishRepository
import javax.inject.Inject

class GetDishesUseCase @Inject constructor(
   private val dishRepository: DishRepository
){
    suspend operator fun invoke(tag: Tag? = null): List<Dish> = dishRepository.getDishes(tag = tag)
}