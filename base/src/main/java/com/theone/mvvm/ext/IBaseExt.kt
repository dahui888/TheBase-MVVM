package com.theone.mvvm.ext

import android.content.Context
import android.util.SparseArray
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import com.qmuiteam.qmui.kotlin.matchParent
import com.qmuiteam.qmui.kotlin.wrapContent
import com.qmuiteam.qmui.layout.QMUIFrameLayout
import com.qmuiteam.qmui.util.QMUIResHelper
import com.qmuiteam.qmui.widget.QMUITopBarLayout
import com.qmuiteam.qmui.widget.QMUIWindowInsetLayout
import com.theone.mvvm.R
import com.theone.mvvm.base.IBaseQMUI

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
 * @date 2021-04-01 13:47
 * @describe TODO
 * @email 625805189@qq.com
 * @remark
 */

/**
 * 根据判断创建布局
 * @receiver IQMUIBase
 * @param context Context
 * @param translucentFull Boolean 内容层是否需要填充满整个父布局，一般是延伸至状态栏，TopBar设置为透明。
 * @return View
 */
fun IBaseQMUI.createView(context: Context, translucentFull: Boolean): View {
    if (showTopBar()) {
        // 如果需要TopBar，创建一个布局，加入TopBar和Body
        return QMUIWindowInsetLayout(context).apply {
            layoutParams = ViewGroup.LayoutParams(matchParent, matchParent)
            // TODO 这里要再额外的用一层，不能直接用contentView，原因见下Tips1
            QMUIFrameLayout(context).run {
                addView(getContentView())
                fitsSystemWindows = !translucentFull
                this@apply.addView(this)
                // TODO 这个时候这个设置margin一定要放在addView后面
                if (!translucentFull) {
                    // 获取TopBar的高度
                    val topBarHeight = QMUIResHelper.getAttrDimen(
                        context,
                        R.attr.qmui_topbar_height
                    )
                    // TODO Tips1 这里设置了margin后会使content最外层布局设置的一些属性无效。
                    // 设置一个向上的TopBar高度的间距
                    layoutParams.let {
                        if (it is ViewGroup.MarginLayoutParams) {
                            it.setMargins(0, topBarHeight, 0, 0)
                            requestLayout()
                        }
                    }
                }
            }
            // TopBar要放在后面（布局的上一层），如果body充满整个父容器时，要保证TopBar是在上面的。
            addView(getTopBar())
        }
    }
    // 不需要TopBar直接返回内容层
    return getContentView()
}

/**
 * 创建判断一个默认的TopBar
 * @receiver IQMUIBase
 * @param context Context
 * @return QMUITopBarLayout?
 */
fun IBaseQMUI.createTopBar(context: Context): QMUITopBarLayout? {
    return if (showTopBar()) {
        QMUITopBarLayout(context).apply {
            layoutParams = ViewGroup.LayoutParams(matchParent, wrapContent)
            fitsSystemWindows = true
        }
    } else {
        null
    }
}

fun SparseArray<Any>.addParams(@NonNull variableId: Int,
                               @NonNull any: Any){
    if(get(variableId) == null){
        put(variableId,any)
    }
}