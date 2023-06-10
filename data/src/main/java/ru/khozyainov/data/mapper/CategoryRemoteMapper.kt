package ru.khozyainov.data.mapper

import ru.khozyainov.data.model.CategoryRemote
import ru.khozyainov.domain.model.Category

class CategoryRemoteMapper: RemoteMapper<Category, CategoryRemote> {
    override fun mapToDomain(remote: CategoryRemote): Category {
        return Category(
            id = remote.id,
            title = remote.title ?: "",
            imageUrl = remote.imageUrl ?: ""
        )
    }

    override fun mapToRemote(model: Category): CategoryRemote {
        return CategoryRemote(
            id = model.id,
            title = model.title,
            imageUrl = model.imageUrl
        )
    }
}