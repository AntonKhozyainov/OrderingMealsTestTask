package ru.khozyainov.orderingmealstesttask.utils

import android.content.Context
import ru.khozyainov.orderingmealstesttask.App
import ru.khozyainov.orderingmealstesttask.di.AppComponent

val Context.appComponent: AppComponent
    get() = when (this) {
        is App -> appComponent
        else -> applicationContext.appComponent
    }