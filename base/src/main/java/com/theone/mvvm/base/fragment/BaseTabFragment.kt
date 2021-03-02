package com.theone.mvvm.base.fragment

import android.content.Context
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.qmuiteam.qmui.widget.QMUIViewPager
import com.qmuiteam.qmui.widget.tab.QMUITabBuilder
import com.qmuiteam.qmui.widget.tab.QMUITabSegment
import com.theone.mvvm.base.adapter.TabFragmentAdapter
import com.theone.mvvm.base.viewmodel.BaseViewModel
import com.theone.mvvm.entity.QMUITabBean
import com.theone.mvvm.ext.getLinePagerIndicator
import com.theone.mvvm.ext.getPagerTitleView
import com.theone.mvvm.ext.qmui.init
import net.lucode.hackware.magicindicator.MagicIndicator
import net.lucode.hackware.magicindicator.ViewPagerHelper
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView


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
abstract class BaseTabFragment<VM : BaseViewModel> : BaseVmFragment<VM>() {

    private var mTabs: MutableList<QMUITabBean> = mutableListOf()
    private var mFragments: MutableList<Fragment> = mutableListOf()

    private lateinit var mPagerAdapter: TabFragmentAdapter

    abstract fun initTabAndFragments(tabs: MutableList<QMUITabBean>, fragments: MutableList<Fragment>)

    abstract fun getViewPager(): QMUIViewPager
    abstract fun getTabSegment(): QMUITabSegment?
    abstract fun getMagicIndicator(): MagicIndicator?

    override fun initView(rootView: View) {
    }

    /**
     * @remark 如果Tab数据从网络加载，重写这个方法，请求数据，待数据返回成功后调用 {@link #startInit}方法
     */
    override fun onLazyInit() {
        startInit()
    }

    protected open fun startInit() {
        initTabAndFragments(mTabs, mFragments)
        initViewPager()
        initSegment()
        initMagicIndicator()
        mLoadSir.showSuccess()
    }

    protected open fun initViewPager() {
        mPagerAdapter = TabFragmentAdapter(childFragmentManager, mFragments)
        getViewPager().run {
            adapter = mPagerAdapter
        }
    }

    protected open fun initSegment() {
        getTabSegment()?.init(getViewPager(), mTabs, createTabBuilder()!!)
    }

    protected open fun createTabBuilder(): QMUITabBuilder? {
        return getTabSegment()?.tabBuilder()
    }

    protected open fun initMagicIndicator() {
        getMagicIndicator()?.run {
            navigator = createNavigator()
            ViewPagerHelper.bind(this, getViewPager())
        }
    }

    protected open fun createNavigator(): CommonNavigator {
        val mCommonNavigator = CommonNavigator(mActivity)
        mCommonNavigator.run {
            scrollPivotX = 0.65f
            isAdjustMode = false
            adapter = createNavigatorAdapter()
        }
        return mCommonNavigator
    }

    protected open fun createNavigatorAdapter(): CommonNavigatorAdapter? {
        return object : CommonNavigatorAdapter() {
            override fun getCount(): Int = mTabs.size

            override fun getTitleView(context: Context, index: Int): IPagerTitleView {
                return getNavTitleView(context, index)
            }

            override fun getIndicator(context: Context): IPagerIndicator {
                return getNavIndicator(context)
            }
        }
    }

    protected open fun getNavTitleView(context: Context, index: Int): IPagerTitleView =
        getPagerTitleView(context, index, mTabs, getViewPager())

    protected open fun getNavIndicator(context: Context): IPagerIndicator =
        getLinePagerIndicator(context)

}