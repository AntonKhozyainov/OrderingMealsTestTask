package ru.khozyainov.domain.usecase.dish

import ru.khozyainov.domain.model.Dish
import ru.khozyainov.domain.model.Tag

class GetSelectionListFromDishesUseCase {
    operator fun invoke(dishes: List<Dish>): List<Tag> {
        val list = dishes.asSequence().map { dish ->
            dish.tags.map { tagName ->
                Tag(
                    name = tagName, isSelected = false
                )
            }
        }.flatten().toSet().sortedBy { tag ->
            tag.isSelected
            tag.name
        }.toMutableList()

        if (list.isNotEmpty()) {
            list[0] = list.first().copy(isSelected = true)
        }

        return list
    }
}