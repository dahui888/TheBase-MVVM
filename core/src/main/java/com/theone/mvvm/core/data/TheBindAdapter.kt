package com.theone.mvvm.core.data

import android.graphics.drawable.Drawable
import android.os.SystemClock
import android.text.Editable
import android.text.InputType
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import android.widget.*
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.qmuiteam.qmui.util.QMUIResHelper
import com.theone.common.ext.YYYY_MM_DD_HH_MM_SS
import com.theone.common.ext.formatLong
import com.theone.common.ext.getDrawable
import com.theone.common.util.DateFormatUtils
import com.theone.mvvm.callback.databind.BooleanObservableField
import com.theone.mvvm.core.R


object TheBindAdapter {

    @BindingAdapter(value = ["longDate", "type"], requireAll = false)
    @JvmStatic
    fun formatDate(
        textView: TextView,
        longDate: Long,
        type: Int?
    ) {
        textView.text = DateFormatUtils.formatTimeStampString(
            textView.context,
            longDate,
            if (null == type) DateFormatUtils.FORMAT_TYPE_PERSONAL_FOOTPRINT else DateFormatUtils.FORMAT_TYPE_NORMAL
        )
    }

    @BindingAdapter(value = ["date", "type"], requireAll = false)
    @JvmStatic
    fun formatStringDate(
        textView: TextView,
        date: String,
        type: Int?
    ) {
        textView.text = DateFormatUtils.formatTimeStampString(
            textView.context,
            date.formatLong(
                YYYY_MM_DD_HH_MM_SS
            )!!,
            if (null == type) DateFormatUtils.FORMAT_TYPE_PERSONAL_FOOTPRINT else DateFormatUtils.FORMAT_TYPE_NORMAL
        )
    }

    @BindingAdapter(value = ["checkChange"])
    @JvmStatic
    fun checkChange(checkbox: CheckBox, listener: CompoundButton.OnCheckedChangeListener) {
        checkbox.setOnCheckedChangeListener(listener)
    }

    @BindingAdapter(value = ["check", "check_drawable"], requireAll = false)
    @JvmStatic
    fun initCheckBox(checkbox: CheckBox, value: BooleanObservableField, checkDrawable: Drawable?) {
        checkbox.run {
            buttonDrawable = checkDrawable ?: QMUIResHelper.getAttrDrawable(
                context,
                R.attr.qmui_common_list_item_switch
            )
            isChecked = value.get()
            setOnCheckedChangeListener { _, isChecked ->
                value.set(isChecked)
            }
        }
    }

    @BindingAdapter(value = ["showPwd"])
    @JvmStatic
    fun showPwd(view: EditText, boolean: Boolean) {
        if (boolean) {
            view.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
        } else {
            view.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        }
        view.setSelection(view.text.length)
    }

    @BindingAdapter(value = ["imageUrl", "placeHolder"], requireAll = false)
    @JvmStatic
    fun imageUrl(view: ImageView, url: String?, placeholder: Drawable? = null) {
        if(url.isNullOrEmpty()) return
        Glide.with(view.context)
            .load(url)
            .transition(DrawableTransitionOptions().crossFade())
            .apply(
                RequestOptions().placeholder(
                    placeholder
                        ?: getDrawable(
                            view.context,
                            R.drawable.image_place_holder
                        )
                ).centerCrop().diskCacheStrategy(DiskCacheStrategy.ALL)
            )
            .into(view)
    }

    @BindingAdapter(value = ["visible"], requireAll = false)
    @JvmStatic
    fun visible(view: View, visible: Boolean) {
        view.visibility = if (visible) View.VISIBLE else View.GONE
    }

    @BindingAdapter(value = ["visible"], requireAll = false)
    @JvmStatic
    fun visibleNoEmpty(view: View, text: String) {
        view.visibility = if (TextUtils.isEmpty(text)) View.GONE else View.VISIBLE
    }

    @BindingAdapter(value = ["circleImageUrl"])
    @JvmStatic
    fun circleImageUrl(view: ImageView, url: String) {
        Glide.with(view.context.applicationContext)
            .load(url)
            .apply(RequestOptions.bitmapTransform(CircleCrop()))
            .transition(DrawableTransitionOptions.withCrossFade(500))
            .into(view)
    }

    @BindingAdapter(value = ["afterTextChanged"])
    @JvmStatic
    fun EditText.afterTextChanged(action: (String) -> Unit) {
        addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                action(s.toString())
            }
        })
    }

}