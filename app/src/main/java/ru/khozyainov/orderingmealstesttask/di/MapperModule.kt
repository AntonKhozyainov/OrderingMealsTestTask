package ru.khozyainov.orderingmealstesttask.di

import dagger.Module
import dagger.Provides
import ru.khozyainov.data.local.mapper.DishEntityMapper
import ru.khozyainov.data.remote.mapper.CategoryRemoteMapper
import ru.khozyainov.data.remote.mapper.DishRemoteMapper
import ru.khozyainov.orderingmealstesttask.mapper.DishUiMapper
import javax.inject.Singleton

@Module
object MapperModule {

    @Provides
    @Singleton
    fun providesCategoryRemoteMapper(): CategoryRemoteMapper = CategoryRemoteMapper()

    @Provides
    @Singleton
    fun providesDishRemoteMapper(): DishRemoteMapper = DishRemoteMapper()

    @Provides
    @Singleton
    fun providesDishUiMapper(): DishUiMapper = DishUiMapper()

    @Provides
    @Singleton
    fun providesDishEntityMapper(): DishEntityMapper = DishEntityMapper()
}