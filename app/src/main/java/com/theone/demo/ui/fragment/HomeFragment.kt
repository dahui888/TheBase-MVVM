package com.theone.demo.ui.fragment

import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getColor
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.OnLifecycleEvent
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.qmuiteam.qmui.kotlin.matchParent
import com.qmuiteam.qmui.qqface.QMUIQQFaceView
import com.qmuiteam.qmui.util.QMUIColorHelper
import com.qmuiteam.qmui.util.QMUIResHelper
import com.theone.demo.R
import com.theone.demo.data.model.bean.ArticleResponse
import com.theone.demo.data.model.bean.BannerResponse
import com.theone.demo.ui.adapter.ArticleAdapter
import com.theone.demo.viewmodel.HomeViewModel
import com.theone.demo.app.widge.OffsetLinearLayoutManager
import com.theone.demo.app.widge.banner.BannerViewHolder
import com.theone.mvvm.base.constant.LayoutManagerType
import com.theone.mvvm.base.fragment.BaseRecyclerPagerFragment
import com.theone.mvvm.databinding.BaseRecyclerPagerFragmentBinding
import com.zhpan.bannerview.BannerViewPager
import com.zhpan.bannerview.constants.IndicatorGravity


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
class HomeFragment :
    BaseRecyclerPagerFragment<ArticleResponse, ArticleAdapter, HomeViewModel, BaseRecyclerPagerFragmentBinding>() {

    private lateinit var mBannerViewPager: BannerViewPager<BannerResponse, BannerViewHolder>
    private var mMaxOffsetHeight: Float = 0f
    private var isLightMode: Boolean = true
    private var isShow: Boolean = true
    private lateinit var mTitleView: QMUIQQFaceView

    override fun isNeedChangeStatusBarMode(): Boolean = true

    override fun isStatusBarLightMode(): Boolean = isLightMode

    override fun showTitleBar(): Boolean = true

    override fun createAdapter(): ArticleAdapter = ArticleAdapter()

    override fun translucentFull(): Boolean = true

    override fun initView(rootView: View) {
        super.initView(rootView)
        getTopBar()?.run {
            mTitleView = setTitle("首页")
            setBackgroundAlpha(0)
        }
    }

    override fun initAdapter() {
        super.initAdapter()
        val mBannerHeight = dp2px(250)
        mMaxOffsetHeight = (mBannerHeight - QMUIResHelper.getAttrDimen(
            mActivity,
            R.attr.qmui_topbar_height
        )).toFloat()
        mBannerViewPager = BannerViewPager<BannerResponse, BannerViewHolder>(mActivity)
        mBannerViewPager.layoutParams = ViewGroup.LayoutParams(matchParent, mBannerHeight)
        mBannerViewPager
            .setIndicatorGravity(IndicatorGravity.END)
            .setIndicatorSliderColor(
                getColor(mActivity, R.color.white),
                QMUIResHelper.getAttrColor(mActivity, R.attr.app_skin_primary_color)
            )
            .setHolderCreator { BannerViewHolder() }
            .setOnPageClickListener { position: Int ->

            }
        mAdapter.addHeaderView(mBannerViewPager)
    }

    override fun initRecyclerView() {
        super.initRecyclerView()
        mRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val offsetLinearLayoutManager =
                    recyclerView.layoutManager as OffsetLinearLayoutManager
                val y = offsetLinearLayoutManager.computeVerticalScrollOffset(null)
                val percent: Float
                 = if (y > mMaxOffsetHeight)
                    1.0f
                else
                    y / mMaxOffsetHeight
                val isLight = percent > 0.6
                if (!isLightMode && isLight) {
                    setStatusBarMode(true)
                } else if (isLightMode && !isLight) {
                    setStatusBarMode(false)
                }
                mTitleView.setTextColor(
                    getColorAlpha(
                        percent,
                        R.color.qmui_config_color_gray_1
                    )
                )
                getTopBar()?.run {
                    updateBottomSeparatorColor(
                        getColorAlpha(
                            percent,
                            R.color.qmui_config_color_separator
                        )
                    )
                    setBackgroundColor(
                        getColorAlpha(
                            percent,
                            R.color.qmui_config_color_white
                        )
                    )
                }
            }
        })
    }

    private fun getColorAlpha(percent: Float, color: Int): Int {
        return QMUIColorHelper.setColorAlpha(
            getColor(mActivity, color),
            percent
        )
    }

    override fun getLayoutManager(type: LayoutManagerType?): RecyclerView.LayoutManager {
        return OffsetLinearLayoutManager(mActivity)
    }

    /**
     * 更改状态栏模式
     * 由于只有折叠和展开时才会调用，所以在这里对轮播也进行处理下
     * @param isLight
     * @remark 显示的时候才做更改
     */
    private fun setStatusBarMode(isLight: Boolean) {
        isLightMode = isLight
        if (isShow) {
            setBannerStatus(isLight)
            updateStatusBarMode(isLight)
        }
    }

    /**
     * 设置轮播状态
     * @param start
     */
    private fun setBannerStatus(start: Boolean) {
        mBannerViewPager.run {
            if (start) {
                stopLoop()
            } else {
                startLoop()
            }
        }
    }

    override fun createObserver() {
        super.createObserver()
        mVm.getBanners().observe(viewLifecycleOwner, Observer {
            mBannerViewPager.create(it)
        })
        mVm.firstLoadSuccess.observe(viewLifecycleOwner, Observer {
            setStatusBarMode(false)
        })
    }

    override fun initData() {

    }

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {

    }


    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onLazyPause() {
        isShow = false
        setBannerStatus(false)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    override fun onLazyResume() {
        isShow = true
        super.onLazyResume()
        setBannerStatus(true)
    }

}