package ru.khozyainov.orderingmealstesttask.ui.categorydetail

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import ru.khozyainov.domain.model.Tag

class TagAdapter(
    onItemClicked: (tag: Tag) -> Unit
) : AsyncListDifferDelegationAdapter<Tag>(TagDiffUtilCallback()) {

    init {
        delegatesManager.addDelegate(TagAdapterDelegate(onItemClicked = onItemClicked))
    }

    class TagDiffUtilCallback : DiffUtil.ItemCallback<Tag>() {
        override fun areItemsTheSame(oldItem: Tag, newItem: Tag): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Tag, newItem: Tag): Boolean {
            return oldItem == newItem
        }

    }
}