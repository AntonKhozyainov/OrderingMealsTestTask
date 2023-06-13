package ru.khozyainov.data.remote.api

import retrofit2.http.GET
import ru.khozyainov.data.remote.model.CategoriesRemote

interface CategoryApi {
    @GET("058729bd-1402-4578-88de-265481fd7d54")
    suspend fun getCategories(): CategoriesRemote
}