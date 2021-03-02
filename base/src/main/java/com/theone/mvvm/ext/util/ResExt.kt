package com.theone.mvvm.ext.util

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.content.ContextCompat
import com.qmuiteam.qmui.util.QMUIDisplayHelper


//  ┏┓　　　┏┓
//┏┛┻━━━┛┻┓
//┃　　　　　　　┃
//┃　　　━　　　┃
//┃　┳┛　┗┳　┃
//┃　　　　　　　┃
//┃　　　┻　　　┃
//┃　　　　　　　┃
//┗━┓　　　┏━┛
//    ┃　　　┃                  神兽保佑
//    ┃　　　┃                  永无BUG！
//    ┃　　　┗━━━┓
//    ┃　　　　　　　┣┓
//    ┃　　　　　　　┏┛
//    ┗┓┓┏━┳┓┏┛
//      ┃┫┫　┃┫┫
//      ┗┻┛　┗┻┛
/**
 * @author The one
 * @date 2021/3/2 0002
 * @describe TODO
 * @email 625805189@qq.com
 * @remark
 */

fun getDrawable(context: Context, drawable: Int): Drawable? {
    return ContextCompat.getDrawable(context, drawable)
}

fun View.getDrawable(drawable: Int): Drawable? {
    return getDrawable(context, drawable)
}

fun dp2px(context: Context, dp: Int): Int {
    return QMUIDisplayHelper.dp2px(context, dp)
}

fun sp2px(context: Context, sp: Int): Int {
    return QMUIDisplayHelper.sp2px(context, sp)
}