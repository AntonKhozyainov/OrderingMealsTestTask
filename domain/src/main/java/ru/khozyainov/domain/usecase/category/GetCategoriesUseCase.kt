package ru.khozyainov.domain.usecase.category

import ru.khozyainov.domain.model.Category
import ru.khozyainov.domain.repository.CategoryRepository
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val categoryRepository: CategoryRepository
) {
    suspend operator fun invoke(): List<Category> = categoryRepository.getCategories()
}