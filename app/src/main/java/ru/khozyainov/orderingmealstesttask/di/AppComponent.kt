package ru.khozyainov.orderingmealstesttask.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ru.khozyainov.data.di.CategoryRemoteDataSourceModule
import ru.khozyainov.data.di.ApiModule
import ru.khozyainov.data.di.MapperModule
import ru.khozyainov.data.mapper.CategoryRemoteMapper
import ru.khozyainov.orderingmealstesttask.ui.categories.CategoriesFragment
import javax.inject.Singleton

@Component(modules = [
    ApiModule::class,
    NetworkModule::class,
    CategoryRemoteDataSourceModule::class,
    CategoryRepositoryModule::class,
    MapperModule::class
])
@Singleton
interface AppComponent {

    fun inject(categoriesFragment: CategoriesFragment)

    @Component.Builder
    interface Builder{
        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }
}