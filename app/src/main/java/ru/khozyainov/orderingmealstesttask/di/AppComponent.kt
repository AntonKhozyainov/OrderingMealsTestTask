package ru.khozyainov.orderingmealstesttask.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ru.khozyainov.data.di.CategoryRemoteDataSourceModule
import ru.khozyainov.data.di.ApiModule
import ru.khozyainov.data.di.DatabaseModule
import ru.khozyainov.data.di.DishLocalDataSourceModule
import ru.khozyainov.data.di.DishRemoteDataSourceModule
import ru.khozyainov.orderingmealstesttask.ui.basket.BasketFragment
import ru.khozyainov.orderingmealstesttask.ui.categories.CategoriesFragment
import ru.khozyainov.orderingmealstesttask.ui.categorydetail.CategoryDetailFragment
import ru.khozyainov.orderingmealstesttask.ui.dishdetail.DishDetailDialog
import javax.inject.Singleton

@Component(
    modules = [
        ApiModule::class,
        NetworkModule::class,
        CategoryRemoteDataSourceModule::class,
        CategoryRepositoryModule::class,
        BasketRepositoryModule::class,
        DishRemoteDataSourceModule::class,
        DishLocalDataSourceModule::class,
        DishRepositoryModule::class,
        MapperModule::class,
        UseCaseModule::class,
        DatabaseModule::class]
)
@Singleton
interface AppComponent {

    fun inject(categoriesFragment: CategoriesFragment)
    fun inject(categoryDetailFragment: CategoryDetailFragment)
    fun inject(dishDetailDialog: DishDetailDialog)
    fun inject(basketFragment: BasketFragment)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }
}