package ru.khozyainov.orderingmealstesttask.mapper

import ru.khozyainov.domain.model.Dish
import ru.khozyainov.orderingmealstesttask.model.DishUi

class DishUiMapper : UiMapper<Dish, DishUi> {

    override fun mapToDomain(ui: DishUi): Dish {
        return Dish(
            id = ui.id,
            title = ui.title,
            price = ui.price,
            weight = ui.weight,
            imageUrl = ui.imageUrl,
            description = ui.description,
            tags = ui.tags,
            count = ui.count
        )
    }

    override fun mapToUI(model: Dish): DishUi {
        return DishUi(
            id = model.id,
            title = model.title,
            price = model.price,
            weight = model.weight,
            imageUrl = model.imageUrl,
            description = model.description,
            tags = model.tags,
            count = model.count
        )
    }

}
