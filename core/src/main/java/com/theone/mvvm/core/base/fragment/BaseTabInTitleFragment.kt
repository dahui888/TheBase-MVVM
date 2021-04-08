package com.theone.mvvm.core.base.fragment

import android.view.View
import android.widget.RelativeLayout
import com.qmuiteam.qmui.kotlin.matchParent
import com.qmuiteam.qmui.kotlin.wrapContent
import com.qmuiteam.qmui.util.QMUIResHelper
import com.qmuiteam.qmui.widget.QMUIViewPager
import com.qmuiteam.qmui.widget.tab.QMUITabSegment
import com.theone.common.ext.goneViews
import com.theone.common.ext.showViews
import com.theone.mvvm.core.R
import com.theone.mvvm.base.viewmodel.BaseViewModel
import com.theone.mvvm.core.databinding.BaseTabInTitleLayoutBinding
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
 * @describe Tab在TitleBar类型
 * @email 625805189@qq.com
 * @remark
 */
abstract class BaseTabInTitleFragment<VM : BaseViewModel> :
    BaseTabFragment<VM, BaseTabInTitleLayoutBinding>() {

    private val mMagicIndicator: MagicIndicator by lazy {
        MagicIndicator(context).apply {
            layoutParams = RelativeLayout.LayoutParams(wrapContent, matchParent).apply {
                addRule(RelativeLayout.CENTER_IN_PARENT)
            }
        }
    }

    override fun getLayoutId(): Int = R.layout.base_tab_in_title_layout

    override fun showTopBar(): Boolean = true

    override fun initView(rootView: View) {
        super.initView(rootView)
        initTopBar()
        goneViews(getTopBar())
    }

   protected open fun initTopBar() {
        getTopBar()?.run {
            setCenterView(mMagicIndicator)
            goneViews(this)
        }
    }

    override fun startInit() {
        showViews(getTopBar())
        super.startInit()
    }

    override fun getViewPager(): QMUIViewPager = mBinding.mQMUIViewPager

    override fun getTabSegment(): QMUITabSegment? = null

    override fun getMagicIndicator(): MagicIndicator? = mMagicIndicator
}