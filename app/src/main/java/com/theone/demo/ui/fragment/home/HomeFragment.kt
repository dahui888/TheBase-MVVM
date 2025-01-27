package com.theone.demo.ui.fragment.home

import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getColor
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.OnLifecycleEvent
import androidx.recyclerview.widget.RecyclerView
import com.qmuiteam.qmui.alpha.QMUIAlphaImageButton
import com.qmuiteam.qmui.kotlin.matchParent
import com.qmuiteam.qmui.qqface.QMUIQQFaceView
import com.qmuiteam.qmui.util.QMUIColorHelper
import com.qmuiteam.qmui.util.QMUIResHelper
import com.theone.common.ext.dp2px
import com.theone.demo.R
import com.theone.demo.data.model.bean.BannerResponse
import com.theone.demo.viewmodel.HomeViewModel
import com.theone.demo.app.widget.OffsetLinearLayoutManager
import com.theone.demo.app.widget.banner.HomeBannerAdapter
import com.theone.demo.app.widget.banner.HomeBannerViewHolder
import com.theone.demo.ui.fragment.BaseArticleFragment
import com.theone.demo.ui.fragment.search.SearchFragment
import com.theone.demo.ui.fragment.web.WebExplorerFragment
import com.theone.common.ext.goneViews
import com.theone.common.ext.showViews
import com.theone.mvvm.ext.qmui.updateStatusBarMode
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
class HomeFragment : BaseArticleFragment<HomeViewModel>(), View.OnClickListener {

    private fun showBanner(): Boolean = true

    private var mBannerViewPager: BannerViewPager<BannerResponse, HomeBannerViewHolder>? = null
    private var mMaxOffsetHeight: Float = 0f
    private var isLightMode: Boolean = true
    private var isShow: Boolean = true
    private lateinit var mTitleView: QMUIQQFaceView
    private lateinit var mSearchBtn: QMUIAlphaImageButton

    override fun isNeedChangeStatusBarMode(): Boolean = true

    override fun isStatusBarLightMode(): Boolean = if (showBanner()) isLightMode else true

    override fun showTopBar(): Boolean = true

    override fun translucentFull(): Boolean = showBanner()

    override fun getItemSpace(): Int = 0

    override fun initView(rootView: View) {
        super.initView(rootView)
        getTopBar()?.run {
            mTitleView = setTitle("首页")
            mSearchBtn =
                addRightImageButton(R.drawable.mz_titlebar_ic_search_light, R.id.topbar_search)
            mSearchBtn.setOnClickListener(this@HomeFragment)
            goneViews(mSearchBtn)
            if (showBanner())
                setBackgroundAlpha(0)
        }
    }

    override fun createObserver() {
        super.createObserver()
        if (showBanner()){
            mViewModel.getBanners().observeInFragment(this, Observer {
                mBannerViewPager?.create(it)
                setStatusBarMode(false)
            })
        }

    }

    override fun initAdapter() {
        super.initAdapter()
        if (showBanner()) {
            val mBannerHeight = dp2px(250)
            mMaxOffsetHeight = (mBannerHeight - QMUIResHelper.getAttrDimen(
                mActivity,
                R.attr.qmui_topbar_height
            )).toFloat()
            mBannerViewPager = BannerViewPager<BannerResponse, HomeBannerViewHolder>(mActivity)
            mBannerViewPager?.run {
                layoutParams = ViewGroup.LayoutParams(matchParent, mBannerHeight)
                adapter = HomeBannerAdapter()
                setAutoPlay(true)
                setInterval(3000)
                setIndicatorGravity(IndicatorGravity.END)
                setIndicatorSliderColor(
                    getColor(mActivity, R.color.white),
                    QMUIResHelper.getAttrColor(mActivity, R.attr.app_skin_primary_color)
                )
                setOnPageClickListener { position: Int ->
                    mViewModel.getBanners().value?.let {
                        startFragment(
                            WebExplorerFragment.newInstance(
                                it[position]
                            )
                        )
                    }
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
                    val isLight = percent > 0.5
                    if (!isLightMode && isLight) {
                        setStatusBarMode(true)
                    } else if (isLightMode && !isLight) {
                        setStatusBarMode(false)
                    }
                    // 更改Title的字体颜色
                    mTitleView.setTextColor(
                        getColorAlpha(
                            percent,
                            R.color.qmui_config_color_gray_1
                        )
                    )
                    getTopBar()?.run {
                        // 更改底部分割线的颜色
                        updateBottomSeparatorColor(
                            getColorAlpha(
                                percent,
                                R.color.qmui_config_color_separator
                            )
                        )
                        // 更改背景颜色
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

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.topbar_search -> {
                startFragment(SearchFragment())
            }
        }
    }

    /**
     * @TODO 根据百分比获取一个颜色的alpha值
     * @param percent 百分比
     * @param color   颜色
     */
    private fun getColorAlpha(percent: Float, color: Int): Int {
        return QMUIColorHelper.setColorAlpha(
            getColor(mActivity, color),
            percent
        )
    }

    /**
     * 更改状态栏模式
     * @param isLight
     */
    private fun setStatusBarMode(isLight: Boolean) {
        // 只有当Banner数据存在后才进行状态栏的切换
        mViewModel.getBanners().value?.let {
            //显示的时候才做更改
            if (isShow) {
                showViews(mSearchBtn)
                mSearchBtn.setImageResource(if (isLight) R.drawable.mz_titlebar_ic_search_dark else R.drawable.mz_titlebar_ic_search_light)
                setBannerStatus(!isLight)
                updateStatusBarMode(isLight)
            }
            isLightMode = isLight
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
                    startLoop()
                } else {
                    stopLoop()
                }
            }
    }

    /**
     * 重写父类方法更换LayoutManager
     */
    override fun getLayoutManager(): RecyclerView.LayoutManager {
        return OffsetLinearLayoutManager(mActivity)
    }

    // 当前界面不显示了
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    override fun onLazyPause() {
        isShow = false
        setBannerStatus(false)
    }

    // 当前界面显示了
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    override fun onLazyResume() {
        isShow = true
        super.onLazyResume()
        setBannerStatus(true)
    }


}