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
 * @describe 基类相关
 * @email 625805189@qq.com
 * @remark
 */
interface IBaseQMUI {

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
     * 显示加载框
     * @param msg String?
     */
    fun showLoading(msg:String?)

    /**
     * 隐藏加载框
     */
    fun hideLoading()

    /**
     * @return 是否设置状态栏LightMode true 深色图标 false 白色背景
     */
    fun isStatusBarLightMode():Boolean

    /**
     * @return 是否要进行对状态栏的处理
     */
    fun isNeedChangeStatusBarMode():Boolean

}
