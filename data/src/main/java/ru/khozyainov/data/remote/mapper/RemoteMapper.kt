package ru.khozyainov.data.remote.mapper

import ru.khozyainov.data.remote.model.ModelRemote
import ru.khozyainov.domain.model.Model

interface RemoteMapper<M : Model, MR : ModelRemote> {
    fun mapToDomain(remote: MR): M
    fun mapToRemote(model: M): MR
}