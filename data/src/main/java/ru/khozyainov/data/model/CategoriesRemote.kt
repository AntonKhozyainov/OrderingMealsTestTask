package ru.khozyainov.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import ru.khozyainov.data.model.RemoteContract.CATEGORIES

@JsonClass(generateAdapter = true)
data class CategoriesRemote(
    @Json(name = CATEGORIES) val categories: List<CategoryRemote>
)
