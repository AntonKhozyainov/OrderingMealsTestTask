package ru.khozyainov.data.local.entity

object DishEntityContract {

    const val TABLE_NAME = "basket"

    object Columns{
        const val ID = "id"
        const val TITLE = "title"
        const val PRICE = "price"
        const val WEIGHT = "weight"
        const val IMAGE = "image_url"
        const val COUNT = "count"

    }
}