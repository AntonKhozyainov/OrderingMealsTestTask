package ru.khozyainov.orderingmealstesttask.ui.basket

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import ru.khozyainov.orderingmealstesttask.R
import ru.khozyainov.orderingmealstesttask.databinding.ItemDishBasketBinding
import ru.khozyainov.orderingmealstesttask.model.DishUi
import ru.khozyainov.orderingmealstesttask.utils.getCircularProgressDrawable

class DishBasketAdapterDelegate(
    private val onClickedInc: (dish: DishUi) -> Unit,
    private val onClickedDec: (dish: DishUi) -> Unit
) : AbsListItemAdapterDelegate<DishUi, DishUi, DishBasketAdapterDelegate.DishInBasketHolder>() {

    override fun isForViewType(
        item: DishUi, items: MutableList<DishUi>, position: Int
    ): Boolean = true

    override fun onCreateViewHolder(parent: ViewGroup): DishInBasketHolder = DishInBasketHolder(
        binding = ItemDishBasketBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ), onClickedInc = onClickedInc, onClickedDec = onClickedDec
    )

    override fun onBindViewHolder(
        item: DishUi, holder: DishInBasketHolder, payloads: MutableList<Any>
    ) {
        holder.bind(dish = item)
    }

    class DishInBasketHolder(
        private val binding: ItemDishBasketBinding,
        onClickedInc: (dish: DishUi) -> Unit,
        onClickedDec: (dish: DishUi) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        private lateinit var currentDishInBasket: DishUi

        init {
            binding.incDishBasketButton.setOnClickListener {
                onClickedInc(currentDishInBasket)
            }

            binding.decDishBasketButton.setOnClickListener {
                onClickedDec(currentDishInBasket)
            }
        }

        fun bind(dish: DishUi) {

            currentDishInBasket = dish

            with(binding) {
                titleBasketDishTextView.text = dish.title
                priceBasketDishTextView.text = itemView.context.getString(R.string.price, dish.price)
                weightBasketDishTextView.text = itemView.context.getString(R.string.weight, dish.weight)
                countDishBasketTextView.text = dish.count.toString()

                Glide.with(itemView).asBitmap().load(dish.imageUrl)
                    .into(object : CustomTarget<Bitmap>() {

                        override fun onLoadStarted(placeholder: Drawable?) {
                            super.onLoadStarted(placeholder)
                            val circularProgressDrawable =
                                itemView.context.getCircularProgressDrawable()
                            basketDishImageView.setImageDrawable(circularProgressDrawable)
                        }

                        override fun onLoadFailed(errorDrawable: Drawable?) {
                            super.onLoadFailed(errorDrawable)
                            basketDishImageView.setImageResource(R.drawable.broken_image)
                        }

                        override fun onResourceReady(
                            resource: Bitmap, transition: Transition<in Bitmap>?
                        ) {
                            basketDishImageView.setImageBitmap(resource)
                        }

                        override fun onLoadCleared(placeholder: Drawable?) {}
                    })
            }
        }
    }


}