package com.example.newsproject.ui.utils

import android.widget.TextView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.airbnb.lottie.LottieAnimationView
import com.example.newsproject.ui.CustomProgressBarView

object VisibilityDataBinding {

    @JvmStatic
    @BindingAdapter("empty")
    fun TextView.setVisibility(
        boolean: Boolean?
    ) {
        if (boolean != null) {
            this.isVisible = boolean
        }
    }

    @JvmStatic
    @BindingAdapter("empty")
    fun LottieAnimationView.setVisibility(
        boolean: Boolean?
    ) {
        if (boolean != null) {
            this.isVisible = boolean
        }
    }

    @JvmStatic
    @BindingAdapter("loading")
    fun CustomProgressBarView.setVisibility(
        boolean: Boolean?
    ){
        if (boolean != null) {
            this.isVisible = boolean
        }
    }
}