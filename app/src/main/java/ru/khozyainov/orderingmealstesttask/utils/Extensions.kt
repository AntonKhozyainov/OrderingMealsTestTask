package ru.khozyainov.orderingmealstesttask.utils

import android.content.Context
import androidx.appcompat.content.res.AppCompatResources
import ru.khozyainov.orderingmealstesttask.App
import ru.khozyainov.orderingmealstesttask.di.AppComponent
import androidx.appcompat.widget.Toolbar
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.google.android.material.appbar.MaterialToolbar
import org.threeten.bp.Instant
import org.threeten.bp.ZoneId
import org.threeten.bp.format.DateTimeFormatter
import ru.khozyainov.orderingmealstesttask.R

private val formatter = DateTimeFormatter.ofPattern("d MMMM, yyyy").withZone(ZoneId.systemDefault())

val Context.appComponent: AppComponent
    get() = when (this) {
        is App -> appComponent
        else -> applicationContext.appComponent
    }

fun Toolbar.changeForCategoryDetailScreen(context: Context, categoryName: String) {
    with(this) {
        title = categoryName
        subtitle = null
        navigationIcon = AppCompatResources.getDrawable(context, R.drawable.toolbar_back)
        logo = null

    }
    val toolbarMaterial: MaterialToolbar = this as MaterialToolbar
    toolbarMaterial.isTitleCentered = true
}

fun Toolbar.setDefaultState(context: Context) {
    with(this) {
        title = "Санкт-Петербург" //todo
        subtitle = formatter.format(Instant.now())
        navigationIcon = null
        logo = AppCompatResources.getDrawable(context, R.drawable.toolbar_location)

    }
    val toolbarMaterial: MaterialToolbar = this as MaterialToolbar
    toolbarMaterial.isTitleCentered = false
}

fun Context.getCircularProgressDrawable(): CircularProgressDrawable =
    CircularProgressDrawable(this).apply {
        strokeWidth = 5f
        centerRadius = 30f
        start()
    }
