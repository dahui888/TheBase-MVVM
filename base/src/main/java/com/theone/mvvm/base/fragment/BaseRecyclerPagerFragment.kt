package com.theone.mvvm.base.fragment

import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.chad.library.adapter.base.listener.OnLoadMoreListener
import com.qmuiteam.qmui.util.QMUIDisplayHelper
import com.theone.mvvm.R
import com.theone.mvvm.base.constant.LayoutManagerType
import com.theone.mvvm.base.viewmodel.BaseListViewModel
import com.theone.mvvm.base.ext.net.loadListData
import com.theone.mvvm.base.ext.net.loadListError
import com.theone.mvvm.base.ext.showLoading
import com.theone.mvvm.base.ext.showLoadingPage
import com.theone.mvvm.base.ext.util.logE
import com.theone.mvvm.widge.SpacesItemDecoration
import kotlinx.android.synthetic.main.base_recycler_pager_fragment.*


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
 * @date 2021/2/23 0023
 * @describe TODO
 * @email 625805189@qq.com
 * @remark
 */
abstract class BaseRecyclerPagerFragment
<T, VM : BaseListViewModel<T>>
    : BaseVmFragment<VM>(),
    SwipeRefreshLayout.OnRefreshListener, OnLoadMoreListener, OnItemClickListener {

    lateinit var mAdapter: BaseQuickAdapter<T, *>

    override fun getLayoutId(): Int = R.layout.base_recycler_pager_fragment
    abstract fun createAdapter(): BaseQuickAdapter<T, *>

    protected open fun getRecyclerView(): RecyclerView = recyclerView
    protected open fun getRefreshLayout(): SwipeRefreshLayout = swipeRefresh

    override fun onLazyInit() {
        onFirstLoading()
    }

    override fun initView(rootView: View) {
        initAdapter()
        initRecyclerView()
        initPullRefreshLayout()
    }

    override fun onReLoad() {
        onFirstLoading()
    }

    open fun initAdapter() {
        mAdapter = createAdapter().apply {
            loadMoreModule.setOnLoadMoreListener(this@BaseRecyclerPagerFragment)
            setOnItemClickListener(this@BaseRecyclerPagerFragment)
        }
    }

    open fun initRecyclerView() {
        getRecyclerView().run {
            getSpacesItemDecoration()?.run {
                addItemDecoration(this)
            }
            layoutManager = getLayoutManager(mVm.type.value)
            adapter = mAdapter
        }
    }

    open fun getLayoutManager(type: LayoutManagerType?): RecyclerView.LayoutManager {
        val layoutManager: RecyclerView.LayoutManager
        layoutManager = when (type) {
            LayoutManagerType.GRID -> GridLayoutManager(mActivity, mVm.column.value)
            LayoutManagerType.STAGGERED -> {
                val m = StaggeredGridLayoutManager(
                    mVm.column.value,
                    StaggeredGridLayoutManager.VERTICAL
                )
                m.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_NONE
                return m
            }
            else -> LinearLayoutManager(mActivity)
        }
        return layoutManager
    }

    open fun getSpacesItemDecoration(): SpacesItemDecoration? {
        val space = QMUIDisplayHelper.dp2px(mActivity, mVm.space.value)
        return SpacesItemDecoration(
            if (mVm.type.value == LayoutManagerType.LIST) 1 else mVm.column.value,
            mAdapter.headerLayoutCount,
            space
        )
    }

    open fun initPullRefreshLayout() {
        getRefreshLayout().run {
            isEnabled = false
            setOnRefreshListener(this@BaseRecyclerPagerFragment)
        }
    }

    override fun createObserver() {
        mVm.run {
            type.observe(viewLifecycleOwner, Observer {
                getRecyclerView().layoutManager = getLayoutManager(it)
            })
            getResponseLiveData().observe(viewLifecycleOwner, Observer {
                loadListData(mVm, mAdapter, mLoadSir)
            })
            getErrorMsgLiveData().observe(viewLifecycleOwner, Observer {
                loadListError(
                    it,
                    mVm,
                    mAdapter,
                    mLoadSir
                )
            })
            getFinallyLiveData().observe(viewLifecycleOwner, Observer {
                onRefreshComplete()
            })
        }
    }

    open fun onRefreshComplete() {
        getRefreshLayout().run {
            isEnabled = true
            isRefreshing = false
        }
    }

    open fun onFirstLoading() {
        showLoadingPage()
        getRecyclerView().scrollToPosition(0)
        mVm.isFirst.value = true
        mVm.requestNewData()
    }

    override fun onRefresh() {
        mVm.isFresh.value = true
        mVm.requestNewData()
    }

    override fun onLoadMore() {
        mVm.requestServer()
    }

    override fun getViewModelIndex(): Int = 1

}