package com.theone.mvvm.core.base.fragment

import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.theone.common.callback.IImageUrl
import com.theone.common.ext.getColor
import com.theone.common.ext.invisible
import com.theone.common.ext.isVisible
import com.theone.common.ext.visible
import com.theone.mvvm.core.R
import com.theone.mvvm.core.base.adapter.ImageSnapAdapter
import com.theone.mvvm.core.base.viewmodel.BaseListViewModel

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
 * @date 2021-04-25 09:14
 * @describe TODO
 * @email 625805189@qq.com
 * @remark
 */
abstract class BaseImageSnapFragment<T : IImageUrl, VM : BaseListViewModel<T>, DB : ViewDataBinding> :
    BasePagerPullRefreshFragment<T, VM, DB>(),ImageSnapAdapter.OnImageSnapClickListener<T> {

    abstract fun onScrollChanged(item:T,position:Int,total:Int)

    /**
     * @return 滑动方向
     */
    protected open fun getOrientation(): Int = RecyclerView.HORIZONTAL

    override fun createAdapter(): BaseQuickAdapter<T, *> = ImageSnapAdapter<T>(this)

    override fun initView(root: View) {
        super.initView(root)
        getContentView().setBackgroundColor(getColor(mActivity,R.color.qmui_config_color_background))
        getTopBar()
    }

    override fun initAdapter() {
        with(mAdapter) {
            if (this is LoadMoreModule)
                loadMoreModule.setOnLoadMoreListener(this@BaseImageSnapFragment)
            animationEnable = true
            setAnimationWithDefault(BaseQuickAdapter.AnimationType.ScaleIn)
            isAnimationFirstOnly = false
        }
    }

    override fun initRecyclerView() {
        super.initRecyclerView()
        getRecyclerView().let {
            PagerSnapHelper().attachToRecyclerView(it)
            it.addOnScrollListener(getScrollListener())
        }
    }

    protected open fun getScrollListener(): RecyclerView.OnScrollListener {
        return object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager
                val position = linearLayoutManager.findFirstVisibleItemPosition()
                if (mAdapter.data.size > position) {
                    onScrollChanged(mAdapter.getItem(position) as T,position,mAdapter.data.size)
                }
            }

        }
    }

    override fun getLayoutManager(): RecyclerView.LayoutManager {
        return LinearLayoutManager(mActivity, getOrientation(), false)
    }

    /**
     * 更改进出动画效果 QMUIFragment提供
     * @return
     */
    override fun onFetchTransitionConfig(): TransitionConfig = SCALE_TRANSITION_CONFIG

    override fun onImageClick(data: T,position: Int) {
        getTopBar()?.run {
            if(isVisible()){
                invisible()
            }else{
                visible()
            }
        }
    }

    override fun onVideoClick(data: T,position: Int) {

    }

    override fun onImageLongClick(data: T,position: Int): Boolean {
        return false
    }

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {

    }

}