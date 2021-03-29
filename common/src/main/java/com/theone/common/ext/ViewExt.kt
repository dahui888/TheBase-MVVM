package com.theone.common.ext

import android.view.View
import android.view.ViewGroup


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
 * @date 2021/2/22 0022
 * @describe TODO
 * @email 625805189@qq.com
 * @remark
 */

val matchParent: Int = ViewGroup.LayoutParams.MATCH_PARENT
val wrapContent: Int = ViewGroup.LayoutParams.WRAP_CONTENT

val match_wrap : ViewGroup.LayoutParams = ViewGroup.LayoutParams(matchParent, wrapContent)
val wrap_wrap : ViewGroup.LayoutParams = ViewGroup.LayoutParams(wrapContent, wrapContent)

fun View.setMargin(left: Int, top: Int, right: Int, bottom: Int) {
    val params = layoutParams
    if (params is ViewGroup.MarginLayoutParams) {
        params.setMargins(left, top, right, bottom)
        requestLayout()
    }
}

fun setVisible(visible: Int, vararg views: View?) {
    for (view in views)
        view?.run {
            if (visibility != visible) {
                visibility = visible
            }
        }
}

fun goneViews( vararg views: View?){
    setVisible(View.GONE, *views)
}

fun showViews( vararg views: View?){
    setVisible(View.VISIBLE, *views)
}
