package ru.khozyainov.orderingmealstesttask.ui.categorydetail

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL
import ru.khozyainov.domain.model.Tag
import ru.khozyainov.orderingmealstesttask.R
import ru.khozyainov.orderingmealstesttask.databinding.FragmentCategoryDetailBinding
import ru.khozyainov.orderingmealstesttask.model.DishUi
import ru.khozyainov.orderingmealstesttask.utils.ItemOffsetDecoration
import ru.khozyainov.orderingmealstesttask.utils.ViewBindingFragment
import ru.khozyainov.orderingmealstesttask.utils.appComponent
import ru.khozyainov.orderingmealstesttask.utils.autoCleared
import ru.khozyainov.orderingmealstesttask.utils.changeForCategoryDetailScreen
import ru.khozyainov.orderingmealstesttask.utils.launchAndCollectLatest
import ru.khozyainov.orderingmealstesttask.utils.setDefaultState
import javax.inject.Inject

class CategoryDetailFragment :
    ViewBindingFragment<FragmentCategoryDetailBinding>(FragmentCategoryDetailBinding::inflate) {

    @Inject
    lateinit var viewModel: CategoryDetailViewModel

    private var dishAdapter: DishAdapter by autoCleared()

    private var selectionAdapter: TagAdapter by autoCleared()

    private val args: CategoryDetailFragmentArgs by navArgs()

    private var toolbar: Toolbar? = null

    override fun onAttach(context: Context) {
        requireContext().appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        updateToolBar()
        initList()
        observeState()

        binding.categoryDetailsRetryButton.setOnClickListener {
            viewModel.refresh()
        }
    }

    private fun updateToolBar() {

        toolbar = activity?.findViewById(R.id.appToolBar)

        toolbar?.changeForCategoryDetailScreen(requireContext(), args.categoryName)

        toolbar?.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

    }

    private fun initList() {
        selectionAdapter = TagAdapter { tag ->
            viewModel.setSelection(tag = tag)
        }

        with(binding.selectionRecyclerView) {
            adapter = selectionAdapter
            layoutManager = LinearLayoutManager(requireContext(), HORIZONTAL, false)
            addItemDecoration(ItemOffsetDecoration(context = requireContext(), dp = 4))
            (itemAnimator as? DefaultItemAnimator)?.supportsChangeAnimations = false
        }

        dishAdapter = DishAdapter { dish ->
            findNavController().navigate(
                CategoryDetailFragmentDirections.actionCategoryDetailFragmentToDishDetailDialog(dish = dish)
            )
        }

        with(binding.dishesRecyclerView) {
            adapter = dishAdapter
            layoutManager = GridLayoutManager(requireContext(), 3)
            addItemDecoration(ItemOffsetDecoration(context = requireContext(), dp = 4))
            (itemAnimator as? DefaultItemAnimator)?.supportsChangeAnimations = false
        }
    }

    private fun observeState() {
        viewModel.uiState.launchAndCollectLatest(viewLifecycleOwner) { state ->
            when (state) {
                is CategoryDetailScreenState.Error -> showError(state.throwable)
                is CategoryDetailScreenState.Loading -> showProgress()
                is CategoryDetailScreenState.Success -> updateDishes(
                    dishes = state.dishes,
                    selection = state.selection
                )
            }
        }
    }

    private fun updateDishes(dishes: List<DishUi>, selection: List<Tag>) {
        dishAdapter.items = dishes
        selectionAdapter.items = selection
        with(binding) {
            dishesRecyclerView.isVisible = true
            categoryDetailProgressIndicator.isVisible = false
            categoryDetailsRetryButton.isVisible = false
            categoryDetailsExceptionTextView.isVisible = false

            if (dishAdapter.items.isEmpty()) {
                dishesRecyclerView.isVisible = false
                categoryDetailsExceptionTextView.isVisible = true
                categoryDetailsExceptionTextView.text = getString(R.string.empty_list)
            }
        }
    }

    private fun showError(throwable: Throwable) {
        with(binding) {
            dishesRecyclerView.isVisible = false
            categoryDetailProgressIndicator.isVisible = false
            categoryDetailsRetryButton.isVisible = true
            categoryDetailsExceptionTextView.isVisible = true
            categoryDetailsExceptionTextView.text = throwable.message
        }
    }

    private fun showProgress() {
        with(binding) {
            dishesRecyclerView.isVisible = false
            categoryDetailProgressIndicator.isVisible = true
            categoryDetailsRetryButton.isVisible = false
            categoryDetailsExceptionTextView.isVisible = false
        }
    }

    override fun onPause() {
        toolbar?.setDefaultState(requireContext())
        super.onPause()
    }
}