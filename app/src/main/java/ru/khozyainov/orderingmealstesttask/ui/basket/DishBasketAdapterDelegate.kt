package ru.khozyainov.orderingmealstesttask.ui.basket

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
import ru.khozyainov.domain.model.DishInBasket
import ru.khozyainov.orderingmealstesttask.R
import ru.khozyainov.orderingmealstesttask.databinding.ItemDishBasketBinding

class DishBasketAdapterDelegate(
    private val onClickedInc: (dish: DishInBasket) -> Unit,
    private val onClickedDec: (dish: DishInBasket) -> Unit
) : AbsListItemAdapterDelegate<DishInBasket, DishInBasket, DishBasketAdapterDelegate.DishInBasketHolder>() {

    override fun isForViewType(
        item: DishInBasket,
        items: MutableList<DishInBasket>,
        position: Int
    ): Boolean = true

    override fun onCreateViewHolder(parent: ViewGroup): DishInBasketHolder =
        DishInBasketHolder(
            binding = ItemDishBasketBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onClickedInc = onClickedInc,
            onClickedDec = onClickedDec
        )

    override fun onBindViewHolder(
        item: DishInBasket,
        holder: DishInBasketHolder,
        payloads: MutableList<Any>
    ) {
        holder.bind(dishInBasket = item)
    }

    class DishInBasketHolder(
        private val binding: ItemDishBasketBinding,
        onClickedInc: (dish: DishInBasket) -> Unit,
        onClickedDec: (dish: DishInBasket) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        private lateinit var currentDishInBasket: DishInBasket

        init {
            binding.changeCountDishButton.setOnClickListener {
                onClickedDec(currentDishInBasket)
            }
        }

        fun bind(dishInBasket: DishInBasket) {

            currentDishInBasket = dishInBasket


            with(binding){
                titleBasketDishTextView.text = dishInBasket.title

                priceBasketDishTextView.text = itemView.context.getString(R.string.price, dishInBasket.price)
                weightBasketDishTextView.text = itemView.context.getString(R.string.weight, dishInBasket.weight)

                Glide.with(itemView)
                    .asBitmap()
                    .load(dishInBasket.imageUrl)
                    .into(object : CustomTarget<Bitmap>() {

                        override fun onLoadStarted(placeholder: Drawable?) {
                            super.onLoadStarted(placeholder)
                            val circularProgressDrawable =
                                CircularProgressDrawable(itemView.context).apply {
                                    strokeWidth = 5f
                                    centerRadius = 30f
                                    start()
                                }
                            basketDishImageView.setImageDrawable(circularProgressDrawable)
                        }

                        override fun onLoadFailed(errorDrawable: Drawable?) {
                            super.onLoadFailed(errorDrawable)
                            basketDishImageView.setImageResource(R.drawable.broken_image)
                        }

                        override fun onResourceReady(
                            resource: Bitmap,
                            transition: Transition<in Bitmap>?
                        ) {
                            basketDishImageView.setImageBitmap(resource)
                        }

                        override fun onLoadCleared(placeholder: Drawable?) {}
                    })
            }
        }
    }


}