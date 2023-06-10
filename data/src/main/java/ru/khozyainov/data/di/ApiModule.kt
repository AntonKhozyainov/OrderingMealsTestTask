package ru.khozyainov.data.di

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import ru.khozyainov.data.api.CategoryApi
import javax.inject.Singleton

@Module
object ApiModule {

    @Provides
    @Singleton
    fun providesNetworkCategoryApi(retrofit: Retrofit): CategoryApi =
        retrofit.create(CategoryApi::class.java)

}