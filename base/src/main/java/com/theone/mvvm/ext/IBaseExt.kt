package com.theone.mvvm.ext

import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.qmuiteam.qmui.kotlin.matchParent
import com.qmuiteam.qmui.kotlin.wrapContent
import com.qmuiteam.qmui.layout.QMUIFrameLayout
import com.qmuiteam.qmui.util.QMUIResHelper
import com.qmuiteam.qmui.widget.QMUITopBarLayout
import com.qmuiteam.qmui.widget.QMUIWindowInsetLayout
import com.theone.mvvm.R
import com.theone.mvvm.base.IQMUIBase

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
 * @param translucentFull Boolean
 * @return View
 */
fun IQMUIBase.createView(context: Context, translucentFull: Boolean): View {
    if (showTopBar()) {
        // 如果需要TopBar，创建一个布局，加入TopBar和Body
        val root = QMUIWindowInsetLayout(context)
        root.layoutParams = ViewGroup.LayoutParams(matchParent, matchParent)
        // TODO 这里要再额外的用一层，不能直接用contentView，原因见下Tips1
        QMUIFrameLayout(context).run {
            addView(getContentView())
            fitsSystemWindows = !translucentFull
            root.addView(this)
            // 这个一定要放在addView后面
            if (!translucentFull) {
                // 获取TopBar的高度
                val margin = QMUIResHelper.getAttrDimen(
                    context,
                    R.attr.qmui_topbar_height
                )
                // TODO Tips1 这里设置了margin后会使content最外层布局设置的一些属性无效。
                // 设置一个向上的TopBar高度的间距
                layoutParams.let {
                    if (it is ViewGroup.MarginLayoutParams) {
                        it.setMargins(0, margin, 0, 0)
                        requestLayout()
                    }
                }
            }
        }
        // TopBar要放在后面（布局的上一层），如果body充满整个父容器时，要保证TopBar是在上面的。
        root.addView(getTopBar())
        return root
    }
    return getContentView()
}

/**
 * 创建判断一个默认的TopBar
 * @receiver IQMUIBase
 * @param context Context
 * @return QMUITopBarLayout?
 */
fun IQMUIBase.createTopBar(context: Context): QMUITopBarLayout? {
    return if (showTopBar()) {
        QMUITopBarLayout(context).apply {
            layoutParams = ViewGroup.LayoutParams(matchParent, wrapContent)
            fitsSystemWindows = true
        }
    } else {
        null
    }
}