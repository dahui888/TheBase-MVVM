package com.theone.mvvm.callback.databind

import android.text.SpannableString
import androidx.databinding.ObservableField

/**
 * 描述　:自定义的SpannableString类型 ObservableField  提供了默认值，避免取值的时候还要判空
 */
class SpannableStringObservableField(value: SpannableString = SpannableString("")) : ObservableField<SpannableString>(value) {

    override fun get(): SpannableString {
        return super.get()!!
    }

}