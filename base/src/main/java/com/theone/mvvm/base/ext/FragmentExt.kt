package com.theone.mvvm.base.ext

import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment
import com.qmuiteam.qmui.arch.QMUIFragment
import com.qmuiteam.qmui.util.QMUIDisplayHelper
import com.qmuiteam.qmui.util.QMUIStatusBarHelper
import com.theone.mvvm.base.fragment.BaseFragment


/**
 * 更新状态栏模式
 *
 * @param isLight true 设置状态栏黑色字体图标，
 *
 * 支持 4.4 以上版本 MIUI 和 Flyme，以及 6.0 以上版本的其他 Android
 */
fun Fragment.updateStatusBarMode(isLight: Boolean) {
    if (isLight) {
        QMUIStatusBarHelper.setStatusBarLightMode(requireActivity())
    } else {
        QMUIStatusBarHelper.setStatusBarDarkMode(requireActivity())
    }
}


fun Fragment.getView(layoutId: Int): View {
   return layoutInflater.inflate(layoutId, null)
}

fun Fragment.dp2px(dp: Int): Int = QMUIDisplayHelper.dp2px(requireActivity(), dp)
fun Fragment.sp2px(dp: Int): Int = QMUIDisplayHelper.sp2px(requireActivity(), dp)