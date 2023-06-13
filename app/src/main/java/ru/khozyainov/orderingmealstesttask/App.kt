package ru.khozyainov.orderingmealstesttask

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import ru.khozyainov.orderingmealstesttask.di.AppComponent
import ru.khozyainov.orderingmealstesttask.di.DaggerAppComponent

class App : Application() {

    private var _appComponent: AppComponent? = null

    internal val appComponent: AppComponent
        get() = checkNotNull(_appComponent)

    override fun onCreate() {
        super.onCreate()

        AndroidThreeTen.init(this)

        _appComponent = DaggerAppComponent.builder().context(this).build()
    }
}