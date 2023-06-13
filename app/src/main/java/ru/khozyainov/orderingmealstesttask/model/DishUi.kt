package ru.khozyainov.orderingmealstesttask.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DishUi(
    val id: Int,
    val title: String,
    val price: Int,
    val weight: Int,
    val imageUrl: String,
    val description: String,
    val tags: List<String>,
    val count: Int
) : Parcelable, ModelUI()
