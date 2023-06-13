package ru.khozyainov.data.remote.mapper

import ru.khozyainov.data.remote.model.DishRemote
import ru.khozyainov.domain.model.Dish

class DishRemoteMapper : RemoteMapper<Dish, DishRemote> {
    override fun mapToDomain(remote: DishRemote): Dish {
        return Dish(
            id = remote.id,
            title = remote.title ?: "",
            price = remote.price ?: 0,
            weight = remote.weight ?: 0,
            imageUrl = remote.imageUrl ?: "",
            description = remote.description ?: "",
            tags = remote.tags ?: emptyList(),
            count = 1
        )
    }

    override fun mapToRemote(model: Dish): DishRemote {
        return DishRemote(
            id = model.id,
            title = model.title,
            imageUrl = model.imageUrl,
            price = model.price,
            weight = model.weight,
            description = model.description,
            tags = model.tags
        )
    }

}