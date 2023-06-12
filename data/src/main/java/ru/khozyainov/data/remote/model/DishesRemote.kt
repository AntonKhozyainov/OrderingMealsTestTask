package ru.khozyainov.data.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import ru.khozyainov.data.remote.model.RemoteContract.DISHES

@JsonClass(generateAdapter = true)
data class DishesRemote(
    @Json(name = DISHES) val dishes: List<DishRemote>
)
