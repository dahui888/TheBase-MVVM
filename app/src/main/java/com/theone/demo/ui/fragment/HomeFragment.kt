package com.theone.demo.ui.fragment

import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getColor
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.OnLifecycleEvent
import androidx.recyclerview.widget.RecyclerView
import com.qmuiteam.qmui.kotlin.matchParent
import com.qmuiteam.qmui.qqface.QMUIQQFaceView
import com.qmuiteam.qmui.util.QMUIColorHelper
import com.qmuiteam.qmui.util.QMUIResHelper
import com.theone.demo.R
import com.theone.demo.data.model.bean.BannerResponse
import com.theone.demo.viewmodel.HomeViewModel
import com.theone.demo.app.widge.OffsetLinearLayoutManager
import com.theone.demo.app.widge.banner.BannerViewHolder
import com.theone.mvvm.base.constant.LayoutManagerType
import com.theone.mvvm.base.ext.dp2px
import com.theone.mvvm.base.ext.updateStatusBarMode
import com.theone.mvvm.base.ext.util.logE
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
    ArticleFragment<HomeViewModel>() {

    private fun showBanner(): Boolean = true

    private var mBannerViewPager: BannerViewPager<BannerResponse, BannerViewHolder>? = null
    private var mMaxOffsetHeight: Float = 0f
    private var isLightMode: Boolean = true
    private var isShow: Boolean = true
    private lateinit var mTitleView: QMUIQQFaceView

    override fun isNeedChangeStatusBarMode(): Boolean = true

    override fun isStatusBarLightMode(): Boolean = if (showBanner()) isLightMode else true

    override fun showTitleBar(): Boolean = true

    override fun translucentFull(): Boolean = showBanner()

    override fun initView(rootView: View) {
        super.initView(rootView)
        getTopBar()?.run {
            mTitleView = setTitle("首页")
            if (showBanner())
                setBackgroundAlpha(0)
        }
    }

    override fun createObserver() {
        super.createObserver()
        if (showBanner())
            mViewModel.getBanners().observe(viewLifecycleOwner, Observer {
                mBannerViewPager?.create(it)
            })
    }

    override fun initAdapter() {
        super.initAdapter()
        if (showBanner()) {
            val mBannerHeight = dp2px(250)
            mMaxOffsetHeight = (mBannerHeight - QMUIResHelper.getAttrDimen(
                mActivity,
                R.attr.qmui_topbar_height
            )).toFloat()
            mBannerViewPager = BannerViewPager<BannerResponse, BannerViewHolder>(mActivity)
            mBannerViewPager?.run {
                layoutParams = ViewGroup.LayoutParams(matchParent, mBannerHeight)
                setIndicatorGravity(IndicatorGravity.END)
                setIndicatorSliderColor(
                    getColor(mActivity, R.color.white),
                    QMUIResHelper.getAttrColor(mActivity, R.attr.app_skin_primary_color)
                )
                setHolderCreator { BannerViewHolder() }
                setOnPageClickListener { position: Int ->

                }
                mAdapter.addHeaderView(this)
            }
        }
    }

    override fun initRecyclerView() {
        super.initRecyclerView()
        if (showBanner())
            getRecyclerView().addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val offsetLinearLayoutManager =
                        recyclerView.layoutManager as OffsetLinearLayoutManager
                    val y = offsetLinearLayoutManager.computeVerticalScrollOffset(null)
                    val percent: Float = if (y > mMaxOffsetHeight)
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
            setBannerStatus(isShow)
            updateStatusBarMode(isLight)
        }
    }

    /**
     * 设置轮播状态
     * @param start
     */
    private fun setBannerStatus(start: Boolean) {
        if (showBanner())
            mBannerViewPager?.run {
                if (start) {
                    "startLoop ${this.javaClass.simpleName}".logE()
                    startLoop()
                } else {
                    "stopLoop ${this.javaClass.simpleName}".logE()
                    stopLoop()
                }
            }
    }

    override fun onFirstLoading() {
        super.onFirstLoading()
        if (showBanner())
            mViewModel.requestBanner()
    }

    override fun onRefresh() {
        super.onRefresh()
        if (showBanner())
            mViewModel.requestBanner()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onLazyPause() {
        "onLazyPause ${this.javaClass.simpleName}".logE()
        isShow = false
        setBannerStatus(false)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    override fun onLazyResume() {
        "onLazyResume ${this.javaClass.simpleName}".logE()
        isShow = true
        super.onLazyResume()
        setBannerStatus(true)
    }

}