package ru.khozyainov.data.remote.model

object RemoteContract {

    const val CATEGORIES = "сategories"

    const val DISHES = "dishes"

    object Category {
        const val ID = "id"
        const val TITLE = "name"
        const val IMAGE_URL = "image_url"
    }

    object Dish {
        const val ID = "id"
        const val TITLE = "name"
        const val IMAGE_URL = "image_url"
        const val PRICE = "price"
        const val WEIGHT = "weight"
        const val DESCRIPTION = "description"
        const val TAG = "tegs"
    }

}