package com.theone.mvvm.base.fragment

import com.qmuiteam.qmui.widget.QMUIViewPager
import com.qmuiteam.qmui.widget.tab.QMUITabSegment
import com.theone.mvvm.R
import com.theone.mvvm.base.viewmodel.BaseViewModel
import kotlinx.android.synthetic.main.base_tab_in_title_layout.*
import net.lucode.hackware.magicindicator.MagicIndicator


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
 * @date 2021/3/3 0003
 * @describe TODO
 * @email 625805189@qq.com
 * @remark
 */
abstract class BaseTabInTitleFragment<VM : BaseViewModel> : BaseTabFragment<VM>() {

    override fun showTitleBar(): Boolean = false

    override fun getLayoutId(): Int = R.layout.base_tab_in_title_layout

    override fun getViewPager(): QMUIViewPager = mQMUIViewPager

    override fun getTabSegment(): QMUITabSegment? = null

    override fun getMagicIndicator(): MagicIndicator? = mMagicIndicator

}