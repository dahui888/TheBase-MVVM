package com.theone.mvvm.base.fragment

import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.qmuiteam.qmui.kotlin.matchParent
import com.qmuiteam.qmui.widget.QMUIViewPager
import com.qmuiteam.qmui.widget.tab.QMUITabSegment
import com.theone.mvvm.R
import com.theone.mvvm.base.viewmodel.BaseViewModel
import com.theone.mvvm.entity.QMUITabBean
import kotlinx.android.synthetic.main.base_title_tab_layout.*
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
 * @date 2021/3/2 0002
 * @describe TODO
 * @email 625805189@qq.com
 * @remark
 */
abstract class BaseTitleTabFragment<VM : BaseViewModel> : BaseTabFragment<VM>() {

    override fun getLayoutId(): Int = R.layout.base_title_tab_layout

    override fun getViewPager(): QMUIViewPager = view_pager

    override fun getTabSegment(): QMUITabSegment? = null

    override fun getMagicIndicator(): MagicIndicator? = indicator

}