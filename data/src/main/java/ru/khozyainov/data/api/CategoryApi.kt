package ru.khozyainov.data.api

import retrofit2.http.GET
import ru.khozyainov.data.model.CategoriesRemote
import ru.khozyainov.data.model.CategoryRemote

interface CategoryApi {

    @GET("058729bd-1402-4578-88de-265481fd7d54")
    suspend fun getCategories(): CategoriesRemote//List<CategoryRemote>
}