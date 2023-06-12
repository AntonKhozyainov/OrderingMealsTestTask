package ru.khozyainov.orderingmealstesttask.ui.categorydetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import ru.khozyainov.domain.model.Tag
import ru.khozyainov.orderingmealstesttask.R
import ru.khozyainov.orderingmealstesttask.databinding.ItemSelectionBinding

class TagAdapterDelegate(
    private val onItemClicked: (tag: Tag) -> Unit
) : AbsListItemAdapterDelegate<Tag, Tag, TagAdapterDelegate.TagHolder>() {

    override fun isForViewType(
        item: Tag,
        items: MutableList<Tag>,
        position: Int
    ): Boolean = true

    override fun onCreateViewHolder(parent: ViewGroup): TagHolder =
        TagHolder(
            binding = ItemSelectionBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onItemClicked = onItemClicked
        )

    override fun onBindViewHolder(
        item: Tag,
        holder: TagHolder,
        payloads: MutableList<Any>
    ) {
        holder.bind(tag = item)
    }

    class TagHolder(
        private val binding: ItemSelectionBinding,
        onItemClicked: (tag: Tag) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        private lateinit var currentTag: Tag

        init {
            binding.root.setOnClickListener {
                onItemClicked(currentTag)
            }
        }

        fun bind(tag: Tag) {

            currentTag = tag

            with(binding) {
                tagNameTextView.text = tag.name
                if (tag.isSelected) {
                    val colorWhite = itemView.context.getColor(R.color.white)
                    val colorBlue = itemView.context.getColor(R.color.blue)
                    tagNameTextView.setTextColor(colorWhite)
                    tagMaterialCard.setCardBackgroundColor(colorBlue)
                } else {
                    val colorBlack = itemView.context.getColor(R.color.black)
                    val colorSmokyWhite = itemView.context.getColor(R.color.smoky_white)
                    tagNameTextView.setTextColor(colorBlack)
                    tagMaterialCard.setCardBackgroundColor(colorSmokyWhite)
                }
            }

        }
    }


}