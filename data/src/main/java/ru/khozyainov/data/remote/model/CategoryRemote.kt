package ru.khozyainov.data.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import ru.khozyainov.data.remote.model.RemoteContract.Category.ID
import ru.khozyainov.data.remote.model.RemoteContract.Category.IMAGE_URL
import ru.khozyainov.data.remote.model.RemoteContract.Category.TITLE

@JsonClass(generateAdapter = true)
data class CategoryRemote(
    @Json(name = ID) val id: Int,
    @Json(name = TITLE) val title: String? = null,
    @Json(name = IMAGE_URL) val imageUrl: String? = null
) : ModelRemote()
