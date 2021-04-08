package com.theone.mvvm.core.base.fragment

import android.content.Context
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import com.qmuiteam.qmui.arch.QMUIFragment
import com.qmuiteam.qmui.widget.QMUIViewPager
import com.qmuiteam.qmui.widget.tab.QMUITabBuilder
import com.qmuiteam.qmui.widget.tab.QMUITabSegment
import com.theone.common.ext.notNull
import com.theone.mvvm.core.base.adapter.TabFragmentAdapter
import com.theone.mvvm.base.viewmodel.BaseViewModel
import com.theone.mvvm.core.data.entity.QMUITabBean
import com.theone.mvvm.core.ext.*
import com.theone.mvvm.core.ext.qmui.init
import com.theone.mvvm.core.base.viewmodel.BaseRequestViewModel
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
 * @describe TAB相关
 * @email 625805189@qq.com
 * @remark
 */
abstract class BaseTabFragment<VM : BaseViewModel, DB : ViewDataBinding> :
    BaseCoreFragment<VM, DB>() {

    /**
     * 如果TAB数据是从网络获取，则返回一个请求的ViewModel，继承 BaseRequestViewModel
     */
    protected open fun getRequestViewModel(): BaseRequestViewModel<*>? = null

    private var mTabs: MutableList<QMUITabBean> = mutableListOf()
    private var mFragments: MutableList<QMUIFragment> = mutableListOf()

    private lateinit var mPagerAdapter: TabFragmentAdapter

    abstract fun initTabAndFragments(
        tabs: MutableList<QMUITabBean>,
        fragments: MutableList<QMUIFragment>
    )

    abstract fun getViewPager(): QMUIViewPager
    abstract fun getTabSegment(): QMUITabSegment?
    abstract fun getMagicIndicator(): MagicIndicator?

    override fun initView(rootView: View) {

    }

    override fun onLazyInit() {
        getRequestViewModel().notNull({
            showLoadingPage()
           it.requestServer()
        }, {
            startInit()
        })
    }

    override fun onPageReLoad() {
        onLazyInit()
    }

    override fun createObserver() {
        getRequestViewModel()?.run {
            addLoadingObserve(this)
            getResponseLiveData().observeInFragment(this@BaseTabFragment, Observer {
                startInit()
            })
            getErrorMsgLiveData().observeInFragment(this@BaseTabFragment, Observer {
                showErrorPage(it)
            })
        }
    }

    override fun initData() {
    }

    protected open fun startInit() {
        mTabs.clear()
        mFragments.clear()
        initTabAndFragments(mTabs, mFragments)
        initViewPager()
        initTabSegment()
        initMagicIndicator()
        showSuccessPage()
    }

    protected open fun initViewPager() {
        mPagerAdapter = TabFragmentAdapter(childFragmentManager, mFragments)
        getViewPager().run {
            adapter = mPagerAdapter
        }
    }

    protected open fun initTabSegment() {
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
        getPagerTitleView(
            context,
            index,
            mTabs,
            getViewPager()
        )

    protected open fun getNavIndicator(context: Context): IPagerIndicator =
        getLinePagerIndicator(context)

}