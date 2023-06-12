package ru.khozyainov.data.di

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import ru.khozyainov.data.remote.api.CategoryApi
import ru.khozyainov.data.remote.api.DishApi
import javax.inject.Singleton

@Module
object ApiModule {

    @Provides
    @Singleton
    fun providesNetworkCategoryApi(retrofit: Retrofit): CategoryApi =
        retrofit.create(CategoryApi::class.java)

    @Provides
    @Singleton
    fun providesNetworkDishApi(retrofit: Retrofit): DishApi =
        retrofit.create(DishApi::class.java)
}