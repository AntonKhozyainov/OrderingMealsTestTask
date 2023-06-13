package ru.khozyainov.orderingmealstesttask.ui.basket

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import ru.khozyainov.orderingmealstesttask.model.DishUi

class DishBasketAdapter(
    onClickedInc: (dish: DishUi) -> Unit,
    onClickedDec: (dish: DishUi) -> Unit
) : AsyncListDifferDelegationAdapter<DishUi>(DishInBasketDiffUtilCallback()) {

    init {
        delegatesManager.addDelegate(
            DishBasketAdapterDelegate(
                onClickedInc = onClickedInc, onClickedDec = onClickedDec
            )
        )
    }

    class DishInBasketDiffUtilCallback : DiffUtil.ItemCallback<DishUi>() {
        override fun areItemsTheSame(oldItem: DishUi, newItem: DishUi): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: DishUi, newItem: DishUi): Boolean {
            return oldItem == newItem
        }

    }
}