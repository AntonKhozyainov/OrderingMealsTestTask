package ru.khozyainov.data.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import ru.khozyainov.data.remote.model.RemoteContract.CATEGORIES

@JsonClass(generateAdapter = true)
data class CategoriesRemote(
    @Json(name = CATEGORIES) val categories: List<CategoryRemote>
)
