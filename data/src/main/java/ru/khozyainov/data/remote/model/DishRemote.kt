package ru.khozyainov.data.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import ru.khozyainov.data.remote.model.RemoteContract.Dish.DESCRIPTION
import ru.khozyainov.data.remote.model.RemoteContract.Dish.ID
import ru.khozyainov.data.remote.model.RemoteContract.Dish.IMAGE_URL
import ru.khozyainov.data.remote.model.RemoteContract.Dish.PRICE
import ru.khozyainov.data.remote.model.RemoteContract.Dish.TAG
import ru.khozyainov.data.remote.model.RemoteContract.Dish.TITLE
import ru.khozyainov.data.remote.model.RemoteContract.Dish.WEIGHT

@JsonClass(generateAdapter = true)
data class DishRemote(
    @Json(name = ID) val id: Int,
    @Json(name = TITLE) val title: String? = null,
    @Json(name = IMAGE_URL) val imageUrl: String? = null,
    @Json(name = PRICE) val price: Int? = null,
    @Json(name = WEIGHT) val weight: Int? = null,
    @Json(name = DESCRIPTION) val description: String? = null,
    @Json(name = TAG) val tags: List<String>? = null
) : ModelRemote()
