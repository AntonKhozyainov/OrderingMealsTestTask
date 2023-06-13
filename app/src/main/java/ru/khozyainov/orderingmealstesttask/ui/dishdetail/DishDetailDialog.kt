package ru.khozyainov.orderingmealstesttask.ui.dishdetail

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import ru.khozyainov.orderingmealstesttask.R
import ru.khozyainov.orderingmealstesttask.databinding.DialogDishBinding
import ru.khozyainov.orderingmealstesttask.utils.ViewBindingDialogFragment
import ru.khozyainov.orderingmealstesttask.utils.appComponent
import ru.khozyainov.orderingmealstesttask.utils.getCircularProgressDrawable
import javax.inject.Inject

class DishDetailDialog : ViewBindingDialogFragment<DialogDishBinding>(DialogDishBinding::inflate) {

    @Inject
    lateinit var viewModel: DishDetailViewModel

    private val args: DishDetailDialogArgs by navArgs()

    override fun onAttach(context: Context) {
        requireContext().appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val dish = args.dish
        with(binding) {
            titleDishDetailTextView.text = dish.title
            priceDishDetailTextView.text = getString(R.string.price, dish.price)
            weightDishDetailTextView.text = getString(R.string.weight, dish.weight)
            descriptionDishDetailTextView.text = dish.description
            descriptionDishDetailTextView.movementMethod = ScrollingMovementMethod()

            closeDishDetailImageButton.setOnClickListener {
                dismiss()
            }

            addToBasketButton.setOnClickListener {
                viewModel.addDishToBasket(dish = args.dish)
            }

            setImage()
        }

    }

    private fun setImage() {

        Glide.with(requireContext()).asBitmap().load(args.dish.imageUrl)
            .into(object : CustomTarget<Bitmap>() {

                override fun onLoadStarted(placeholder: Drawable?) {
                    super.onLoadStarted(placeholder)
                    val circularProgressDrawable = requireContext().getCircularProgressDrawable()
                    binding.dishDetailImageView.setImageDrawable(circularProgressDrawable)
                }

                override fun onLoadFailed(errorDrawable: Drawable?) {
                    super.onLoadFailed(errorDrawable)
                    binding.dishDetailImageView.setImageResource(R.drawable.broken_image)
                }

                override fun onResourceReady(
                    resource: Bitmap, transition: Transition<in Bitmap>?
                ) {
                    binding.dishDetailImageView.setImageBitmap(resource)
                }

                override fun onLoadCleared(placeholder: Drawable?) {}
            })


    }

}