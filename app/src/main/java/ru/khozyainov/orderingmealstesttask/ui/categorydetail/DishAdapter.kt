package ru.khozyainov.orderingmealstesttask.ui.categorydetail

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import ru.khozyainov.orderingmealstesttask.model.DishUi

class DishAdapter(
    onItemClicked: (dish: DishUi) -> Unit
) : AsyncListDifferDelegationAdapter<DishUi>(DishDiffUtilCallback()) {

    init {
        delegatesManager.addDelegate(DishAdapterDelegate(onItemClicked = onItemClicked))
    }

    class DishDiffUtilCallback : DiffUtil.ItemCallback<DishUi>() {
        override fun areItemsTheSame(oldItem: DishUi, newItem: DishUi): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: DishUi, newItem: DishUi): Boolean {
            return oldItem == newItem
        }

    }
}