package ru.khozyainov.orderingmealstesttask.mapper

import ru.khozyainov.domain.model.Dish
import ru.khozyainov.domain.model.DishInBasket
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
            tags = ui.tags
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
            tags = model.tags
        )
    }

}

fun DishUiMapper.mapDishUiToDishInBasket(dish: DishUi): DishInBasket =
    DishInBasket(
        id = dish.id,
        title = dish.title,
        price = dish.price,
        weight = dish.weight,
        imageUrl = dish.imageUrl,
        count = 1
    )