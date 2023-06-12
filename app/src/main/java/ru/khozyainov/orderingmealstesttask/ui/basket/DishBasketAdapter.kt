package ru.khozyainov.orderingmealstesttask.ui.basket

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import ru.khozyainov.domain.model.Category
import ru.khozyainov.domain.model.DishInBasket

class DishBasketAdapter(
    onClickedInc: (dish: DishInBasket) -> Unit,
    onClickedDec: (dish: DishInBasket) -> Unit
): AsyncListDifferDelegationAdapter<DishInBasket>(DishInBasketDiffUtilCallback()) {

    init {
        delegatesManager.addDelegate(DishBasketAdapterDelegate(onClickedInc = onClickedInc, onClickedDec = onClickedDec))
    }

    class DishInBasketDiffUtilCallback: DiffUtil.ItemCallback<DishInBasket>(){
        override fun areItemsTheSame(oldItem: DishInBasket, newItem: DishInBasket): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: DishInBasket, newItem: DishInBasket): Boolean {
            return oldItem == newItem
        }

    }
}