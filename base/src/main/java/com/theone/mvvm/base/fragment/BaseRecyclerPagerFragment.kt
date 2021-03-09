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
import com.theone.mvvm.base.ext.util.logE
import com.theone.mvvm.widge.SpacesItemDecoration


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
<T, AP : BaseQuickAdapter<T, *>, VM : BaseListViewModel<T>, DB : ViewDataBinding>
    : BaseVmDbFragment<VM, DB>(),
    SwipeRefreshLayout.OnRefreshListener, OnLoadMoreListener, OnItemClickListener {

    lateinit var mAdapter: BaseQuickAdapter<T, *>
    lateinit var mRefreshLayout: SwipeRefreshLayout
    lateinit var mRecyclerView: RecyclerView

    override fun getLayoutId(): Int = R.layout.base_recycler_pager_fragment
    abstract fun createAdapter(): AP

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
        mAdapter = createAdapter()
        mAdapter.loadMoreModule.setOnLoadMoreListener(this)
        mAdapter.setOnItemClickListener(this)
    }

    open fun initRecyclerView() {
        mRecyclerView = mDB.root.findViewById(R.id.recyclerView)
        mRecyclerView.run {
            addItemDecoration(getSpacesItemDecoration())
            layoutManager = getLayoutManager(mVm.type.value)
            adapter = mAdapter
        }
    }

    open fun getLayoutManager(type: LayoutManagerType?): RecyclerView.LayoutManager {
        val layoutManager: RecyclerView.LayoutManager
        layoutManager = when (type) {
            LayoutManagerType.GRID -> GridLayoutManager(mActivity, mVm.column.value)
            LayoutManagerType.STAGGERED -> {
                val m = StaggeredGridLayoutManager(mVm.column.value, StaggeredGridLayoutManager.VERTICAL)
                m.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_NONE
                return m
            }
            else -> LinearLayoutManager(mActivity)
        }
        return layoutManager
    }

    open fun getSpacesItemDecoration(): SpacesItemDecoration {
        val space = QMUIDisplayHelper.dp2px(mActivity, mVm.space.value)
        return SpacesItemDecoration(
            if (mVm.type.value == LayoutManagerType.LIST) 1 else mVm.column.value,
            mAdapter.headerLayoutCount,
            space
        )
    }

    open fun initPullRefreshLayout() {
        mRefreshLayout = mDB.root.findViewById(R.id.swipeRefresh)
        mRefreshLayout.isEnabled = false
        mRefreshLayout.setOnRefreshListener(this@BaseRecyclerPagerFragment)
    }

    override fun createObserver() {
        mVm.run {
            type.observe(viewLifecycleOwner, Observer {
                mRecyclerView.layoutManager = getLayoutManager(it)
            })
            getResponseLiveData().observe(viewLifecycleOwner, Observer {
                "Observer getResponse ${this.javaClass.simpleName}".logE()
                loadListData(mVm, mAdapter, mLoadSir)
            })
            getErrorMsgLiveData().observe(viewLifecycleOwner, Observer {
                "Observer getErrorMsg ${this.javaClass.simpleName}".logE()
                loadListError(
                    it,
                    mVm,
                    mAdapter,
                    mLoadSir
                )
            })
            getFinallyLiveData().observe(viewLifecycleOwner, Observer {
                "Observer Finally ${this.javaClass.simpleName}".logE()
                onRefreshComplete()
            })
        }
    }

    open fun onRefreshComplete(){
        mRefreshLayout.isEnabled = true
        mRefreshLayout.isRefreshing = false
    }

    open fun onFirstLoading() {
        mLoadSir.showLoading()
        mRecyclerView.scrollToPosition(0)
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

    override fun getViewModelIndex(): Int  = 2

}