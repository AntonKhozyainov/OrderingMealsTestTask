package ru.khozyainov.orderingmealstesttask.ui.categorydetail

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import ru.khozyainov.orderingmealstesttask.R
import ru.khozyainov.orderingmealstesttask.databinding.ItemDishBinding
import ru.khozyainov.orderingmealstesttask.model.DishUi

class DishAdapterDelegate(
    private val onItemClicked: (dish: DishUi) -> Unit
) : AbsListItemAdapterDelegate<DishUi, DishUi, DishAdapterDelegate.DishHolder>() {

    override fun isForViewType(
        item: DishUi,
        items: MutableList<DishUi>,
        position: Int
    ): Boolean = true

    override fun onCreateViewHolder(parent: ViewGroup): DishHolder =
        DishHolder(
            binding = ItemDishBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onItemClicked = onItemClicked
        )

    override fun onBindViewHolder(
        item: DishUi,
        holder: DishHolder,
        payloads: MutableList<Any>
    ) {
        holder.bind(dish = item)
    }

    class DishHolder(
        private val binding: ItemDishBinding,
        onItemClicked: (dish: DishUi) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        private lateinit var currentDish: DishUi

        init {
            binding.root.setOnClickListener {
                onItemClicked(currentDish)
            }
        }

        fun bind(dish: DishUi) {

            currentDish = dish

            with(binding){
                itemDishTextView.text = dish.title

                Glide.with(itemView)
                    .asBitmap()
                    .load(dish.imageUrl)
                    .into(object : CustomTarget<Bitmap>() {

                        override fun onLoadStarted(placeholder: Drawable?) {
                            super.onLoadStarted(placeholder)
                            val circularProgressDrawable =
                                CircularProgressDrawable(itemView.context).apply {
                                    strokeWidth = 5f
                                    centerRadius = 30f
                                    start()
                                }
                            itemDishImageView.setImageDrawable(circularProgressDrawable)
                        }

                        override fun onLoadFailed(errorDrawable: Drawable?) {
                            super.onLoadFailed(errorDrawable)
                            itemDishImageView.setImageResource(R.drawable.broken_image)
                        }

                        override fun onResourceReady(
                            resource: Bitmap,
                            transition: Transition<in Bitmap>?
                        ) {
                            itemDishImageView.setImageBitmap(resource)
                        }

                        override fun onLoadCleared(placeholder: Drawable?) {}
                    })
            }
        }
    }


}