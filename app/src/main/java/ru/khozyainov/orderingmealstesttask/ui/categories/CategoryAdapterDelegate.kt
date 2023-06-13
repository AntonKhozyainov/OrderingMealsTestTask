package ru.khozyainov.orderingmealstesttask.ui.categories

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.graphics.drawable.toDrawable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import ru.khozyainov.domain.model.Category
import ru.khozyainov.orderingmealstesttask.R
import ru.khozyainov.orderingmealstesttask.databinding.ItemCategoryBinding
import ru.khozyainov.orderingmealstesttask.utils.getCircularProgressDrawable

class CategoryAdapterDelegate(
    private val onItemClicked: (category: Category) -> Unit
) : AbsListItemAdapterDelegate<Category, Category, CategoryAdapterDelegate.CategoryHolder>() {

    override fun isForViewType(
        item: Category, items: MutableList<Category>, position: Int
    ): Boolean = true

    override fun onCreateViewHolder(parent: ViewGroup): CategoryHolder =
        CategoryHolder(
            binding = ItemCategoryBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ),
            onItemClicked = onItemClicked
        )

    override fun onBindViewHolder(
        item: Category, holder: CategoryHolder, payloads: MutableList<Any>
    ) {
        holder.bind(category = item)
    }

    class CategoryHolder(
        private val binding: ItemCategoryBinding, onItemClicked: (category: Category) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        private lateinit var currentCategory: Category

        init {
            binding.root.setOnClickListener {
                onItemClicked(currentCategory)
            }
        }

        fun bind(category: Category) {

            currentCategory = category

            with(binding) {
                categoryTitleTextView.text = category.title

                Glide.with(itemView).asBitmap().load(category.imageUrl)
                    .into(object : CustomTarget<Bitmap>() {

                        override fun onLoadStarted(placeholder: Drawable?) {
                            super.onLoadStarted(placeholder)
                            val circularProgressDrawable =
                                itemView.context.getCircularProgressDrawable()
                            categoryMaterialCard.background = circularProgressDrawable
                        }

                        override fun onLoadFailed(errorDrawable: Drawable?) {
                            super.onLoadFailed(errorDrawable)
                            categoryMaterialCard.background = R.drawable.broken_image.toDrawable()
                        }

                        override fun onResourceReady(
                            resource: Bitmap, transition: Transition<in Bitmap>?
                        ) {
                            categoryMaterialCard.background =
                                BitmapDrawable(itemView.context.resources, resource)
                        }

                        override fun onLoadCleared(placeholder: Drawable?) {}
                    })
            }
        }
    }


}