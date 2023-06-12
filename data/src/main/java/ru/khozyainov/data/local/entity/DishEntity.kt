package ru.khozyainov.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.khozyainov.data.local.entity.DishEntityContract.Columns.COUNT
import ru.khozyainov.data.local.entity.DishEntityContract.Columns.ID
import ru.khozyainov.data.local.entity.DishEntityContract.Columns.IMAGE
import ru.khozyainov.data.local.entity.DishEntityContract.Columns.PRICE
import ru.khozyainov.data.local.entity.DishEntityContract.Columns.TITLE
import ru.khozyainov.data.local.entity.DishEntityContract.Columns.WEIGHT
import ru.khozyainov.data.local.entity.DishEntityContract.TABLE_NAME

@Entity(
    tableName = TABLE_NAME
)
data class DishEntity(
    @PrimaryKey
    @ColumnInfo(name = ID) val id: Int,
    @ColumnInfo(name = TITLE) val title: String,
    @ColumnInfo(name = PRICE) val price: Int,
    @ColumnInfo(name = WEIGHT) val weight: Int,
    @ColumnInfo(name = IMAGE) val imageUrl: String,
    @ColumnInfo(name = COUNT) val count: Int
): ModelEntity()
