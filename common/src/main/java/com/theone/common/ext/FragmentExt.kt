package com.theone.common.ext

import android.view.View
import androidx.fragment.app.Fragment


fun Fragment.getView(layoutId: Int): View {
   return layoutInflater.inflate(layoutId, null)
}

/**
 * dp值转换为px
 */
fun Fragment.dp2px(dp: Int): Int {
    return requireContext().dp2px(dp)
}

/**
 * px值转换成dp
 */
fun Fragment.px2dp(px: Int): Int {
   return requireContext().px2dp(px)
}

inline fun <reified T:Any> Fragment.getValue(label:String,defaultValue:T?=null) = lazy {
    val value = arguments?.get(label)
    if(value is T) value else defaultValue
}

inline fun <reified T:Any> Fragment.getValueNonNull(label:String,defaultValue:T?=null) = lazy {
    val value = arguments?.get(label)
    requireNotNull(if(value is T) value else defaultValue){
        label
    }
}