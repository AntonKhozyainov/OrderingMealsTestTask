package ru.khozyainov.orderingmealstesttask.ui.basket

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import ru.khozyainov.domain.model.DishInBasket
import ru.khozyainov.orderingmealstesttask.R
import ru.khozyainov.orderingmealstesttask.databinding.FragmentBasketBinding
import ru.khozyainov.orderingmealstesttask.utils.ItemOffsetDecoration
import ru.khozyainov.orderingmealstesttask.utils.ViewBindingFragment
import ru.khozyainov.orderingmealstesttask.utils.appComponent
import ru.khozyainov.orderingmealstesttask.utils.autoCleared
import ru.khozyainov.orderingmealstesttask.utils.launchAndCollectLatest
import javax.inject.Inject

class BasketFragment : ViewBindingFragment<FragmentBasketBinding>(FragmentBasketBinding::inflate) {

    @Inject
    lateinit var viewModel: BasketViewModel

    private var dishBasketAdapter: DishBasketAdapter by autoCleared()

    override fun onAttach(context: Context) {
        requireContext().appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initList()
        observeState()
    }

    private fun initList() {
        dishBasketAdapter = DishBasketAdapter(
            onClickedInc = { dishInBasket ->
                //todo
            },
            onClickedDec = { dishInBasket ->
                //todo
            },
        )

        with(binding.basketRecyclerView) {
            adapter = dishBasketAdapter
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(ItemOffsetDecoration(context = requireContext(), dp = 4))
            (itemAnimator as? DefaultItemAnimator)?.supportsChangeAnimations = false
        }
    }

    private fun observeState(){
        viewModel.uiState.launchAndCollectLatest(viewLifecycleOwner){ state ->
            when(state){
                is BasketScreenState.Error -> showError(state.throwable)
                is BasketScreenState.Loading -> showProgress()
                is BasketScreenState.Success -> updateDishes(state.dishes)
            }
        }
    }

    private fun updateDishes(dishes: List<DishInBasket>){
        dishBasketAdapter.items = dishes
        with(binding) {
            basketRecyclerView.isVisible = true
            addToBasketButton.isVisible = true
            basketProgressIndicator.isVisible = false
            basketRetryButton.isVisible = false
            basketTextView.isVisible = false
            if (dishBasketAdapter.items.isEmpty()) {
                basketRecyclerView.isVisible = false
                addToBasketButton.isVisible = false
                basketTextView.isVisible = true
                basketTextView.text = getString(R.string.empty_list)
            }
        }
    }

    private fun showError(throwable: Throwable){
        with(binding) {
            basketRecyclerView.isVisible = false
            addToBasketButton.isVisible = false
            basketProgressIndicator.isVisible = false
            basketRetryButton.isVisible = true
            basketTextView.isVisible = true
            basketTextView.text = throwable.message
        }
    }

    private fun showProgress(){
        with(binding) {
            basketProgressIndicator.isVisible = true
            basketRecyclerView.isVisible = false
            addToBasketButton.isVisible = false
            basketRetryButton.isVisible = false
            basketTextView.isVisible = false
        }
    }
}