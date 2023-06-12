package ru.khozyainov.orderingmealstesttask.mapper

import ru.khozyainov.domain.model.Model
import ru.khozyainov.orderingmealstesttask.model.ModelUI

interface UiMapper<M : Model, UIM : ModelUI> {
    fun mapToDomain(ui: UIM): M
    fun mapToUI(model: M): UIM
}