package ru.khozyainov.orderingmealstesttask.ui.categories

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import ru.khozyainov.domain.model.Category

class CategoryAdapter(
    onItemClicked: (category: Category) -> Unit
): AsyncListDifferDelegationAdapter<Category>(CategoryDiffUtilCallback()) {

    init {
        delegatesManager.addDelegate(CategoryAdapterDelegate(onItemClicked = onItemClicked))
    }

    class CategoryDiffUtilCallback: DiffUtil.ItemCallback<Category>(){
        override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem == newItem
        }

    }
}