package ru.khozyainov.data.local.mapper

import ru.khozyainov.data.local.entity.DishEntity
import ru.khozyainov.domain.model.DishInBasket

class DishEntityMapper: EntityMapper<DishInBasket, DishEntity> {

    override fun mapToDomain(entity: DishEntity): DishInBasket {
        return DishInBasket(
            id = entity.id,
            title = entity.title,
            price = entity.price,
            weight = entity.weight,
            imageUrl = entity.imageUrl,
            count = entity.count
        )
    }

    override fun mapToEntity(model: DishInBasket): DishEntity {
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