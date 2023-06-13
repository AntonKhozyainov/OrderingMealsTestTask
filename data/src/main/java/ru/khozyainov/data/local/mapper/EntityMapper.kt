package ru.khozyainov.data.local.mapper

import ru.khozyainov.data.local.entity.ModelEntity
import ru.khozyainov.domain.model.Model

interface EntityMapper<M : Model, ME : ModelEntity> {
    fun mapToDomain(entity: ME): M
    fun mapToEntity(model: M): ME
}