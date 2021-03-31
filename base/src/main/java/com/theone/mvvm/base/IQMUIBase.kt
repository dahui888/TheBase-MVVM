package com.theone.mvvm.base

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import com.qmuiteam.qmui.kotlin.matchParent
import com.qmuiteam.qmui.kotlin.wrapContent
import com.qmuiteam.qmui.util.QMUIResHelper
import com.qmuiteam.qmui.widget.QMUITopBarLayout
import com.qmuiteam.qmui.widget.QMUIWindowInsetLayout
import com.theone.mvvm.R

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
 * @date 2021-03-31 14:28
 * @describe TODO
 * @email 625805189@qq.com
 * @remark
 */
interface IQMUIBase {

    /**
     * 获取布局
     */
    fun getLayoutId(): Int

    /**
     * 提供一个方法供子类获取TopBar
     */
    fun getTopBar(): QMUITopBarLayout?

    /**
     * 是否需要TopBar
     */
    fun showTopBar(): Boolean

    /**
     * 获取内容层
     * @return View
     */
    fun getContentView(): View

    /**
     * 布局初始化
     * @param root View
     */
    fun initView(root: View)

    /**
     * @return 是否设置状态栏LightMode true 深色图标 false 白色背景
     */
    fun isStatusBarLightMode():Boolean

    /**
     * @return 是否要进行对状态栏的处理
     */
    fun isNeedChangeStatusBarMode():Boolean

}

fun IQMUIBase.createView(context: Context, translucentFull: Boolean): View {
    if (showTopBar()) {
        // 如果需要TopBar，创建一个布局，加入TopBar和Body
        val root = QMUIWindowInsetLayout(context)
        root.layoutParams = ViewGroup.LayoutParams(matchParent, matchParent)
        getContentView().run {
            fitsSystemWindows = !translucentFull
            root.addView(this)
            // 这个一定要放在addView后面
            if (!translucentFull) {
                // 设置一个向上的TopBar高度的间距
                val margin = QMUIResHelper.getAttrDimen(
                    context,
                    R.attr.qmui_topbar_height
                )
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