package ru.khozyainov.orderingmealstesttask.ui.categories


import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import ru.khozyainov.domain.model.Category
import ru.khozyainov.orderingmealstesttask.databinding.FragmentCategoriesBinding
import ru.khozyainov.orderingmealstesttask.utils.ItemOffsetDecoration
import ru.khozyainov.orderingmealstesttask.utils.ViewBindingFragment
import ru.khozyainov.orderingmealstesttask.utils.appComponent
import ru.khozyainov.orderingmealstesttask.utils.autoCleared
import ru.khozyainov.orderingmealstesttask.utils.launchAndCollectLatest
import javax.inject.Inject

class CategoriesFragment :
    ViewBindingFragment<FragmentCategoriesBinding>(FragmentCategoriesBinding::inflate) {

    @Inject
    lateinit var viewModel: CategoriesViewModel

    private var categoryAdapter: CategoryAdapter by autoCleared()

    override fun onAttach(context: Context) {
        requireContext().appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initList()
        observeState()

        binding.categoriesRetryButton.setOnClickListener {
            viewModel.refresh()
        }
    }

    private fun initList() {
        categoryAdapter = CategoryAdapter { category ->
            findNavController().navigate(
                CategoriesFragmentDirections.actionCategoriesFragmentToCategoryDetailFragment(
                    categoryName = category.title
                )
            )
        }

        with(binding.categoriesRecyclerView) {
            adapter = categoryAdapter
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(ItemOffsetDecoration(context = requireContext(), dp = 4))
            (itemAnimator as? DefaultItemAnimator)?.supportsChangeAnimations = false
        }
    }

    private fun observeState() {
        viewModel.uiState.launchAndCollectLatest(viewLifecycleOwner) { state ->
            when (state) {
                is CategoriesScreenState.Error -> showError(state.throwable)
                is CategoriesScreenState.Loading -> showProgress()
                is CategoriesScreenState.Success -> updateCategories(state.categories)
            }
        }
    }

    private fun updateCategories(categories: List<Category>) {
        categoryAdapter.items = categories
        with(binding) {
            categoriesRecyclerView.isVisible = true
            categoriesProgressIndicator.isVisible = false
            categoriesRetryButton.isVisible = false
            categoriesExceptionTextView.isVisible = false
        }
    }

    private fun showError(throwable: Throwable) {
        with(binding) {
            categoriesProgressIndicator.isVisible = false
            categoriesRecyclerView.isVisible = false
            categoriesRetryButton.isVisible = true
            categoriesExceptionTextView.isVisible = true
            categoriesExceptionTextView.text = throwable.message
        }
    }

    private fun showProgress() {
        with(binding) {
            categoriesProgressIndicator.isVisible = true
            categoriesRecyclerView.isVisible = false
            categoriesRetryButton.isVisible = false
            categoriesExceptionTextView.isVisible = false
        }
    }

}