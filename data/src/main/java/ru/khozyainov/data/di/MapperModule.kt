package ru.khozyainov.data.di

import dagger.Module
import dagger.Provides
import ru.khozyainov.data.mapper.CategoryRemoteMapper
import javax.inject.Singleton

@Module
object MapperModule {

    @Provides
    @Singleton
    fun providesCategoryRemoteMapper(): CategoryRemoteMapper = CategoryRemoteMapper()
}