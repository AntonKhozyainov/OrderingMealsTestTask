package ru.khozyainov.data.local.mapper

import ru.khozyainov.data.local.entity.DishEntity
import ru.khozyainov.domain.model.Dish

class DishEntityMapper : EntityMapper<Dish, DishEntity> {

    override fun mapToDomain(entity: DishEntity): Dish {
        return Dish(
            id = entity.id,
            title = entity.title,
            price = entity.price,
            weight = entity.weight,
            imageUrl = entity.imageUrl,
            count = entity.count,
            tags = listOf(),
            description = ""
        )
    }

    override fun mapToEntity(model: Dish): DishEntity {
        return DishEntity(
            id = model.id,
            title = model.title,
            price = model.price,
            weight = model.weight,
            imageUrl = model.imageUrl,
            count = model.count
        )
    }

}