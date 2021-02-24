package com.theone.mvvm.base.fragment

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.chad.library.adapter.base.listener.OnLoadMoreListener
import com.qmuiteam.qmui.util.QMUIDisplayHelper
import com.qmuiteam.qmui.widget.QMUITopBarLayout
import com.theone.mvvm.R
import com.theone.mvvm.base.constant.LayoutManagerType
import com.theone.mvvm.base.viewmodel.BaseListViewModel
import com.theone.mvvm.ext.getVmClazz
import com.theone.mvvm.ext.loadListData
import com.theone.mvvm.ext.showLoading
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
abstract class BaseListFragment
<T, AP : BaseQuickAdapter<T, *>, VM : BaseListViewModel<T>, DB : ViewDataBinding>
    : BaseVmDbFragment<VM, DB>(),
    SwipeRefreshLayout.OnRefreshListener, OnLoadMoreListener, OnItemClickListener {

    lateinit var mAdapter: BaseQuickAdapter<T, *>
    lateinit var mTopBarLayout: QMUITopBarLayout
    lateinit var mRefreshLayout: SwipeRefreshLayout
    lateinit var mRecyclerView: RecyclerView

    override fun getLayoutId(): Int = R.layout.base_topbar_fragment
    abstract fun createAdapter(): AP

    override fun onLazyInit() {
        onFirstLoading()
    }

    override fun initView(savedInstanceState: Bundle?) {
        initTopBar()
        initAdapter()
        initRecyclerView()
        initPullRefreshLayout()
        initLoadSer(mRefreshLayout) {
            mLoader.showLoading()
            mVm.requestServer()
        }
    }

    open fun initTopBar() {
        mTopBarLayout = mDB.root.findViewById(R.id.topBarLayout)
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
        mRefreshLayout.setOnRefreshListener(this@BaseListFragment)
    }

    override fun createObserver() {
        mVm.run {
            isFirstLoad.observe(viewLifecycleOwner, Observer {
                if (it) {
                    firstRequest()
                }
            })
            isHeadFresh.observe(viewLifecycleOwner, Observer {
                if (it) {
                    firstRequest()
                }
            })
            getResponse().observe(viewLifecycleOwner, Observer {
                loadListData(mVm, mAdapter, mLoader)
                mRefreshLayout.isEnabled = true
                mRefreshLayout.isRefreshing = false
            })
            type.observe(viewLifecycleOwner, Observer {
                mRecyclerView.layoutManager = getLayoutManager(it)
            })
        }
    }

    private fun firstRequest() {
        mVm.mPage.value = 1
        mVm.requestServer()
    }
    open fun onFirstLoading() {
        mLoader.showLoading()
        mRecyclerView.scrollToPosition(0)
        mVm.isFirstLoad.postValue(true)
    }

    override fun onRefresh() {
        mVm.isHeadFresh.postValue(true)
    }

    override fun onLoadMore() {
        mVm.requestServer()
    }

    override fun createViewModel(): VM {
        return ViewModelProvider(this).get(getVmClazz(this, 2))
    }

}