package ru.khozyainov.data.di

import dagger.Binds
import dagger.Module
import ru.khozyainov.data.datasource.CategoryRemoteDataSource
import ru.khozyainov.data.datasource.CategoryRemoteDataSourceImpl

@Module
abstract class CategoryRemoteDataSourceModule {
    @Binds
    abstract fun provides(impl: CategoryRemoteDataSourceImpl): CategoryRemoteDataSource
}